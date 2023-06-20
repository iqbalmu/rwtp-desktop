package app.controller.transaksi.rental;

import app.dao.RentalDAO;
import app.model.transaksi.Rental;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class TableRentalController implements Initializable {
    public TableView<Rental> tableRental;
    public TableColumn<Rental, Timestamp> date;
    public TableColumn<Rental, String> noTransaksi;
    public TableColumn<Rental, String> nama;
    public TableColumn<Rental, String> nopol;
    public TableColumn<Rental, Integer> lamaRental;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date.setCellValueFactory(new PropertyValueFactory<Rental, Timestamp>("date"));
        noTransaksi.setCellValueFactory(new PropertyValueFactory<Rental, String>("no_transaksi"));
        nama.setCellValueFactory(new PropertyValueFactory<Rental, String >("id_pelanggan"));
        nopol.setCellValueFactory(new PropertyValueFactory<Rental, String>("nopol"));
        lamaRental.setCellValueFactory(new PropertyValueFactory<Rental, Integer>("lama_rental"));

        RentalDAO rentalDAO = new RentalDAO();
        ObservableList<Rental> rentals = rentalDAO.showData();
        tableRental.setItems(rentals);
    }
}
