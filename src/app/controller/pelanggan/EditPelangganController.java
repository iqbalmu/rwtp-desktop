package app.controller.pelanggan;

import app.dao.PelangganDAO;
import app.dao.SopirDAO;
import app.model.Pelanggan;
import app.model.Sopir;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditPelangganController {

    public TextField txtName;
    public TextField txtAlamat;
    public TextField txtPhone;
    public TextField txtKategori;
    public TextField txtMember;
    public int id;

    public void setField(int id, String name, String alamat, String phone, String kategori, String member){
        this.id = id;
        this.txtName.setText(name);
        this.txtAlamat.setText(alamat);
        this.txtPhone.setText(phone);
        this.txtKategori.setText(kategori);
        this.txtMember.setText(member);
    }

    private ObservableList<Pelanggan> list;
    public ObservableList<Pelanggan> getList() {
        return list;
    }

    public void updateData(ActionEvent actionEvent) throws IOException {
        System.out.println("Update data");
        Pelanggan pelanggan = new Pelanggan(id, txtName.getText(), txtPhone.getText(),txtKategori.getText(),txtAlamat.getText(),txtMember.getText());
        PelangganDAO pDao = new PelangganDAO();

        int result = pDao.updateData(pelanggan);
        if (result != 0){
            System.out.println("Update Success");
        }

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();

        list = pDao.showData();
    }
}
