package app.controller.sopir;

import app.dao.SopirDAO;
import app.model.Sopir;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class SopirController {

    public TableView<Sopir> tableSopir;
    public TableColumn<Sopir, Integer> id;
    public TableColumn<Sopir, String> name;
    public TableColumn<Sopir, String> phone;
    public TableColumn<Sopir, String> address;
    public TableColumn<Sopir, String> ktp;
    public TableColumn<Sopir, String> sim;

    public void initialize() {
        id.setCellValueFactory(new PropertyValueFactory<Sopir, Integer>("id_sopir"));
        name.setCellValueFactory(new PropertyValueFactory<Sopir, String>("nama"));
        phone.setCellValueFactory(new PropertyValueFactory<Sopir, String>("phone"));
        address.setCellValueFactory(new PropertyValueFactory<Sopir, String>("alamat"));
        ktp.setCellValueFactory(new PropertyValueFactory<Sopir, String>("ktp"));
        sim.setCellValueFactory(new PropertyValueFactory<Sopir, String>("sim"));

        SopirDAO sDAO = new SopirDAO();
        ObservableList<Sopir> sList = sDAO.showData();
        tableSopir.setItems(sList);
    }

    public Sopir selected;

    public void deleteData(ActionEvent actionEvent) {
        System.out.println("delete data");
        selected = tableSopir.getSelectionModel().getSelectedItem();
        System.out.println(selected);

        SopirDAO sDao = new SopirDAO();
        int result = sDao.delData(selected);
        if (result != 0) {
            System.out.println("Delete Success");
        }

        ObservableList<Sopir> sList = sDao.showData();
        tableSopir.setItems(sList);
    }

    public void addData(ActionEvent actionEvent) throws IOException {
        System.out.println("add data");
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/xml/sopir/addSopir.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setTitle("Tambah Data Sopir");
        stage.setScene(scene);
        stage.showAndWait();

        AddSopirController asc = loader.getController();
        tableSopir.setItems(asc.getsList());
    }

    public void editData(ActionEvent actionEvent) throws IOException {
        System.out.println("edit data");
        selected = tableSopir.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();

        if (selected != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/xml/sopir/editSopir.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            EditSopirController esc = loader.getController();
            esc.setField(selected.getId_sopir(), selected.getNama(), selected.getAlamat(), selected.getPhone(), selected.getSim(), selected.getKtp());

            stage.setTitle("Edit Data Sopir");
            stage.setScene(scene);
            stage.showAndWait();

            tableSopir.setItems(esc.getList());
        }else{
            stage.setTitle("Edit Data Sopir");
            Label label = new Label();
            label.setText("Data Edit Belum dipilih");

            Scene scene = new Scene(label);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void updateDataSopir(ActionEvent actionEvent) throws IOException {
        System.out.println("update data sopir");
    }

}
