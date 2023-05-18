package app.dao;

import app.model.Pelanggan;
import app.model.Sopir;
import app.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PelangganDAO implements daoInterface<Pelanggan> {

    @Override
    public int addData(Pelanggan data) {
        int result = 0;
        try {
            String query = "INSERT INTO pelanggan(nama, no_telp, kategori, alamat, isMember) VALUE (?,?,?,?,?)";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getNama());
            ps.setString(2, data.getTelp());
            ps.setString(3, data.getKategori());
            ps.setString(4, data.getAlamat());
            ps.setString(5, data.getIsMember());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public int delData(Pelanggan data) {
        int result = 0;

        try {
            String query = "DELETE FROM pelanggan WHERE id_pelanggan=?";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1, data.getId());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

    @Override
    public int updateData(Pelanggan data) {
        int result = 0;
//
        try {
            String query = "UPDATE pelanggan SET nama=?, no_telp=?, kategori=?, alamat=?, isMember=? WHERE id_pelanggan=?";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getNama());
            ps.setString(2, data.getTelp());
            ps.setString(3, data.getKategori());
            ps.setString(4, data.getAlamat());
            ps.setString(5, data.getIsMember());
            ps.setInt(6, data.getId());

            result = ps.executeUpdate();

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
//
        return result;
    }

    @Override
    public ObservableList<Pelanggan> showData() {
        ObservableList<Pelanggan> pList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM pelanggan";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();

            while (res.next()) {
                int id = res.getInt("id_pelanggan");
                String nama = res.getString("nama");
                String telp = res.getString("no_telp");
                String kategori = res.getString("kategori");
                String alamat = res.getString("alamat");
                String isMember = res.getString("isMember");

                if (isMember == null || isMember.equals("")) {
                    isMember = "-";
                }
//                else {
//                    isMember = "Yes";
//                }

                Pelanggan p = new Pelanggan(id, nama, telp, kategori, alamat, isMember);
                pList.add(p);
            }

        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }


        return pList;
    }
}

