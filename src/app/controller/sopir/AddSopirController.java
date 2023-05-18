package app.controller.sopir;

import app.dao.SopirDAO;
import app.model.Sopir;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddSopirController {

    public TextField txtName;
    public TextField txtAlamat;
    public TextField txtPhone;
    public TextField txtSim;
    public TextField txtKtp;

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

        if(name.isEmpty() || phone.isEmpty() || address.isEmpty() || ktp.isEmpty() || sim.isEmpty()){
            System.out.println("field kosong");
        }else{
            SopirDAO dao = new SopirDAO();
            dao.addData(new Sopir(name,phone,address,ktp,sim));
            sList = dao.showData();
        }

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();

    }
}
