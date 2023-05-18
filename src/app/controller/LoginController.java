package app.controller;

import app.Main;
import app.model.UserInfo;
import app.utility.JDBCConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public TextField usernameTxt;
    public PasswordField passwordTxt;
    public Button loginBtn;
    public Label wrongLogin;

    public void login(ActionEvent actionEvent) throws IOException {
        checkLogin();
    }

    public void checkLogin() throws IOException {
        Main m = new Main();
        if (usernameTxt.getText().isEmpty() && usernameTxt.getText().isEmpty()){
            wrongLogin.setText("Data login tidak boleh kosong");
        }else {
            try {
                String query = "SELECT * FROM user WHERE username=? AND password=?";

                PreparedStatement ps;
                ps = JDBCConnection.getConnection().prepareStatement(query);
                ps.setString(1, usernameTxt.getText());
                ps.setString(2, passwordTxt.getText());
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    m.changeScene("view/xml/main2.fxml");
                    UserInfo.username = usernameTxt.getText();
                } else {
                    wrongLogin.setText("Data login tidak sesuai!!");
                }

            } catch (SQLException ext) {
                System.out.println(ext.getMessage());
            }
        }
    }

}
