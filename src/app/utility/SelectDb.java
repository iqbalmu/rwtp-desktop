package app.utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectDb {
//    public static ObservableList<String> where(String table, String field) {
//      ObservableList<String> data = FXCollections.observableArrayList();
//        try {
//            String query = "SELECT * FROM " + table;
//            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
//            ResultSet res = ps.executeQuery();
//            while (res.next()) {
//                data.add(res.getString(field));
//            }
//        }catch (SQLException ex){
//            System.out.println(ex.getMessage());
//        }
//      return data;
//    };

    public static ObservableList<String> where(String table, String field) {
        ObservableList<String> data = FXCollections.observableArrayList();
        try {
            String query = "SELECT "+field+" FROM " + table;
            PreparedStatement ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                data.add(res.getString(field));
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return data;
    }
}
