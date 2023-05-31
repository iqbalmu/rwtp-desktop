package app.controller.transaksi;

import app.dao.MobilDAO;
import app.model.Mobil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class SewaController implements Initializable{

    public ComboBox cbIdentitas;
    public ComboBox cbClass;
    public ComboBox cbJadwal;
    public ComboBox cbMobil;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listIdentitas = FXCollections.observableArrayList("KTM", "KTP");
        cbIdentitas.setItems(listIdentitas);

        ObservableList<String> listClass = FXCollections.observableArrayList("Ekonomi", "Eksekutif");
        cbClass.setItems(listClass);

        ObservableList<String> listJadwal = FXCollections.observableArrayList(
                "Pagi 10:00 WIB", "Siang 14:00 WIB", "Malam 20:00 WIB");
        cbJadwal.setItems(listJadwal);
    }

    public ObservableList<Mobil> getMobil(String kelas){
        ObservableList<Mobil> mobils;
        MobilDAO mobilDAO = new MobilDAO();
        mobils = mobilDAO.getMobilAvailable(kelas);
        return mobils;
    }

    public void selectClass(ActionEvent actionEvent) {
        String selectedClass = cbClass.getValue().toString();
        cbMobil.setItems(getMobil(selectedClass));
    }
}
