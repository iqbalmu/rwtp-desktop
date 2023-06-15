package app.controller.sopir;

import app.dao.SopirDAO;
import app.model.Sopir;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddSopirController implements Initializable {

    public TextField txtName;
    public TextField txtAlamat;
    public TextField txtPhone;
    public TextField txtSim;
    public TextField txtKtp;
    public ComboBox<String> cbStatus;

    private ObservableList<Sopir> sList;

    public ObservableList<Sopir> getsList() {
        return sList;
    }

    public void insertDataSopir(ActionEvent actionEvent) {
        String name = txtName.getText();
        String phone = txtPhone.getText();
        String address = txtAlamat.getText();
        String ktp = txtKtp.getText();
        String sim = txtSim.getText();
        String status = cbStatus.getSelectionModel().getSelectedItem();

        if(name.isEmpty() || phone.isEmpty() || address.isEmpty() || ktp.isEmpty() || sim.isEmpty() || status.isEmpty()){
            System.out.println("field kosong");
        }else{
            SopirDAO dao = new SopirDAO();
            dao.addData(new Sopir(name,phone,address,ktp,sim, status));
            sList = dao.showData();
        }

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> statuses = FXCollections.observableArrayList("Utama", "Cadangan");
        cbStatus.setItems(statuses);
    }
}
