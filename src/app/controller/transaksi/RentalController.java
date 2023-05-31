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

public class RentalController implements Initializable{

    public ComboBox cbIdentitas;
    public ComboBox cbLRental;
    public ComboBox cbClass;
    public ComboBox cbMobil;

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
