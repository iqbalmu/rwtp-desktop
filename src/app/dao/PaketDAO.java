package app.dao;

import app.model.Mobil;
import app.model.Paket;
import app.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PaketDAO implements daoInterface<Paket> {
    @Override
    public int addData(Paket data) {
        int result = 0;
        try(Connection conn = JDBCConnection.getConnection()){

            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO log_transaksi(no_transaksi) VALUE (?)")){
                ps.setString(1, data.getNoTransaksi());
                ps.executeUpdate();
            }

            try(PreparedStatement ps = JDBCConnection.getConnection().prepareStatement("INSERT INTO paket(no_transaksi, nopol, nama_pengirim, hp_pengirim, nama_penerima, hp_penerima, tujuan, kuantitas, bayar, keterangan) VALUE (?,?,?,?,?,?,?,?,?,?)")) {

                ps.setString(1, data.getNoTransaksi());
                ps.setString(2, data.getNopol());
                ps.setString(3, data.getNamaPengirim());
                ps.setString(4, data.getHpPengirim());
                ps.setString(5, data.getNamaPenerima());
                ps.setString(6, data.getHpPenerima());
                ps.setString(7, data.getAlamatPenerima());
                ps.setInt(8, data.getKuantitas());
                ps.setInt(9, data.getBayar());
                ps.setString(10, data.getKeterangan());
                result = ps.executeUpdate();
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public int delData(Paket data) {
        return 0;
    }

    @Override
    public int updateData(Paket data) {
        return 0;
    }

    @Override
    public ObservableList<Paket> showData() {
        ObservableList<Paket> pList =FXCollections.observableArrayList();

        try{
            String query = "SELECT * FROM paket";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();

            while (res.next()){
                int id = res.getInt("id_paket");
                String nopol = res.getString("nopol");
                String pengirim = res.getString("nama_pengirim");
                String penerima = res.getString("nama_penerima");

                Paket paket = new Paket();
                paket.setId(id);
                paket.setNopol(nopol);
                paket.setNamaPenerima(penerima);
                paket.setNamaPengirim(pengirim);
                pList.add(paket);
            }

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return pList;
    }
}
