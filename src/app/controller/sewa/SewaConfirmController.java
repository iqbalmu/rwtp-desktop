package app.controller.sewa;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class SewaConfirmController implements Initializable {
    public boolean status;
    public Label stat1;
    public Label stat2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setStatus(String status1, String status2) {
        stat1.setText(status1);
        stat2.setText(status2);
    }

    public void okeHandler(ActionEvent actionEvent) {
        status = true;
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    public void cancelHandler(ActionEvent actionEvent) {
        status = false;
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

}
