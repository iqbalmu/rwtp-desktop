package app.controller.transaksi;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TransaksiController implements Initializable {

    public AnchorPane contentPane;
    public AnchorPane content;
    public AnchorPane transaksiPane;
    public AnchorPane home;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        defaultMenu();
    }

    public void handlerAllTransaction(){
        try{
            home = FXMLLoader.load(getClass().getResource("/xml/transaksi/ListTransaksi.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add(home);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void handlerPaketMenu(ActionEvent actionEvent){
        try {
            transaksiPane = FXMLLoader.load(getClass().getResource("/xml/transaksi/store/PaketTransaksi.fxml"));
            content.getChildren().clear();
            content.getChildren().add(transaksiPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void handlerRentalMenu(ActionEvent actionEvent){
        try {
            transaksiPane = FXMLLoader.load(getClass().getResource("/xml/transaksi/store/RentalTransaksi.fxml"));
            content.getChildren().clear();
            content.getChildren().add(transaksiPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void handlerSewaMenu(ActionEvent actionEvent){
        defaultMenu();
    }

    public void defaultMenu(){
        try{
            transaksiPane = FXMLLoader.load(getClass().getResource("/xml/transaksi/store/SewaTransaksi.fxml"));
            content.getChildren().clear();
            content.getChildren().add(transaksiPane);
        }catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
