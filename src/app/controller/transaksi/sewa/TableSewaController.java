package app.controller.transaksi.sewa;

import app.dao.SewaDAO;
import app.model.transaksi.Sewa;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;

public class TableSewaController implements Initializable {

    public TableView<Sewa> tableSewa;
    public TableColumn<Sewa, String> no_transaksi;
    public TableColumn<Sewa, String> nama_pelanggan;
    public TableColumn<Sewa, String> nopol;
    public TableColumn<Sewa, String> jadwal;
    public TableColumn<Sewa, String> kursi;
    public TableColumn<Sewa, String> tujuan;
    public TableColumn<Sewa, Timestamp> date;
    public TableColumn<Sewa, Date> sewaDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date.setCellValueFactory(new PropertyValueFactory<Sewa, Timestamp>("date"));
        no_transaksi.setCellValueFactory(new PropertyValueFactory<Sewa, String>("no_transaksi"));
        nama_pelanggan.setCellValueFactory(new PropertyValueFactory<Sewa, String>("id_pelanggan"));
        nopol.setCellValueFactory(new PropertyValueFactory<Sewa, String>("nopol"));
        sewaDate.setCellValueFactory(new PropertyValueFactory<Sewa, Date>("sewa_date"));
        jadwal.setCellValueFactory(new PropertyValueFactory<Sewa, String>("jadwal"));
        kursi.setCellValueFactory(new PropertyValueFactory<Sewa, String>("kursi"));
        tujuan.setCellValueFactory(new PropertyValueFactory<Sewa, String>("tujuan"));

        SewaDAO sewaDAO = new SewaDAO();
        ObservableList<Sewa> sewas = sewaDAO.showData();
        tableSewa.setItems(sewas);
    }

}
