package app.controller.pelanggan;

import app.dao.PelangganDAO;
import app.dao.SopirDAO;
import app.model.Pelanggan;
import app.model.Sopir;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPelangganController implements Initializable {

    public TextField txtNama;
    public TextField txtTelp;
    public TextField txtKategori;
    public TextField txtAlamat;
    public Button btnTambah;
    public ComboBox cbKategori;

    private ObservableList<Pelanggan> pList;

    public ObservableList<Pelanggan> getpList() {
        return pList;
    }

    public void insertData(ActionEvent actionEvent) {
        String nama = txtNama.getText();
        String telp = txtTelp.getText();
        String kategori = cbKategori.getSelectionModel().getSelectedItem().toString();
        kategori = (kategori == "KTP") ? "umum" : "mahasiswa";
        String alamat = txtAlamat.getText();

        if(nama.isEmpty() || telp.isEmpty() || kategori.isEmpty() || alamat.isEmpty()){
            System.out.println("field kosong");
        }else{
            PelangganDAO dao = new PelangganDAO();
            dao.addData(new Pelanggan(nama, telp, kategori, alamat, false));
            pList = dao.showData();
        }

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listKategori = FXCollections.observableArrayList("Mahasiswa", "Umum");
        cbKategori.setItems(listKategori);
    }
}
