package app.controller.transaksi.paket;

import app.dao.MobilDAO;
import app.dao.PaketDAO;
import app.model.Mobil;
import app.model.transaksi.Paket;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.UUID;

public class PaketController implements Initializable {
    public TextField nama_pengirim;
    public TextField hp_pengirim;
    public TextField nama_penerima;
    public TextField alamat_penerima;
    public TextField hp_penerima;
    public TextField kuantitas;
    public ComboBox<Mobil> cbMobil;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbMobil.setItems(getMobil());
    }

    public void btnSaveHandler(ActionEvent actionEvent) {
        System.out.println("Store Data");

        String noTransaksi = UUID.randomUUID().toString();
        String nopol = cbMobil.getSelectionModel().getSelectedItem().toString();
        String namaPengirim = nama_pengirim.getText();
        String hpPengirim = hp_pengirim.getText();
        String namaPenerima = nama_penerima.getText();
        String hpPenerima = hp_penerima.getText();
        String alamatPenerima = alamat_penerima.getText();
        int kuantitasTxt = Integer.parseInt(kuantitas.getText());
        int bayar = 30000 * kuantitasTxt; // untuk harga nanti dinamis dari database tabel harga
        String keterangan = "";
        Timestamp date = new Timestamp(new Date().getTime());

        if (namaPengirim.isEmpty() || hpPengirim.isEmpty() || namaPenerima.isEmpty() || hpPenerima.isEmpty() || alamatPenerima.isEmpty() || kuantitasTxt < 1){
            System.out.println("Lengkapi Data Paket");
        }else{
            PaketDAO paketDAO = new PaketDAO();
            paketDAO.addData(new Paket(noTransaksi, nopol, namaPengirim, hpPengirim, namaPenerima,hpPenerima, alamatPenerima, kuantitasTxt, bayar, keterangan, date));
        }
    }

    public ObservableList<Mobil> getMobil() {
        ObservableList<Mobil> mobils;
        MobilDAO mobilDAO = new MobilDAO();
        mobils = mobilDAO.getMobilReady();
        return mobils;
    }

}
