package app.controller.transaksi.rental;

import app.Main;
import app.dao.MobilDAO;
import app.dao.PelangganDAO;
import app.dao.RentalDAO;
import app.model.Mobil;
import app.model.Pelanggan;
import app.model.UserInfo;
import app.model.transaksi.Rental;
import app.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

public class RentalController implements Initializable {

    public ComboBox<String> cbIdentitas;
    public ComboBox<String> cbClass;
    public ComboBox<Mobil> cbMobil;
    public TextField txtNama;
    public TextField txtAlamat;
    public TextField txtPhone;
    public TextField txtLamaRental;

    public AnchorPane home;

    public AnchorPane contentPane;
    public TextField txtIdentitas;
    public DatePicker dpRental;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listIdentitas = FXCollections.observableArrayList("Mahasiswa", "Umum");
        cbIdentitas.setItems(listIdentitas);

        ObservableList<String> listClass = FXCollections.observableArrayList("Ekonomi", "Eksekutif");
        cbClass.setItems(listClass);
    }

    public ObservableList<Mobil> getMobil(String kelas) {
        ObservableList<Mobil> mobils;
        MobilDAO mobilDAO = new MobilDAO();
        mobils = mobilDAO.getMobilAvailable(kelas);
        return mobils;
    }

    public void selectClass(ActionEvent actionEvent) {
        String selectedClass = cbClass.getValue();
        cbMobil.setItems(getMobil(selectedClass));
    }

    final String noTransaksi = UUID.randomUUID().toString();
    public void handleSaveRental(ActionEvent actionEvent) throws IOException {
        System.out.println("Saved");

        String identitas = txtIdentitas.getText();
        String nama = txtNama.getText();
        String phone = txtPhone.getText();
        String kategori = cbIdentitas.getSelectionModel().getSelectedItem();
        String alamat = txtAlamat.getText();
        boolean isMember = false;  //isMember nanti ambil dari data pelanggan untuk mengubah harga menjadi free

        String nmobil = cbMobil.getSelectionModel().getSelectedItem().toString();
        String nopol = nmobil.substring(0, nmobil.indexOf("|")-1);
        LocalDate rentalDate = dpRental.getValue();
        int dRental = Integer.parseInt(txtLamaRental.getText());
        String kelas = cbClass.getValue();
        double harga = ((Objects.equals(kelas, "Ekonomi")) ? 100_000 : 150_000)*dRental;
        String keterangan = "";

        // confirm data
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/xml/transaksi/rental/DetailTransaksiRental.fxml"));
        Parent root = loader.load();
        DetailRentalController drc = loader.getController();
        drc.setField(
                nama,
                identitas,
                kelas,
                nmobil,
                rentalDate.toString(),
                rentalDate.plusDays(dRental).toString(),
                String.valueOf(harga),
                UserInfo.username,
                new Timestamp(new Date().getTime()).toString()
        );
        Scene scene = new Scene(root);
        stage.setTitle("Confirm Data");
        stage.setScene(scene);
        stage.showAndWait();

        if (!drc.status) {
            System.out.println("rental batal");
            return;
        }

        // insert table pelanggan
        Pelanggan pelanggan = new Pelanggan(identitas,nama,phone,kategori,alamat,isMember);
        PelangganDAO pDao = new PelangganDAO();
        pDao.addData(pelanggan);

        // set model rental
        Rental rental = new Rental();

        rental.setId_pelanggan(identitas);
        rental.setNopol(nopol);
        rental.setId_user(UserInfo.id_user);
        rental.setDate(new Timestamp(new Date().getTime()));
        rental.setLama_rental(dRental);
        rental.setRental_date(java.sql.Date.valueOf(rentalDate));
        rental.setBayar(harga);
        rental.setKeterangan(keterangan);
        rental.setNo_transaksi(noTransaksi);
        rental.setReturn_date(java.sql.Date.valueOf(rentalDate.plusDays(dRental)));

        System.out.println(rental.getId_pelanggan());
        System.out.println(rental.getNopol());
        System.out.println(rental.getId_user());
        System.out.println(rental.getDate());
        System.out.println(rental.getLama_rental());
        System.out.println(rental.getRental_date());
        System.out.println(rental.getReturn_date());
        System.out.println(rental.getBayar());

        System.out.println("set rental model success");

//        insert table rental
        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.addData(rental);
        System.out.println("store rental transaksi success");

//        set status mobil beroperasi
        MobilDAO mobilDAO = new MobilDAO();
        mobilDAO.updateStatus("Rental", nopol);
        System.out.println("update status oke");

//        back to AllTransaction;
//        Main main = new Main();
//        main.changeScene("view/xml/main.fxml");
    }

    public void printHandler(ActionEvent actionEvent) {
        try {
            String templateFile = "jasper/rental/rental_tiket.jrxml";
            String compiledReportFile = "jasper/rental/rental_tiket.jasper";
            String pdfFileName = "report/rental/rental_tiket_" + noTransaksi + ".pdf";
            JasperCompileManager.compileReportToFile(templateFile, compiledReportFile);

            // Fill Jasper Report with data
            Map<String, Object> parameters = new HashMap<>();
            // Set report parameters if needed
            parameters.put("noTransaksi", noTransaksi);
            // print report
            JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReportFile, parameters, JDBCConnection.getConnection());
            //export pdf
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFileName);
            // View the generated report
            JasperViewer.viewReport(jasperPrint, false);
        }catch (JRException e){
            e.printStackTrace();
        }
    }

//    public void showTransaksi() throws IOException {
//        Stage stage = new Stage();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/xml/transaksi/rental/DetailTransaksiRental.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        stage.setTitle("Confirm Data");
//        stage.setScene(scene);
//        stage.showAndWait();
//    }
}
