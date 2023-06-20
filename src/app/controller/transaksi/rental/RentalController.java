package app.controller.transaksi.rental;

import app.Main;
import app.dao.MobilDAO;
import app.dao.PelangganDAO;
import app.dao.RentalDAO;
import app.model.Mobil;
import app.model.Pelanggan;
import app.model.UserInfo;
import app.model.transaksi.Rental;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listIdentitas = FXCollections.observableArrayList("Mahasiswas", "Umum");
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

    public void handleSaveRental(ActionEvent actionEvent) throws IOException {
        System.out.println("Saved");

        String identitas = txtIdentitas.getText();
        String nama = txtNama.getText();
        String phone = txtPhone.getText();
        String kategori = cbIdentitas.getSelectionModel().getSelectedItem();
        String alamat = txtAlamat.getText();
        boolean isMember = false;  //isMember nanti ambil dari data pelanggan untuk mengubah harga menjadi free

        showTransaksi();

        // insert table pelanggan
        Pelanggan pelanggan = new Pelanggan(identitas,nama,phone,kategori,alamat,isMember);
        PelangganDAO pDao = new PelangganDAO();
        pDao.addData(pelanggan);

        // set model rental
        Rental rental = new Rental();
        String nopol = cbMobil.getSelectionModel().getSelectedItem().toString();
        int dRental = Integer.parseInt(txtLamaRental.getText());
        int harga = (isMember) ? 0 : 100_000;
        if (kategori.equals("Mahasiswa")) {
            harga = harga - 25_000;
        }
        String keterangan = "";
        String noTransaksi = UUID.randomUUID().toString();

        rental.setId_pelanggan(txtIdentitas.getText());
        rental.setNopol(nopol);
        rental.setId_user(UserInfo.id_user);
        rental.setDate(new Timestamp(new Date().getTime()));
        rental.setLama_rental(dRental);
        rental.setBayar(dRental * harga);
        rental.setKeterangan(keterangan);
        rental.setNo_transaksi(noTransaksi);

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
        Main main = new Main();
        main.changeScene("view/xml/main.fxml");
    }

    public void showTransaksi() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/xml/transaksi/rental/DetailTransaksiRental.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setTitle("Confirm Data");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
