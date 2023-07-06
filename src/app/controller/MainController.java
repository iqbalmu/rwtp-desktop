package app.controller;

import app.Main;
import app.model.UserInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainController {

    public Button logoutBtn;
    public Label userLabel;
    public Button dashboardBtn;
    public Button laporanBtn;
    public Button transaksiBtn;
    public Button pelangganBtn;
    public Button sopirBtn;
    public Button mobilBtn;

    public AnchorPane contentPane;
    public AnchorPane home;
    public Button rentalBtn;
    public Button paketBtn;
    public Button sewaBtn;

    public void setNode(Node node){
        contentPane.getChildren().clear();
        contentPane.getChildren().add(node);
    }

    public void initialize(){
        userLabel.setText(UserInfo.username);
        createPage();
    }

    public void createPage() {
        try {
            home = FXMLLoader.load(getClass().getResource("/xml/dashboard/dashboard.fxml"));
            setNode(home);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

//    public void getSceneAddTransaksi(){
//        try {
//            home = FXMLLoader.load(getClass().getResource("/xml/transaksi/Transaksi.fxml"));
//            setNode(home);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }

    public void logout(ActionEvent actionEvent) throws IOException {
        Main m = new Main();
        m.changeScene("view/xml/login.fxml");
    }

    public void dashboardHandler(ActionEvent actionEvent) {
        createPage();
    }

    public void transaksiHandler(ActionEvent actionEvent) {
        try {
            home = FXMLLoader.load(getClass().getResource("/xml/transaksi/Transaksi.fxml"));
            setNode(home);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void rentalHandler(ActionEvent actionEvent) {
        try {
            home = FXMLLoader.load(getClass().getResource("/xml/rental/RentalTransaksi.fxml"));
            setNode(home);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void paketHandler(ActionEvent actionEvent) {
        try {
            home = FXMLLoader.load(getClass().getResource("/xml/paket/PaketTransaksi.fxml"));
            setNode(home);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sewaHandler(ActionEvent actionEvent) {
        try {
            home = FXMLLoader.load(getClass().getResource("/xml/sewa/SewaTransaksi.fxml"));
            setNode(home);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void pelangganHandler(ActionEvent actionEvent) {
        try {
            home = FXMLLoader.load(getClass().getResource("/xml/pelanggan/pelanggan.fxml"));
            setNode(home);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sopirHandler(ActionEvent actionEvent) {
        try {
            home = FXMLLoader.load(getClass().getResource("/xml/sopir/sopir.fxml"));
            setNode(home);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void mobilHandler(ActionEvent actionEvent) {
        try {
            home = FXMLLoader.load(getClass().getResource("/xml/mobil/mobil.fxml"));
            setNode(home);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void laporanHandler(ActionEvent actionEvent) {
        try {
            home = FXMLLoader.load(getClass().getResource("/xml/laporan/laporan.fxml"));
            setNode(home);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
