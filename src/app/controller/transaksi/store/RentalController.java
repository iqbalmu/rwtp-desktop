package app.controller.transaksi.store;

import app.Main;
import app.controller.MainController;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

public class RentalController implements Initializable{

    public ComboBox<String> cbIdentitas;
    public ComboBox<String> cbClass;
    public ComboBox<Mobil> cbMobil;
    public TextField txtNama;
    public TextField txtAlamat;
    public TextField txtPhone;
    public TextField txtLamaRental;

    public AnchorPane home;

    public AnchorPane contentPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listIdentitas = FXCollections.observableArrayList("KTM", "KTP");
        cbIdentitas.setItems(listIdentitas);

        ObservableList<String> listClass = FXCollections.observableArrayList("Ekonomi", "Eksekutif");
        cbClass.setItems(listClass);
    }

    public ObservableList<Mobil> getMobil(String kelas){
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

        // insert table pelanggan
        String id_pelanggan = UUID.randomUUID().toString();
        Pelanggan pelanggan = new Pelanggan(
                id_pelanggan,
                txtNama.getText(),
                txtPhone.getText(),
                cbIdentitas.getSelectionModel().getSelectedItem(),
                txtAlamat.getText(),
                false
        );
        System.out.println("set pelanggan model success");

        PelangganDAO pDao = new PelangganDAO();
        pDao.addData(pelanggan);

        System.out.println("store pelanggan succes");

        // set model rental

        Rental rental = new Rental();
        String nopol = cbMobil.getSelectionModel().getSelectedItem().toString();

        rental.setId_pelanggan(id_pelanggan);
        rental.setNopol(nopol);
        rental.setId_user(UserInfo.id_user);
        rental.setTimestamp(new Timestamp(new Date().getTime()));
        rental.setLama_rental(Integer.parseInt(txtLamaRental.getText()));
        rental.setBayar(100000); //sementara
        rental.setKeterangan("Bayar Lunas");
        rental.setNo_transaksi(UUID.randomUUID().toString());
        System.out.println("set rental model success");

//        insert table rental
        RentalDAO rentalDAO = new RentalDAO();
        rentalDAO.addData(rental);
        System.out.println("store rental transaksi success");

//        set status mobil beroperasi
        MobilDAO mobilDAO = new MobilDAO();
        mobilDAO.updateStatus("Beroperasi", nopol);

//        back to AllTransaction;
        Main main = new Main();
        main.changeScene("view/xml/main.fxml");
    }
}
