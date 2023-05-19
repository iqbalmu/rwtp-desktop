package app.controller.transaksi;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ListTransaksiController {

    public AnchorPane contentPane;
    public AnchorPane home;

    public void handlerAddTransaksi(ActionEvent actionEvent) throws IOException {
        home = FXMLLoader.load(getClass().getResource("/xml/transaksi/Transaksi.fxml"));
        contentPane.getChildren().clear();
        contentPane.getChildren().add(home);
    }
}
