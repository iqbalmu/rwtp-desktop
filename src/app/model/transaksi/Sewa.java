package app.model.transaksi;

import java.sql.Date;
import java.sql.Timestamp;

public class Sewa {
    private int id_sewa;
    private String id_pelanggan;
    private String nopol;
    private int id_user;
    private Timestamp date;
    private Date sewa_date;
    private String jadwal;
    private String kursi;
    private String tujuan;
    private String no_transaksi;
    private double harga;
    private String keterangan;

    public Sewa(String id_pelanggan, String nopol, int id_user, Timestamp date, Date sewa_date, String jadwal, String kursi, String tujuan, String no_transaksi, double harga, String keterangan) {
        this.id_pelanggan = id_pelanggan;
        this.nopol = nopol;
        this.id_user = id_user;
        this.date = date;
        this.sewa_date = sewa_date;
        this.jadwal = jadwal;
        this.kursi = kursi;
        this.tujuan = tujuan;
        this.no_transaksi = no_transaksi;
        this.harga = harga;
        this.keterangan = keterangan;
    }

    public Sewa() {

    }

    public int getId_sewa() {
        return id_sewa;
    }

    public void setId_sewa(int id_sewa) {
        this.id_sewa = id_sewa;
    }

    public String getId_pelanggan() {
        return id_pelanggan;
    }

    public void setId_pelanggan(String id_pelanggan) {
        this.id_pelanggan = id_pelanggan;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Date getSewa_date() {
        return sewa_date;
    }

    public void setSewa_date(Date sewa_date) {
        this.sewa_date = sewa_date;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    public String getKursi() {
        return kursi;
    }

    public void setKursi(String kursi) {
        this.kursi = kursi;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getNo_transaksi() {
        return no_transaksi;
    }

    public void setNo_transaksi(String no_transaksi) {
        this.no_transaksi = no_transaksi;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}
