package app.dao;

import app.model.Sopir;
import app.model.transaksi.Rental;
import app.utility.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RentalDAO implements daoInterface<Rental> {

    @Override
    public int addData(Rental data) {
        int result = 0;

        try(Connection conn = JDBCConnection.getConnection()) {

            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO log_transaksi(no_transaksi) VALUE (?)")){
                ps.setString(1, data.getNo_transaksi());
                ps.executeUpdate();
            }

            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO rental_transaksi(id_pelanggan, nopol, id_user, lama_rental, bayar, keterangan, no_transaksi, timestamp) " +
                    "VALUE (?,?,?,?,?,?,?,?)")) {
                ps.setString(1, data.getId_pelanggan());
                ps.setString(2, data.getNopol());
                ps.setInt(3, data.getId_user());
                ps.setInt(4, data.getLama_rental());
                ps.setInt(5, data.getBayar());
                ps.setString(6, data.getKeterangan());
                ps.setString(7, data.getNo_transaksi());
                ps.setTimestamp(8, data.getTimestamp());
                ps.executeUpdate();
            }

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

    @Override
    public int delData(Rental data) {
        return 0;
    }

    @Override
    public int updateData(Rental data) {
        return 0;
    }

    @Override
    public List<Rental> showData() {
        return null;
    }
}
