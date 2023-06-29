package app.controller.transaksi.paket;

import app.utility.JDBCConnection;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetailPaketController {
    public Label txtUser;
    public Label txtDateTransaksi;
    public Label txtPengirim;
    public Label txtKuantitas;
    public Label txtAlamat;
    public Label txtPenerima;
    public Label txtPengirimTelp;
    public Label txtHarga;
    public Label txtPenerimaTelp;

    public boolean status;

    public void setField(String pengirim, String pgTelp, String penerima, String pnTelp, String alamat, String qnt, String harga, String user, String dateTrans){
        txtPengirim.setText(pengirim);
        txtPengirimTelp.setText(pgTelp);
        txtPenerima.setText(penerima);
        txtPenerimaTelp.setText(pnTelp);
        txtAlamat.setText(alamat);
        txtKuantitas.setText(qnt);
        txtHarga.setText(harga);
        txtUser.setText(user);
        txtDateTransaksi.setText(dateTrans);
    }

    public void okeHandler(ActionEvent actionEvent) {
        status = true;
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    public void cancelHandler(ActionEvent actionEvent) {
        status = false;
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

}
