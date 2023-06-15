package app.controller.mobil;

import app.dao.MobilDAO;
import app.dao.SopirDAO;
import app.model.Mobil;
import app.model.Sopir;
import app.utility.JDBCConnection;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditMobilController implements Initializable {
    public TextField txtNopol;
    public ComboBox<String> cbJenis;
    public ComboBox<String> cbKelas;
    public ComboBox<Sopir> cbSopir;
    public ComboBox<String> cbStatus;

    private ObservableList<Mobil> list;

    public ObservableList<Mobil> getList() {
        return list;
    }

    public int id;
//    public int id_sopir;
//    public String nopol, jenis, kelas, status;
    public void setField(int id, String nopol, String jenis, String kelas, String status, int id_sopir) {
        this.id = id;
        this.txtNopol.setText(nopol);
        this.cbJenis.setValue(jenis);
        this.cbKelas.setValue(kelas);
        this.cbStatus.setValue(status);
        this.cbSopir.setValue(getNamaSopir(id_sopir));
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

    public void updateDataMobil(ActionEvent actionEvent) {
        String nopol = txtNopol.getText();
        String jenis = cbJenis.getSelectionModel().getSelectedItem();
        String kelas = cbKelas.getSelectionModel().getSelectedItem();
        Sopir sopir = cbSopir.getSelectionModel().getSelectedItem();
        String status = cbStatus.getSelectionModel().getSelectedItem();

        MobilDAO mobilDAO = new MobilDAO();
        int result = mobilDAO.updateData(new Mobil(id, nopol, jenis, kelas, status, sopir.getId_sopir()));

        if (result != 0) {
            System.out.println("Update Succes");
        }else{
            System.out.println("Update Data Failed!");
        }

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();

        list = mobilDAO.showData();

    }

    public ObservableList<Sopir> getDataSopir() {
        ObservableList<Sopir> data;
        SopirDAO sopirDAO = new SopirDAO();
        data = sopirDAO.getSopirAvailable();

        return data;
    }

    public Sopir getNamaSopir(int id){
        Sopir sopir = new Sopir();
        try {
            String sql = "SELECT nama_sopir FROM sopir WHERE id_sopir=?";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                String nama = res.getString("nama_sopir");
                sopir.setNama(nama);
            }

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return sopir;
    }

}
