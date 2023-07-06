package app.model.transaksi;

import java.sql.Timestamp;
import java.sql.Date;

public class Rental {

    private int id_rental;
    private String id_pelanggan;
    private int id_user;
    private double bayar;

    private double denda;
    private String nopol;
    private int lama_rental;
    private String keterangan;
    private String no_transaksi;
    private Timestamp date;
    private Date rental_date;
    private Date actual_return;

    private Date return_date;

    public Rental() {
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

    public double getBayar() {
        return bayar;
    }

    public void setBayar(double bayar) {
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

    public Date getRental_date() {
        return rental_date;
    }

    public void setRental_date(Date rental_date) {
        this.rental_date = rental_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public double getDenda() {
        return denda;
    }

    public void setDenda(double denda) {
        this.denda = denda;
    }

    public Date getActual_return() {
        return actual_return;
    }

    public void setActual_return(Date actual_return) {
        this.actual_return = actual_return;
    }
}
