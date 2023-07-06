package app.controller.paket;

import app.controller.transaksi.paket.DetailPaketController;
import app.dao.PaketDAO;
import app.model.UserInfo;
import app.model.transaksi.Paket;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Objects;
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
        setTablePaket();
    }

    public void setTablePaket() {
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

    public void handlerUpdateStat(ActionEvent actionEvent) throws IOException {
        Paket selected = tablePaket.getSelectionModel().getSelectedItem();
        String status = selected.getStatus();
        int id = selected.getId();
        PaketDAO paketDAO = new PaketDAO();

        //confirm data
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/xml/paket/ConfirmUpdateStatus.fxml"));
        Parent root = loader.load();
        ConfirmController cc = loader.getController();

        System.out.println("awal stat: " + status);
        if(Objects.equals(status, "Dikirim")) {
            System.out.println("yuhu");
            cc.setStatus("Dikirim", "Sudah Diterima");
            status = "Sudah Diterima";
        }else{
            cc.setStatus("Sudah Diterima", "Dikirim");
            status = "Dikirim";
        }

        Scene scene = new Scene(root);
        stage.setTitle("Confirm Data");
        stage.setScene(scene);
        stage.showAndWait();

        if (cc.status){
            System.out.println("status: "+ status);
            paketDAO.updateStatus(status, id);
            setTablePaket();
        }
    }
}
