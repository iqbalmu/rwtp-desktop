package app.dao;

import app.model.transaksi.Rental;
import app.model.transaksi.Sewa;
import app.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class SewaDAO implements daoInterface<Sewa> {
    @Override
    public int addData(Sewa data) {
        int result = 0;

        try(Connection conn = JDBCConnection.getConnection()) {

            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO log_transaksi(no_transaksi) VALUE (?)")){
                ps.setString(1, data.getNo_transaksi());
                ps.executeUpdate();
            }

            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO sewa_transaksi(id_pelanggan, nopol, id_user, `date`, jadwal, kursi, tujuan, no_transaksi, harga, keterangan, sewa_date) " +
                    "VALUE (?,?,?,?,?,?,?,?,?,?,?)")) {
                ps.setString(1, data.getId_pelanggan());
                ps.setString(2, data.getNopol());
                ps.setInt(3, data.getId_user());
                ps.setTimestamp(4, data.getDate());
                ps.setString(5, data.getJadwal());
                ps.setString(6, data.getKursi());
                ps.setString(7, data.getTujuan());
                ps.setString(8, data.getNo_transaksi());
                ps.setDouble(9, data.getHarga());
                ps.setString(10, data.getKeterangan());
                ps.setDate(11, data.getSewa_date());
                ps.executeUpdate();
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

    public List<String> getKursiAvailable(String nopol) {
        List<String> data = null;

        try{
            String query = "SELECT kursi FROM  sewa_transaksi WHERE nopol=?";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, nopol);
            ResultSet res = ps.executeQuery();

            while (res.next()){
                String kursis = res.getString("kursi").replace("[", "").replace("]","");
                data = Arrays.asList(kursis.split("\\s*,\\s*"));
            }

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return data;
    }

    @Override
    public int delData(Sewa data) {
        return 0;
    }

    @Override
    public int updateData(Sewa data) {
        return 0;
    }

    @Override
    public ObservableList<Sewa> showData() {
        ObservableList<Sewa> lSewa = FXCollections.observableArrayList();
        try{
            String query = "SELECT `date`, no_transaksi, nopol, jadwal, kursi, tujuan, pelanggan.nama FROM sewa_transaksi st JOIN pelanggan ON st.id_pelanggan = pelanggan.id_pelanggan;";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();

            while (res.next()){
                Timestamp date = res.getTimestamp("date");
                String no_transaksi = res.getString("no_transaksi");
                String nopol = res.getString("nopol");
                String nama = res.getString("nama");
                String jadwal = res.getString("jadwal");
                String kursi = res.getString("kursi");
                String tujuan = res.getString("tujuan");

                Sewa sewa = new Sewa();
                sewa.setDate(date);
                sewa.setNo_transaksi(no_transaksi);
                sewa.setNopol(nopol);
                sewa.setId_pelanggan(nama);
                sewa.setJadwal(jadwal);
                sewa.setKursi(kursi);
                sewa.setTujuan(tujuan);

                lSewa.add(sewa);
            }

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return lSewa;
    }
}
