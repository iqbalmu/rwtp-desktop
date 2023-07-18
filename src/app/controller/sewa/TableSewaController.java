package app.controller.sewa;

import app.controller.paket.ConfirmController;
import app.dao.PaketDAO;
import app.dao.SewaDAO;
import app.model.transaksi.Paket;
import app.model.transaksi.Sewa;
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
import java.util.Date;
import java.util.Objects;
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
    public TableColumn<Sewa, Integer> biaya;
    public TableColumn<Sewa, String> status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableSewa();
    }

    public void setTableSewa(){
        date.setCellValueFactory(new PropertyValueFactory<Sewa, Timestamp>("date"));
        no_transaksi.setCellValueFactory(new PropertyValueFactory<Sewa, String>("no_transaksi"));
        nama_pelanggan.setCellValueFactory(new PropertyValueFactory<Sewa, String>("id_pelanggan"));
        nopol.setCellValueFactory(new PropertyValueFactory<Sewa, String>("nopol"));
        sewaDate.setCellValueFactory(new PropertyValueFactory<Sewa, Date>("sewa_date"));
        jadwal.setCellValueFactory(new PropertyValueFactory<Sewa, String>("jadwal"));
        kursi.setCellValueFactory(new PropertyValueFactory<Sewa, String>("kursi"));
        tujuan.setCellValueFactory(new PropertyValueFactory<Sewa, String>("tujuan"));
        biaya.setCellValueFactory(new PropertyValueFactory<Sewa, Integer>("harga"));
        status.setCellValueFactory(new PropertyValueFactory<Sewa, String>("keterangan"));

        SewaDAO sewaDAO = new SewaDAO();
        ObservableList<Sewa> sewas = sewaDAO.showData();
        tableSewa.setItems(sewas);
    }

    public void handlerUpdateStat(ActionEvent actionEvent) throws IOException {
        Sewa selected = tableSewa.getSelectionModel().getSelectedItem();
        String status = selected.getKeterangan();
        String noTrans = selected.getNo_transaksi();
        SewaDAO sewaDAO = new SewaDAO();

        //confirm data
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/xml/sewa/ConfirmUpdateStatus.fxml"));
        Parent root = loader.load();
        SewaConfirmController scc = loader.getController();

        System.out.println("awal stat: " + status);
        if(Objects.equals(status, "Perjalanan")) {
            scc.setStatus("Perjalanan", "Selesai");
            status = "Selesai";
        }else{
            scc.setStatus("Selesai", "Perjalanan");
            status = "Perjalanan";
        }

        Scene scene = new Scene(root);
        stage.setTitle("Confirm Data");
        stage.setScene(scene);
        stage.showAndWait();

        if (scc.status){
            System.out.println("status: "+ status);
            sewaDAO.updateStatus(status, noTrans);
            setTableSewa();
        }
    }
}
