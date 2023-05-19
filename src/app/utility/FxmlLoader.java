package app.utility;

import app.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.net.URL;

public class FxmlLoader {
    private AnchorPane view;

    public AnchorPane getPage(String scene) {
        try{
            URL fileUrl = Main.class.getResource("view/scene/" + scene + ".fxml");
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("FXML file can't find");
            }

            view = new FXMLLoader().load(fileUrl);
        }catch (Exception ex) {
            System.out.println("No Page" + scene);
        }

        return view;
    }
}
