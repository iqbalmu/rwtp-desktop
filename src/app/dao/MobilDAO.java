package app.dao;

import app.model.Mobil;
import app.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MobilDAO implements daoInterface<Mobil>{

    @Override
    public int addData(Mobil data) {
        int result = 0;
        try {
            String query = "INSERT INTO mobil(nopol, jenis_mobil, kelas, status, id_sopir) VALUE (?,?,?,?,?)";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getNopol());
            ps.setString(2, data.getJenis());
            ps.setString(3, data.getKelas());
            ps.setString(4, data.getStatus());
            ps.setInt(5, data.getId_sopir());
            result = ps.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public int delData(Mobil data) {
        int result = 0;
        try {
            String query = "DELETE FROM mobil WHERE id_mobil=?";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1, data.getId());
            result = ps.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public int updateData(Mobil data) {
        int result = 0;
        try{
            String query = "UPDATE mobil SET nopol=?, jenis_mobil=?, kelas=?, status=?, id_sopir=? WHERE id_mobil=?";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getNopol());
            ps.setString(2, data.getJenis());
            ps.setString(3, data.getKelas());
            ps.setString(4, data.getStatus());
            ps.setInt(5, data.getId_sopir());
            ps.setInt(6, data.getId());
            result = ps.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public ObservableList<Mobil> showData() {
        ObservableList<Mobil> mList = FXCollections.observableArrayList();

        try{
            String query = "SELECT id_mobil, nopol, jenis_mobil, kelas, mobil.status, sopir.nama_sopir  \n" +
                    "FROM mobil JOIN sopir \n" +
                    "ON mobil.id_sopir = sopir.id_sopir;";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();

            while (res.next()){
                int id = res.getInt("id_mobil");
                String nopol = res.getString("nopol");
                String jenis = res.getString("jenis_mobil");
                String kelas = res.getString("kelas");
                String status = res.getString("mobil.status");
                String nama_sopir = res.getString("nama_sopir");

                Mobil m = new Mobil();
                m.setId(id);
                m.setNopol(nopol);
                m.setJenis(jenis);
                m.setKelas(kelas);
                m.setStatus(status);
                m.setNama_sopir(nama_sopir);
                mList.add(m);
            }

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return mList;
    }

    public ObservableList<Mobil> getMobilReady() {
        ObservableList<Mobil> data = FXCollections.observableArrayList();

        try{
            String query = "SELECT * FROM  mobil WHERE LOWER(status)='ready' ";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();

            while (res.next()){
                String nopol = res.getString("nopol");
                String status = res.getString("status");
                int id_sopir = res.getInt("id_sopir");

                Mobil m = new Mobil();
                m.setNopol(nopol);
                m.setStatus(status);
                m.setId_sopir(id_sopir);
                data.add(m);
            }

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return data;
    }

    public ObservableList<Mobil> getMobilAvailable(String kelas) {
        ObservableList<Mobil> data = FXCollections.observableArrayList();

        try{
            String query = "SELECT * FROM  mobil WHERE LOWER(kelas)=? AND LOWER(status)='ready' ";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, kelas);
            ResultSet res = ps.executeQuery();

            while (res.next()){
                int id = res.getInt("id_mobil");
                String nopol = res.getString("nopol");
                String jenis = res.getString("jenis_mobil");
                String mkelas = res.getString("kelas");
                String status = res.getString("status");
                int id_sopir = res.getInt("id_sopir");

                Mobil m = new Mobil(id, nopol, jenis, kelas, status, id_sopir);
                data.add(m);
            }

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return data;
    }

    public int updateStatus(String status, String nopol){
        int result = 0;

        try{
            String query = "UPDATE mobil SET status=? WHERE nopol=?";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, status);
            ps.setString(2, nopol);
            result = ps.executeUpdate();
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return result;
    }
}
