package app.controller.mobil;

import app.dao.MobilDAO;
import app.dao.SopirDAO;
import app.model.Sopir;
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
    public int id;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> lJenis = FXCollections.observableArrayList("Inova", "Reborn", "Fortuner");
        ObservableList<String> lKelas = FXCollections.observableArrayList("Ekonomi", "Eksekutif");
        ObservableList<String> lStatus = FXCollections.observableArrayList("Ready", "Drive", "Maintanance");

        cbJenis.setItems(lJenis);
        cbKelas.setItems(lKelas);
        cbStatus.setItems(lStatus);
        cbSopir.setItems(getDataSopir());
    }

    public void updateDataMobil(ActionEvent actionEvent) {

    }

    public ObservableList<Sopir> getDataSopir() {
        ObservableList<Sopir> data;
        SopirDAO sopirDAO = new SopirDAO();
        data = sopirDAO.showData();

        return data;
    }

//    public void setField(int id, String nopol, String jenis, String kelas, String status, String sopir) {
//        this.id = id;
//        this.txtNopol.setText(nopol);
//        this.cbJenis.setValue(kelas);
//        this.cbKelas.setValue(kelas);
//        this.cbStatus.setValue(status);
//        this.cbSopir.setValue(sopir);
//    }

//    public
}
