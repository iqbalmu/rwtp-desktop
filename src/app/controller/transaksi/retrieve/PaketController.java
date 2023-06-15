package app.controller.transaksi.retrieve;

import app.dao.PaketDAO;
import app.model.Paket;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PaketController implements Initializable {

    public TableView<Paket> tablePaket;
    public TableColumn<Paket, Integer> id;
    public TableColumn<Paket, String> pengirim;
    public TableColumn<Paket, String> penerima;
    public TableColumn<Paket, String> nopol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<Paket, Integer>("id"));
        pengirim.setCellValueFactory(new PropertyValueFactory<Paket, String>("namaPengirim"));
        penerima.setCellValueFactory(new PropertyValueFactory<Paket, String>("namaPenerima"));
        nopol.setCellValueFactory(new PropertyValueFactory<Paket, String>("nopol"));

        PaketDAO paketDAO = new PaketDAO();
        ObservableList<Paket> pakets = paketDAO.showData();
        tablePaket.setItems(pakets);
    }
}
