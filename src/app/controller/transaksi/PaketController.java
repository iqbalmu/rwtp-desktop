package app.controller.transaksi;

import app.dao.PaketDAO;
import app.model.Paket;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class PaketController {
    public TextField nama_pengirim;
    public TextField hp_pengirim;
    public TextField nama_penerima;
    public TextField alamat_penerima;
    public TextField hp_penerima;
    public TextField kuantitas;

    public void btnSaveHandler(ActionEvent actionEvent) {
        System.out.println("Store Data");

        String namaPengirim = nama_pengirim.getText();
        String hpPengirim = hp_pengirim.getText();
        String namaPenerima = nama_penerima.getText();
        String hpPenerima = hp_penerima.getText();
        String alamatPenerima = alamat_penerima.getText();
        int kuantitasTxt = Integer.parseInt(kuantitas.getText());

        if (namaPengirim.isEmpty() || hpPengirim.isEmpty() || namaPenerima.isEmpty() || hpPenerima.isEmpty() || alamatPenerima.isEmpty() || kuantitasTxt < 1){
            System.out.println("Lengkapi Data Paket");
        }else{
            PaketDAO paketDAO = new PaketDAO();
            paketDAO.addData(new Paket("-", namaPengirim, hpPengirim, namaPenerima, hpPenerima, alamatPenerima, kuantitasTxt,"-"));
        }
    }
}
