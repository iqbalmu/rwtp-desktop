package app.controller.transaksi.paket;

import app.dao.PaketDAO;
import app.model.transaksi.Paket;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class TablePaketController implements Initializable {

    public TableView<Paket> tablePaket;
    public TableColumn<Paket, Integer> id;
    public TableColumn<Paket, String> pengirim;
    public TableColumn<Paket, String> penerima;
    public TableColumn<Paket, String> nopol;
    public TableColumn<Paket, Timestamp> date;
    public TableColumn<Paket, String> hpPengirim;
    public TableColumn<Paket, String> hpPenerima;
    public TableColumn<Paket, String> tujuan;
    public TableColumn<Paket, String> status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<Paket, Integer>("id"));
        date.setCellValueFactory(new PropertyValueFactory<Paket, Timestamp>("date"));
        nopol.setCellValueFactory(new PropertyValueFactory<Paket, String>("nopol"));
        pengirim.setCellValueFactory(new PropertyValueFactory<Paket, String>("namaPengirim"));
        hpPengirim.setCellValueFactory(new PropertyValueFactory<Paket, String>("hpPengirim"));
        penerima.setCellValueFactory(new PropertyValueFactory<Paket, String>("namaPenerima"));
        hpPenerima.setCellValueFactory(new PropertyValueFactory<Paket, String>("hpPenerima"));
        tujuan.setCellValueFactory(new PropertyValueFactory<Paket, String>("tujuan"));
        status.setCellValueFactory(new PropertyValueFactory<Paket, String>("status"));

        PaketDAO paketDAO = new PaketDAO();
        ObservableList<Paket> pakets = paketDAO.showData();
        tablePaket.setItems(pakets);
    }
}
