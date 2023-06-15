import app.model.transaksi.Sewa;
import app.utility.JDBCConnection;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class getKursiTest {
    @Test
    void test(){
        String data = null;

        try{
            String query = "SELECT kursi FROM  sewa_transaksi WHERE nopol=?";
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, "OC 2808 PU");
            ResultSet res = ps.executeQuery();

            while (res.next()){
                data = res.getString("kursi");
            }

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println(data);
    }
}
