package app.dao;

import app.model.Mobil;
import app.model.transaksi.Sewa;
import app.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO sewa_transaksi(id_pelanggan, nopol, id_user, tanggal, jadwal, kursi, tujuan, no_transaksi, bayar, keterangan) " +
                    "VALUE (?,?,?,?,?,?,?,?,?,?)")) {
                ps.setString(1, data.getId_pelanggan());
                ps.setString(2, data.getNopol());
                ps.setInt(3, data.getId_user());
                ps.setTimestamp(4, data.getTimestamp());
                ps.setString(5, data.getJadwal());
                ps.setString(6, data.getKursi());
                ps.setString(7, data.getTujuan());
                ps.setString(8, data.getNo_transaksi());
                ps.setInt(9, data.getBayar());
                ps.setString(10, data.getKeterangan());
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
    public List<Sewa> showData() {
        return null;
    }
}
