package app.model.transaksi;

import java.sql.Timestamp;

public class Paket {
    private int id;
    private String nopol;
    private String noTransaksi;
    private String namaPengirim;
    private String hpPengirim;
    private String namaPenerima;
    private String hpPenerima;
    private String tujuan;
    private int kuantitas;
    private int bayar;
    private String status;
    private Timestamp date;

    public Paket(String noTransaksi, String nopol, String namaPengirim, String hpPengirim, String namaPenerima, String hpPenerima, String tujuan, int kuantitas, int bayar, String status, Timestamp date) {
        this.noTransaksi = noTransaksi;
        this.nopol = nopol;
        this.namaPengirim = namaPengirim;
        this.hpPengirim = hpPengirim;
        this.namaPenerima = namaPenerima;
        this.hpPenerima = hpPenerima;
        this.tujuan = tujuan;
        this.kuantitas = kuantitas;
        this.bayar = bayar;
        this.status = status;
        this.date = date;
    }

    public Paket() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public String getNamaPengirim() {
        return namaPengirim;
    }

    public void setNamaPengirim(String namaPengirim) {
        this.namaPengirim = namaPengirim;
    }

    public String getHpPengirim() {
        return hpPengirim;
    }

    public void setHpPengirim(String hpPengirim) {
        this.hpPengirim = hpPengirim;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public String getHpPenerima() {
        return hpPenerima;
    }

    public void setHpPenerima(String hpPenerima) {
        this.hpPenerima = hpPenerima;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public int getKuantitas() {
        return kuantitas;
    }

    public void setKuantitas(int kuantitas) {
        this.kuantitas = kuantitas;
    }

    public int getBayar() {
        return bayar;
    }

    public void setBayar(int bayar) {
        this.bayar = bayar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
