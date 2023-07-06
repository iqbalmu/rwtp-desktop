package app.controller.rental;

import app.model.Mobil;
import app.model.transaksi.Rental;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ReturnFormController {

    public Label txtPinjamDate;
    public Label txtNopol;
    public Label txtBalikDate;
    public Label txtBiaya;
    public Label txtTerlambat;
    public Label txtDenda;
    public TextArea taKeterangan;

    public boolean status;
    public Label txtTotal;

    private ObservableList<Rental> list;

    public ObservableList<Rental> getList() {
        return list;
    }

    public void setField( String txtNopol,String txtPinjamDate,  String txtBalikDate, String txtBiaya, String txtTerlambat, String txtDenda, String taKeterangan, String txTotal) {
        this.txtNopol.setText(txtNopol);
        this.txtPinjamDate.setText(txtPinjamDate);
        this.txtBalikDate.setText(txtBalikDate);
        this.txtBiaya.setText(txtBiaya);
        this.txtTerlambat.setText(txtTerlambat);
        this.txtDenda.setText(txtDenda);
        this.taKeterangan.setText(taKeterangan);
        this.txtTotal.setText(txTotal);
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
