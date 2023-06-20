package app.model.transaksi;

import java.sql.Timestamp;

public class Rental {

    private int id_rental;
    private String id_pelanggan;
    private int id_user;
    private int bayar;
    private String nopol;
    private int lama_rental;
    private String keterangan;
    private String no_transaksi;
    private Timestamp date;

    public Rental() {
    }

    public Rental(int id_rental, String id_pelanggan, int id_user, int bayar, String nopol, int lama_rental, String keterangan, String no_transaksi, Timestamp date) {
        this.id_rental = id_rental;
        this.id_pelanggan = id_pelanggan;
        this.id_user = id_user;
        this.bayar = bayar;
        this.nopol = nopol;
        this.lama_rental = lama_rental;
        this.keterangan = keterangan;
        this.no_transaksi = no_transaksi;
        this.date = date;
    }

    public int getId_rental() {
        return id_rental;
    }

    public void setId_rental(int id_rental) {
        this.id_rental = id_rental;
    }

    public String getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(String id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getBayar() {
        return bayar;
    }

    public void setBayar(int bayar) {
        this.bayar = bayar;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public int getLama_rental() {
        return lama_rental;
    }

    public void setLama_rental(int lama_rental) {
        this.lama_rental = lama_rental;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNo_transaksi() {
        return no_transaksi;
    }

    public void setNo_transaksi(String no_transaksi) {
        this.no_transaksi = no_transaksi;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
