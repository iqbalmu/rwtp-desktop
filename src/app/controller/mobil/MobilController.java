package app.controller.mobil;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MobilController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void deleteData(ActionEvent actionEvent) {
    }

    public void editData(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/xml/mobil/editMobil.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setTitle("Edit Data Mobil");
        stage.setScene(scene);
        stage.showAndWait();
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
