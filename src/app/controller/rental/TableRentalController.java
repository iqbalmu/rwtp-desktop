package app.controller.rental;

import app.controller.mobil.EditMobilController;
import app.dao.MobilDAO;
import app.dao.RentalDAO;
import app.model.transaksi.Rental;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class TableRentalController implements Initializable {
    public TableView<Rental> tableRental;
    public TableColumn<Rental, Timestamp> date;
    public TableColumn<Rental, String> noTransaksi;
    public TableColumn<Rental, String> nama;
    public TableColumn<Rental, String> nopol;
    public TableColumn<Rental, Date> rentalDate;
    public TableColumn<Rental, Date> returnDate;
    public TableColumn<Rental, Date> actualReturn;
    public TableColumn<Rental, Integer> biaya;
    public TableColumn<Rental, Integer> denda;
    public TableColumn<Rental, String> keterangan;

    RentalDAO rentalDAO = new RentalDAO();
    ObservableList<Rental> rentals;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableRental();
    }

    public void setTableRental() {
        date.setCellValueFactory(new PropertyValueFactory<Rental, Timestamp>("date"));
        noTransaksi.setCellValueFactory(new PropertyValueFactory<Rental, String>("no_transaksi"));
        nama.setCellValueFactory(new PropertyValueFactory<Rental, String >("id_pelanggan"));
        nopol.setCellValueFactory(new PropertyValueFactory<Rental, String>("nopol"));
        rentalDate.setCellValueFactory(new PropertyValueFactory<Rental, Date>("rental_date"));
        returnDate.setCellValueFactory(new PropertyValueFactory<Rental, Date>("return_date"));

        actualReturn.setCellValueFactory(new PropertyValueFactory<Rental, Date>("actual_return"));
        biaya.setCellValueFactory(new PropertyValueFactory<Rental, Integer>("bayar"));
        denda.setCellValueFactory(new PropertyValueFactory<Rental, Integer>("denda"));
        keterangan.setCellValueFactory(new PropertyValueFactory<Rental, String>("keterangan"));

        MobilDAO mobilDAO = new MobilDAO();
        mobilDAO.checkUpdateStatusRental("Rental");

        rentals = rentalDAO.showData();
        tableRental.setItems(rentals);
    }

    public void handlerReturn(ActionEvent actionEvent) throws IOException {
        Rental selected = tableRental.getSelectionModel().getSelectedItem();
        String notransaksi = selected.getNo_transaksi();
        String nopol = selected.getNopol();

        Rental data = rentals.get(0);
        long telatMs =  (new Date().getTime() - data.getReturn_date().getTime()) > 0 ? (new Date().getTime() - data.getReturn_date().getTime()) : 0;
        long telatDy = TimeUnit.DAYS.convert(telatMs, TimeUnit.MILLISECONDS);

        String bayar = String.valueOf( (int) data.getBayar());
        String telat = String.valueOf(telatDy);
        int denda = (int) (telatDy * 25_000);
        int total = (int) (data.getBayar() + denda);
        java.sql.Date actualReturn = java.sql.Date.valueOf(LocalDate.now());

        Stage stage = new Stage();

        if (selected != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/xml/rental/ReturnForm.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            ReturnFormController rfc = loader.getController();
            rfc.setField(
                    selected.getNopol(),
                    selected.getRental_date().toString(),
                    selected.getReturn_date().toString(),
                    bayar,
                    telat,
                    String.valueOf(denda),
                    "",
                    String.valueOf(total)
            );

            stage.setTitle("Form Pengembalian");
            stage.setScene(scene);
            stage.showAndWait();

            // update data select
            rentalDAO.updateReturn(String.valueOf(denda), rfc.taKeterangan.getText(), actualReturn, notransaksi);

            // update mobil
            MobilDAO mobilDAO = new MobilDAO();
            mobilDAO.updateStatus("Ready", nopol);

            //update table
            setTableRental();
        }else{
            stage.setTitle("Pengembalian");
            Label label = new Label();
            label.setText("Data Belum dipilih");

            Scene scene = new Scene(label);
            stage.setScene(scene);
            stage.show();
        }
    }
}