package app.dao;

import app.model.Paket;
import app.utility.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PaketDAO implements daoInterface<Paket> {
    @Override
    public int addData(Paket data) {
        int result = 0;
        try {
            String query = "INSERT INTO paket(nopol, nama_pengirim, hp_pengirim, nama_penerima, hp_penerima, tujuan, kuantitas, status) VALUE (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getNopol());
            ps.setString(2, data.getNamaPengirim());
            ps.setString(3, data.getHpPengirim());
            ps.setString(4, data.getNamaPenerima());
            ps.setString(5, data.getHpPenerima());
            ps.setString(6, data.getAlamatPenerima());
            ps.setInt(7, data.getKuantitas());
            ps.setString(8, data.getStatus());
            result = ps.executeUpdate();
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
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
    public List<Paket> showData() {
        return null;
    }
}
