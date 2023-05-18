package app.controller.pelanggan;

import app.dao.PelangganDAO;
import app.model.Pelanggan;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class PelangganController {
    public Button delBtn;
    public Button editBtn;
    public Button addBtn;

    public TableView<Pelanggan> tablePelanggan;
    public TableColumn<Pelanggan, Integer> id;
    public TableColumn<Pelanggan, String> nama;
    public TableColumn<Pelanggan, String> telp;
    public TableColumn<Pelanggan, String> kategori;
    public TableColumn<Pelanggan, String> alamat;
    public TableColumn<Pelanggan, String> isMember;

    public void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<Pelanggan, Integer>("id"));
        nama.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("nama"));
        telp.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("telp"));
        kategori.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("kategori"));
        alamat.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("alamat"));
        isMember.setCellValueFactory(new PropertyValueFactory<Pelanggan, String>("isMember"));

        PelangganDAO pDAO = new PelangganDAO();
        ObservableList<Pelanggan> pList = pDAO.showData();
        tablePelanggan.setItems(pList);
    }

    public void deleteData(ActionEvent actionEvent) {
        System.out.println("delete data");
        Pelanggan selected;
        selected = (Pelanggan) tablePelanggan.getSelectionModel().getSelectedItem();
        System.out.println(selected);

        PelangganDAO pDao = new PelangganDAO();
        int result = pDao.delData(selected);
        if (result != 0){
            System.out.println("Delete Success");
        }

        ObservableList<Pelanggan> pList = pDao.showData();
        tablePelanggan.setItems(pList);
    }

    public void addData(ActionEvent actionEvent) throws IOException {
        System.out.println("add data");
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/xml/pelanggan/addPelanggan.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        AddPelangganController apc = loader.getController();
        stage.setTitle("Tambah Data Pelanggan");
        stage.setScene(scene);
        stage.showAndWait();

        tablePelanggan.setItems(apc.getpList());
    }

    public void editData(ActionEvent actionEvent) throws IOException {
        System.out.println("edit data");
        Pelanggan selected = (Pelanggan) tablePelanggan.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        if (selected != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/xml/pelanggan/editPelanggan.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            EditPelangganController epc = loader.getController();
            epc.setField(selected.getId(), selected.getNama(), selected.getAlamat(), selected.getTelp(), selected.getKategori(),selected.getIsMember());

            stage.setTitle("Edit Data Sopir");
            stage.setScene(scene);
            stage.showAndWait();

            tablePelanggan.setItems(epc.getList());
        }else{
            Label label = new Label();
            label.setText("Data belum dipilih");
            Scene scene = new Scene(label);
            stage.setScene(scene);
            stage.show();
        }

    }
}
