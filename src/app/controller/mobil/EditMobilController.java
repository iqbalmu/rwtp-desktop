package app.controller.mobil;

import app.utility.JDBCConnection;
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

        cbJenis.setItems(lJenis);
        cbKelas.setItems(lKelas);
        cbStatus.setItems(lStatus);

        getDbSopirOption();
    }

    public void getDbSopirOption(){
        ObservableList<String> option = FXCollections.observableArrayList();
        try{
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement("select nama_sopir from sopir");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                option.add(res.getString("nama_sopir"));
            }
            cbSopir.setItems(option);
        }catch (SQLException ex) {
            System.out.println("getDbSopir");
            System.out.println(ex.getMessage());
        }
    }

    public void updateDataMobil(ActionEvent actionEvent) {

    }
}
