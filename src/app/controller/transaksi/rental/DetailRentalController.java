package app.controller.transaksi.rental;

import javafx.scene.control.Label;

public class DetailRentalController {
    public Label txtNama;
    public Label txtPinjamDate;
    public Label txtKelas;
    public Label txtNopol;
    public Label txtNik;
    public Label txtBalikDate;
    public Label txtBiaya;

    public void setField(String nama, String nik, String kelas, String nopol, String pinjamDate, String balikDate, String biaya){
        txtNama.setText(nama);
        txtNik.setText(nik);
        txtKelas.setText(kelas);
        txtNopol.setText(nopol);
        txtPinjamDate.setText(pinjamDate);
        txtBalikDate.setText(balikDate);
        txtBiaya.setText(biaya);
    }

}
