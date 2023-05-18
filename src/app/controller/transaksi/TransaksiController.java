package app.controller.transaksi;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    public void handlerAllTransaction(ActionEvent actionEvent){
        try{
            home = FXMLLoader.load(getClass().getResource("/xml/transaksi/ListTransaksi.fxml"));
            contentPane.getChildren().clear();
            contentPane.getChildren().add((Node) home);
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public void handlerPaketMenu(ActionEvent actionEvent){
        try {
            transaksiPane = FXMLLoader.load(getClass().getResource("/xml/transaksi/PaketTransaksi.fxml"));
            content.getChildren().clear();
            content.getChildren().add((Node) transaksiPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void handlerRentalMenu(ActionEvent actionEvent){
        try {
            transaksiPane = FXMLLoader.load(getClass().getResource("/xml/transaksi/RentalTransaksi.fxml"));
            content.getChildren().clear();
            content.getChildren().add((Node) transaksiPane);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void handlerSewaMenu(ActionEvent actionEvent){
        defaultMenu();
    }

    public void defaultMenu(){
        try{
            transaksiPane = FXMLLoader.load(getClass().getResource("/xml/transaksi/SewaTransaksi.fxml"));
            content.getChildren().clear();
            content.getChildren().add((Node) transaksiPane);
        }catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
