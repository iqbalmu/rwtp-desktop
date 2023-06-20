package app.controller.transaksi;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListTransaksiController implements Initializable {

    public AnchorPane contentPane;
    public AnchorPane home;
    public AnchorPane tablePane;

    public void handlerAddTransaksi(ActionEvent actionEvent) throws IOException {
        home = FXMLLoader.load(getClass().getResource("/xml/transaksi/Transaksi.fxml"));
        contentPane.getChildren().clear();
        contentPane.getChildren().add(home);
    }

    public void showSewaTable() throws IOException {
        home = FXMLLoader.load(getClass().getResource("/xml/transaksi/sewa/sewa_table.fxml"));
        tablePane.getChildren().clear();
        tablePane.getChildren().add(home);
    }

    public void showPaketTable() throws IOException {
        home = FXMLLoader.load(getClass().getResource("/xml/transaksi/paket/paket_table.fxml"));
        tablePane.getChildren().clear();
        tablePane.getChildren().add(home);
    }

    public void showRentalTable() throws IOException {
        home = FXMLLoader.load(getClass().getResource("/xml/transaksi/rental/rental_table.fxml"));
        tablePane.getChildren().clear();
        tablePane.getChildren().add(home);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showSewaTable();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
