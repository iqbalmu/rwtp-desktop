package app.dao;

import app.model.Sopir;
import app.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SopirDAO implements daoInterface<Sopir> {

    @Override
    public int addData(Sopir data) {
        int result = 0;
        try {
            String query = "INSERT INTO sopir(nama_sopir, phone, alamat, ktp, sim) VALUE (?,?,?,?,?)";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getNama());
            ps.setString(2, data.getPhone());
            ps.setString(3, data.getAlamat());
            ps.setString(4, data.getKtp());
            ps.setString(5, data.getSim());
            result = ps.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public int delData(Sopir data) {
        int result = 0;

        try {
            String query = "DELETE FROM sopir WHERE id_sopir=?";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1, data.getId_sopir());
            result = ps.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

    @Override
    public int updateData(Sopir data) {
        int result = 0;

        try{
            String query = "UPDATE sopir SET nama_sopir=?, phone=?, alamat=?, ktp=?, sim=? WHERE id_sopir=?";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getNama());
            ps.setString(2, data.getPhone());
            ps.setString(3, data.getAlamat());
            ps.setString(4, data.getKtp());
            ps.setString(5, data.getSim());
            ps.setInt(6, data.getId_sopir());

            result = ps.executeUpdate();

        }catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        return result;
    }

    @Override
    public ObservableList<Sopir> showData() {
        ObservableList<Sopir> sList = FXCollections.observableArrayList();

        try{
            String query = "SELECT * FROM sopir";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();

            while (res.next()){
                int id = res.getInt("id_sopir");
                String nama = res.getString("nama_sopir");
                String phone = res.getString("phone");
                String alamat = res.getString("alamat");
                String ktp = res.getString("ktp");
                String sim = res.getString("sim");

                Sopir s = new Sopir(id, nama, phone, alamat, ktp, sim);
                sList.add(s);
            }

        }catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }


        return sList;
    }
}

