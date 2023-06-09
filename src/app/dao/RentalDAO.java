package app.dao;
import app.model.transaksi.Rental;
import app.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class RentalDAO implements daoInterface<Rental> {

    @Override
    public int addData(Rental data) {
        int result = 0;

        try(Connection conn = JDBCConnection.getConnection()) {

            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO log_transaksi(no_transaksi) VALUE (?)")){
                ps.setString(1, data.getNo_transaksi());
                ps.executeUpdate();
            }

            try(PreparedStatement ps = conn.prepareStatement("INSERT INTO rental_transaksi(id_pelanggan, nopol, id_user, lama_rental, bayar, keterangan, no_transaksi, date, rental_date, return_date) " +
                    "VALUE (?,?,?,?,?,?,?,?,?,?)")) {
                ps.setString(1, data.getId_pelanggan());
                ps.setString(2, data.getNopol());
                ps.setInt(3, data.getId_user());
                ps.setInt(4, data.getLama_rental());
                ps.setDouble(5, data.getBayar());
                ps.setString(6, data.getKeterangan());
                ps.setString(7, data.getNo_transaksi());
                ps.setTimestamp(8, data.getDate());
                ps.setDate(9, data.getRental_date());
                ps.setDate(10, data.getReturn_date());
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
    public ObservableList<Rental> showData() {
        ObservableList<Rental> rList = FXCollections.observableArrayList();
        try{
            String query = "SELECT `date`, no_transaksi, nopol, rental_date, return_date, pelanggan.nama FROM rental_transaksi rt JOIN pelanggan ON rt.id_pelanggan = pelanggan.id_pelanggan;";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();

            while (res.next()){
                Timestamp date = res.getTimestamp("date");
                String no_transaksi = res.getString("no_transaksi");
                String nopol = res.getString("nopol");
                Date rental_date = res.getDate("rental_date");
                Date return_date = res.getDate("return_date");
                String nama = res.getString("nama");

                Rental rental = new Rental();
                rental.setDate(date);
                rental.setNo_transaksi(no_transaksi);
                rental.setNopol(nopol);
                rental.setRental_date(rental_date);
                rental.setReturn_date(return_date);
                rental.setId_pelanggan(nama);

                rList.add(rental);
            }

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return rList;
    }
}
