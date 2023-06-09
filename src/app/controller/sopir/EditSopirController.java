package app.controller.sopir;

import app.dao.SopirDAO;
import app.model.Sopir;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditSopirController implements Initializable {

    public TextField txtName;
    public TextField txtAlamat;
    public TextField txtPhone;
    public TextField txtSim;
    public TextField txtKtp;
    public ComboBox<String> cbStatus;
    public Button btnSimpan;

    public int id;

    private ObservableList<Sopir> list;

    public ObservableList<Sopir> getList() {
        return list;
    }

    public void setField(int id, String name, String alamat, String phone, String sim, String ktp, String status) {
        this.id = id;
        this.txtName.setText(name);
        this.txtAlamat.setText(alamat);
        this.txtPhone.setText(phone);
        this.txtSim.setText(sim);
        this.txtKtp.setText(ktp);
        this.cbStatus.setValue(status);
    }

    public void updateDataSopir(ActionEvent actionEvent) throws IOException {
        System.out.println("Update data");
        Sopir sopir = new Sopir(id, txtName.getText(),txtPhone.getText(), txtAlamat.getText(), txtKtp.getText(), txtSim.getText(), cbStatus.getSelectionModel().getSelectedItem());
        SopirDAO sDao = new SopirDAO();

        int result = sDao.updateData(sopir);
        if (result != 0){
            System.out.println("Update Success");
        }

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();

        list = sDao.showData();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> statuses = FXCollections.observableArrayList("Utama", "Cadangan");
        cbStatus.setItems(statuses);
    }
}
