package app.controller.transaksi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class RentalController implements Initializable{

    public ComboBox cbIdentitas;
    public ComboBox cbLRental;
    public ComboBox cbClass;
    public ComboBox cbJadwal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listIdentitas = FXCollections.observableArrayList("KTM", "KTP");
        cbIdentitas.setItems(listIdentitas);

        ObservableList<String> listLRental = FXCollections.observableArrayList(
                "1 hari", "2 hari", "3 hari", "4 hari", "5 hari"
        );
        cbLRental.setItems(listLRental);

        ObservableList<String> listClass = FXCollections.observableArrayList("Ekonomi", "Eksekutif");
        cbClass.setItems(listClass);

        ObservableList<String> listJadwal = FXCollections.observableArrayList(
                "Pagi 10:00 WIB", "Siang 14:00 WIB", "Malam 20:00 WIB");
        cbJadwal.setItems(listJadwal);
    }
}
