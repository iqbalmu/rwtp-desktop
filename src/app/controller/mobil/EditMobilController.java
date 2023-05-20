package app.controller.mobil;

import app.dao.MobilDAO;
import app.utility.JDBCConnection;
import app.utility.SelectDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditMobilController implements Initializable {
    public TextField txtNopol;
    public ComboBox cbJenis;
    public ComboBox cbKelas;
    public ComboBox cbSopir;
    public ComboBox cbStatus;
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

    public void updateDataMobil(ActionEvent actionEvent) {

    }
}
