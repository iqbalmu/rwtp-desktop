package app.controller.transaksi.store;

import app.Main;
import app.dao.MobilDAO;
import app.dao.PelangganDAO;
import app.dao.SewaDAO;
import app.model.Mobil;
import app.model.Pelanggan;
import app.model.UserInfo;
import app.model.transaksi.Sewa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

public class SewaController implements Initializable{

    public ComboBox<String> cbIdentitas;
    public ComboBox<String> cbClass;
    public ComboBox<String> cbJadwal;
    public ComboBox<Mobil> cbMobil;
    public TextField txtNama;
    public TextField txtAlamat;
    public TextField txtTujuan;
    public TextField txtPhone;
    public CheckBox ck1, ck2, ck3, ck4, ck5, ck6, ckcc;
    public ObservableList<String> lCkursi = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listIdentitas = FXCollections.observableArrayList("KTM", "KTP");
        cbIdentitas.setItems(listIdentitas);

        ObservableList<String> listClass = FXCollections.observableArrayList("Ekonomi", "Eksekutif");
        cbClass.setItems(listClass);

        ObservableList<String> listJadwal = FXCollections.observableArrayList(
                "Pagi 10:00 WIB", "Siang 14:00 WIB", "Malam 20:00 WIB");
        cbJadwal.setItems(listJadwal);

        ck1.setOnAction(e -> {
            lCkursi.add(ck1.getText());
        });
        ck2.setOnAction(e -> {
            lCkursi.add(ck2.getText());
        });
        ck3.setOnAction(e -> {
            lCkursi.add(ck3.getText());
        });
        ck4.setOnAction(e -> {
            lCkursi.add(ck5.getText());
        });
        ck5.setOnAction(e -> {
            lCkursi.add(ck5.getText());
        });
        ck6.setOnAction(e -> {
            lCkursi.add(ck6.getText());
        });
        ckcc.setOnAction(e -> {
            lCkursi.add(ckcc.getText());
        });

    }

    public ObservableList<Mobil> getMobil(String kelas){
        ObservableList<Mobil> mobils;
        MobilDAO mobilDAO = new MobilDAO();
        mobils = mobilDAO.getMobilAvailable(kelas);
        return mobils;
    }

    public List<String> getKursi(String nopol) {
        List<String> kursis;
        SewaDAO sewaDAO = new SewaDAO();
        kursis = sewaDAO.getKursiAvailable(nopol);
        return kursis;
    }

    String selectedMobil;
    public void selectClass(ActionEvent actionEvent) {
        String selectedClass = cbClass.getValue();
        cbMobil.setItems(getMobil(selectedClass));
        selectedMobil = null;
    }

    public void selectMobil(ActionEvent actionEvent) {
        selectedMobil = cbMobil.getSelectionModel().getSelectedItem().toString();
//        System.out.println(selectedMobil);

        ck1.setSelected(false);
        ck2.setSelected(false);
        ck3.setSelected(false);
        ck4.setSelected(false);
        ck5.setSelected(false);
        ck6.setSelected(false);
        ckcc.setSelected(false);

        List<String> kursis =  getKursi(selectedMobil);
        for (String kursi : kursis) {
            switch (kursi) {
                case "1" -> {
                    ck1.setSelected(true);
                    ck1.setDisable(true);
                }
                case "2" -> {
                    ck2.setSelected(true);
                    ck2.setDisable(true);
                }
                case "3" -> {
                    ck3.setSelected(true);
                    ck3.setDisable(true);
                }
                case "4" -> {
                    ck4.setSelected(true);
                    ck4.setDisable(true);
                }
                case "5" -> {
                    ck5.setSelected(true);
                    ck5.setDisable(true);
                }
                case "6" -> {
                    ck6.setSelected(true);
                    ck6.setDisable(true);
                }
                case "cc" -> {
                    ckcc.setSelected(true);
                    ckcc.setDisable(true);
                }
                default -> {
                    ck1.setSelected(false);
                    ck1.setDisable(false);
                    ck2.setSelected(false);
                    ck2.setDisable(false);
                    ck3.setSelected(false);
                    ck3.setDisable(false);
                    ck4.setSelected(false);
                    ck4.setDisable(false);
                    ck5.setSelected(false);
                    ck5.setDisable(false);
                    ck6.setSelected(false);
                    ck6.setDisable(false);
                    ckcc.setSelected(false);
                    ckcc.setDisable(false);
                }
            }
        }
        // 1. cek setiap checkbox
        // 2. jika nilai checkbox terdapat dalam array kursi
        //    disable checkbox
    }


    public void saveHandler(ActionEvent actionEvent) throws IOException {
        System.out.println("Store");

        // 1. Store data pelanggan
        String id_pelanggan = UUID.randomUUID().toString();
        Pelanggan pelanggan = new Pelanggan(
                id_pelanggan,
                txtNama.getText(),
                txtPhone.getText(),
                cbIdentitas.getSelectionModel().getSelectedItem(),
                txtAlamat.getText(),
                false
        );
        System.out.println("Set model pelanggan success");

        PelangganDAO pelangganDAO = new PelangganDAO();
        pelangganDAO.addData(pelanggan);

        System.out.println("Store data pelanggan success");

        // 2. Store data sewa

        String nopol = cbMobil.getSelectionModel().getSelectedItem().toString();
        String no_transaksi = UUID.randomUUID().toString();

        Sewa sewa = new Sewa(
                id_pelanggan,
                nopol,
                UserInfo.id_user,
                new Timestamp(new Date().getTime()),
                cbJadwal.getSelectionModel().getSelectedItem(),
                lCkursi.toString(),
                txtTujuan.getText(),
                no_transaksi,
                70000,
                "Lunas"
        );
        System.out.println("set sewa success");

        SewaDAO sewaDAO = new SewaDAO();
        sewaDAO.addData(sewa);
        System.out.println("store data sewa success");

        // 3. Set Status mobil to beroperasi
        MobilDAO mobilDAO = new MobilDAO();
        // jika kursi belum penuh, jam < dari jadwal set status "ready"
        // jika kursi penuh set status mobil "sewa"
        // jika kursi belum penuh, jam >= dari jadwal set status "sewa"
        mobilDAO.updateStatus("Beroperasi", nopol);

        // 4. back to home
        Main main = new Main();
        main.changeScene("view/xml/main.fxml");
    }

}
