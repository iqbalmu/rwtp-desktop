package app.controller.transaksi;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ListTransaksiController {

    public AnchorPane contentPane;
    public AnchorPane home;
    public AnchorPane tablePane;

    public void handlerAddTransaksi(ActionEvent actionEvent) throws IOException {
        home = FXMLLoader.load(getClass().getResource("/xml/transaksi/Transaksi.fxml"));
        contentPane.getChildren().clear();
        contentPane.getChildren().add(home);
    }

    public void showSewaTable(ActionEvent actionEvent) throws IOException {
        home = FXMLLoader.load(getClass().getResource("/xml/transaksi/retrieve/sewa_table.fxml"));
        tablePane.getChildren().clear();
        tablePane.getChildren().add(home);
    }

    public void showPaketTable(ActionEvent actionEvent) throws IOException {
        home = FXMLLoader.load(getClass().getResource("/xml/transaksi/retrieve/paket_table.fxml"));
        tablePane.getChildren().clear();
        tablePane.getChildren().add(home);
    }

    public void showRentalTable(ActionEvent actionEvent) throws IOException {
        home = FXMLLoader.load(getClass().getResource("/xml/transaksi/retrieve/rental_table.fxml"));
        tablePane.getChildren().clear();
        tablePane.getChildren().add(home);
    }
}
