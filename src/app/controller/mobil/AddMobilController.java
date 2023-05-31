package app.controller.mobil;

import app.dao.MobilDAO;
import app.dao.SopirDAO;
import app.model.Mobil;
import app.model.Sopir;
import app.utility.JDBCConnection;
import app.utility.SelectDb;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddMobilController implements Initializable {

    public TextField txtNopol;
    public ComboBox cbJenis;
    public ComboBox cbKelas;
    public ComboBox cbSopir;
    public ComboBox cbStatus;

    private ObservableList<Mobil> listMobil;

    public ObservableList<Mobil> getListMobil() {
        return listMobil;
    }

    public void insertDataMobil(ActionEvent actionEvent) {
        String nopol = txtNopol.getText();
        String jenis = cbJenis.getSelectionModel().getSelectedItem().toString();
        String kelas = cbKelas.getSelectionModel().getSelectedItem().toString();
        Sopir sopir = (Sopir) cbSopir.getSelectionModel().getSelectedItem();
        String status = cbStatus.getSelectionModel().getSelectedItem().toString();

        MobilDAO mobilDAO = new MobilDAO();
        mobilDAO.addData(new Mobil(nopol,jenis,kelas,status, sopir.getId_sopir()));
        listMobil = mobilDAO.showData();

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> lJenis = FXCollections.observableArrayList("Inova", "Reborn", "Panther");
        ObservableList<String> lKelas = FXCollections.observableArrayList("Ekonomi", "Eksekutif");
        ObservableList<String> lStatus = FXCollections.observableArrayList("Ready", "Maintanance");

        cbJenis.setItems(lJenis);
        cbKelas.setItems(lKelas);
        cbStatus.setItems(lStatus);
        cbSopir.setItems(getDataSopir());

    }

    public ObservableList<Sopir> getDataSopir() {
        ObservableList<Sopir> data;
        SopirDAO sopirDAO = new SopirDAO();
        data = sopirDAO.getSopirAvailable();

        return data;
    }

}
