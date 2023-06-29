package app.controller.transaksi.sewa;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DetailSewaController {
    public Label txtUser;
    public Label txtDateTransaksi;
    public Label txtNama;
    public Label txtSewaDate;
    public Label txtKelas;
    public Label txtNopol;
    public Label txtPhone;
    public Label txtKursi;
    public Label txtAlamat;
    public Label txtJadwal;
    public Label txtHarga;

    protected boolean status;

    public void setField(String user, String date, String nama, String sewaDate, String kelas, String nopol, String phone, String kursi, String harga, String alamat, String jadwal) {
        txtUser.setText(user);
        txtDateTransaksi.setText(date);
        txtNama.setText(nama);
        txtSewaDate.setText(sewaDate);
        txtKelas.setText(kelas);
        txtNopol.setText(nopol);
        txtPhone.setText(phone);
        txtKursi.setText(kursi);
        txtHarga.setText(harga);
        txtAlamat.setText(alamat);
        txtJadwal.setText(jadwal);
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
