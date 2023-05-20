package app.controller.mobil;

import app.dao.SopirDAO;
import app.model.Sopir;
import app.utility.SelectDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddMobilController implements Initializable {

    public TextField txtNopol;
    public ComboBox cbJenis;
    public ComboBox cbKelas;
    public ComboBox cbSopir;
    public ComboBox cbStatus;

    public void insertDataMobil(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> lJenis = FXCollections.observableArrayList("Inova", "Reborn", "Fortuner");
        ObservableList<String> lKelas = FXCollections.observableArrayList("Ekonomi", "Eksekutif");
        ObservableList<String> lStatus = FXCollections.observableArrayList("Ready", "Drive", "Maintanance");
        ObservableList<String> lSopir = SelectDb.where("sopir", "nama_sopir");

        cbJenis.setItems(lJenis);
        cbKelas.setItems(lKelas);
        cbStatus.setItems(lStatus);
        cbSopir.setItems(lSopir);
    }


}
