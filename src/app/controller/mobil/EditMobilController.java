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
    public ComboBox<String> cbJadwal;
    public ComboBox<Sopir> cbSopir;
    public ComboBox<String> cbStatus;

    private ObservableList<Mobil> list;

    public ObservableList<Mobil> getList() {
        return list;
    }

    public int id;
    public int id_sopir;
//    public int id_sopir;
//    public String nopol, jenis, kelas, status;
    public void setField(int id, String nopol, String jenis, String kelas, String jadwal, String status, int id_sopir) {
        this.id = id;
        this.id_sopir = id_sopir;
        this.txtNopol.setText(nopol);
        this.cbJenis.setValue(jenis);
        this.cbKelas.setValue(kelas);
        this.cbJadwal.setValue(jadwal);
        this.cbStatus.setValue(status);
        this.cbSopir.setValue(getNamaSopir(id_sopir));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> lJenis = FXCollections.observableArrayList("Inova", "Reborn", "Panther");
        ObservableList<String> lKelas = FXCollections.observableArrayList("Ekonomi", "Eksekutif");
        ObservableList<String> lJadwal = FXCollections.observableArrayList("Pagi 10:00 WIB", "Siang 14:00 WIB", "Malam 20:00 WIB");
        ObservableList<String> lStatus = FXCollections.observableArrayList("Ready", "Maintanance");

        cbJenis.setItems(lJenis);
        cbKelas.setItems(lKelas);
        cbJadwal.setItems(lJadwal);
        cbStatus.setItems(lStatus);
        cbSopir.setItems(getDataSopir());
    }

    public void updateDataMobil(ActionEvent actionEvent) {
        String nopol = txtNopol.getText();
        String jenis = cbJenis.getSelectionModel().getSelectedItem();
        String kelas = cbKelas.getSelectionModel().getSelectedItem();
        String jadwal = cbJadwal.getSelectionModel().getSelectedItem();
        Sopir sopir = cbSopir.getSelectionModel().getSelectedItem();
        int id_sopir = (sopir.getId_sopir() > 0) ? sopir.getId_sopir() : this.id_sopir;
        String status = cbStatus.getSelectionModel().getSelectedItem();

        System.out.println(nopol);
        System.out.println(jenis);
        System.out.println(kelas);
        System.out.println(jadwal);
        System.out.println(id_sopir);
        System.out.println(status);

        Mobil mobil = new Mobil();
        mobil.setId(id);
        mobil.setNopol(nopol);
        mobil.setJenis(jenis);
        mobil.setKelas(kelas);
        mobil.setJadwal(jadwal);
        mobil.setId_sopir(id_sopir);
        mobil.setStatus(status);

        MobilDAO mobilDAO = new MobilDAO();
        int result = mobilDAO.updateData(mobil);

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
