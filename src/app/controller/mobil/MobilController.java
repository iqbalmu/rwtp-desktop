package app.controller.mobil;

import app.dao.MobilDAO;
import app.model.Mobil;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MobilController implements Initializable {


    public TableView<Mobil> tableMobil;
    public TableColumn<Mobil, Integer> id;
    public TableColumn<Mobil, String> nopol;
    public TableColumn<Mobil, String> jenis;
    public TableColumn<Mobil, Integer> sopir;
    public TableColumn<Mobil, String> kelas;
    public TableColumn<Mobil, String> status;

    public Mobil selected;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<Mobil, Integer>("id"));
        nopol.setCellValueFactory(new PropertyValueFactory<Mobil, String>("nopol"));
        jenis.setCellValueFactory(new PropertyValueFactory<Mobil, String>("jenis"));
        kelas.setCellValueFactory(new PropertyValueFactory<Mobil, String>("kelas"));
        status.setCellValueFactory(new PropertyValueFactory<Mobil, String>("status"));
        sopir.setCellValueFactory(new PropertyValueFactory<Mobil, Integer>("id_sopir"));

        MobilDAO mobilDAO = new MobilDAO();
        ObservableList<Mobil> mList = mobilDAO.showData();
        tableMobil.setItems(mList);
    }


    public void deleteData(ActionEvent actionEvent) {
        MobilDAO mobilDAO = new MobilDAO();
        System.out.println(mobilDAO.showData());

    }

    public void editData(ActionEvent actionEvent) throws IOException {
        System.out.println("Edit Data");
        selected = tableMobil.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();

        if (selected != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/xml/mobil/editMobil.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            EditMobilController emc = loader.getController();
            emc.setField(selected.getId(),selected.getNopol(),selected.getKelas(),selected.getStatus(),selected.getId_sopir());

            stage.setTitle("Edit Data Mobil");
            stage.setScene(scene);
            stage.showAndWait();
        }else{

        }

    }

    public void addData(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/xml/mobil/addMobil.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setTitle("Tambah Data Mobil");
        stage.setScene(scene);
        stage.showAndWait();
    }


}
