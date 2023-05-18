package app.utility;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ChangeScene {

    public void set(AnchorPane view ,String fxml) {
        try {
            view = FXMLLoader.load(getClass().getResource("/xml/transaksi/transaksi.fxml"));
//            setNode(home);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    };
}
