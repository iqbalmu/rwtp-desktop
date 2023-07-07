package app.controller.transaksi.sewa;

import app.Main;
import app.dao.MobilDAO;
import app.dao.PelangganDAO;
import app.dao.SewaDAO;
import app.model.Mobil;
import app.model.Pelanggan;
import app.model.UserInfo;
import app.model.transaksi.Sewa;
import app.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

public class SewaController implements Initializable {

    public ComboBox<String> cbIdentitas;
    public ComboBox<String> cbClass;
    public ComboBox<String> cbJadwal;
    public ComboBox<Mobil> cbMobil;
    public TextField txtNama;
    public TextField txtAlamat;
    public TextField txtTujuan;
    public TextField txtPhone;
    public CheckBox ck1, ck2, ck3, ck4, ck5, ck6, ckcc, ckIsMember;
    public boolean isMember;
    public ObservableList<String> lCkursi = FXCollections.observableArrayList();
    public TextField txtIdentitas;
    public DatePicker dpSewaDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> listIdentitas = FXCollections.observableArrayList("mahasiswa", "umum");
        cbIdentitas.setItems(listIdentitas);

        ObservableList<String> listClass = FXCollections.observableArrayList("Ekonomi", "Eksekutif");
        cbClass.setItems(listClass);

        ObservableList<String> listJadwal = FXCollections.observableArrayList(
                "Pagi 10:00 WIB", "Siang 14:00 WIB", "Malam 20:00 WIB");
        cbJadwal.setItems(listJadwal);

        txtIdentitas.setDisable(true);
    }

    public ObservableList<Mobil> getMobil(String kelas) {
        ObservableList<Mobil> mobils;
        MobilDAO mobilDAO = new MobilDAO();
        mobils = mobilDAO.getMobilAvailable(kelas);
        return mobils;
    }

    public List<String> getKursi(String nopol, LocalDate sewaDate) {
        List<String> kursis;

        SewaDAO sewaDAO = new SewaDAO();
        kursis = sewaDAO.getKursiAvailable(nopol, sewaDate);

//        System.out.println("avai: " + kursis);

        if (kursis != null) {
            return kursis;
        } else {
            return kursis = List.of("");
        }
    }

    public void selectClass(ActionEvent actionEvent) {
        cbMobil.setValue(null);
        resetCbKursi();
        String selectedClass = cbClass.getValue();
        cbMobil.setItems(getMobil(selectedClass));
    }

    public void resetCbKursi() {
        lCkursi.clear();
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

    public void selectMobil(ActionEvent actionEvent) {
        String selectedMobil = cbMobil.getSelectionModel().getSelectedItem().toString();
        String nopol = selectedMobil.substring(0, selectedMobil.indexOf("|") - 1);
        LocalDate sewaDate = dpSewaDate.getValue();

        resetCbKursi();

        ck1.setOnAction(e -> {
            if (ck1.isSelected()) {
                lCkursi.add(ck1.getText());
            }
        });
        ck2.setOnAction(e -> {
            if (ck2.isSelected()) {
                lCkursi.add(ck2.getText());
            }
        });
        ck3.setOnAction(e -> {
            if (ck3.isSelected()) {
                lCkursi.add(ck3.getText());
            }
        });
        ck4.setOnAction(e -> {
            if (ck4.isSelected()) {
                lCkursi.add(ck4.getText());
            }
        });
        ck5.setOnAction(e -> {
            if (ck5.isSelected()) {
                lCkursi.add(ck5.getText());
            }
        });
        ck6.setOnAction(e -> {
            if (ck6.isSelected()) {
                lCkursi.add(ck6.getText());
            }
        });
        ckcc.setOnAction(e -> {
            if (ckcc.isSelected()) {
                lCkursi.add(ckcc.getText());
            }
        });

        System.out.println(getKursi(nopol, sewaDate));
        List<String> kursis = getKursi(nopol, sewaDate);
        String chs = kursis.toString().replace("[", "").replace("]","");
        String[] chairs = chs.split(", ");

        for (String kursi : chairs) {
            System.out.println(kursi);
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
                case "CC" -> {
                    ckcc.setSelected(true);
                    ckcc.setDisable(true);
                }
                default -> {
//                    ck1.setSelected(false);
//                    ck1.setDisable(false);
//                    ck2.setSelected(false);
//                    ck2.setDisable(false);
//                    ck3.setSelected(false);
//                    ck3.setDisable(false);
//                    ck4.setSelected(false);
//                    ck4.setDisable(false);
//                    ck5.setSelected(false);
//                    ck5.setDisable(false);
//                    ck6.setSelected(false);
//                    ck6.setDisable(false);
//                    ckcc.setSelected(false);
//                    ckcc.setDisable(false);
                }
            }
        }
    }

    final String no_transaksi = UUID.randomUUID().toString();

    public void saveHandler(ActionEvent actionEvent) throws IOException {
        System.out.println("Store");

        // 1. Store data pelanggan
        String id_pelanggan = (!isMember) ? UUID.randomUUID().toString() : txtIdentitas.getText();
        String nama = txtNama.getText();
        String kategori = cbIdentitas.getSelectionModel().getSelectedItem();
        String nmobil = cbMobil.getSelectionModel().getSelectedItem().toString();
        String nopol = nmobil.substring(0, nmobil.indexOf("|") - 1);
        LocalDate sewa_date = dpSewaDate.getValue();
        String kelas = cbClass.getValue();
        double harga = checkHarga(kategori, kelas, isMember);
        int user = UserInfo.id_user;

        Pelanggan pelanggan = new Pelanggan(
                id_pelanggan,
                nama,
                txtPhone.getText(),
                kategori,
                txtAlamat.getText(),
                isMember
        );

        PelangganDAO pelangganDAO = new PelangganDAO();
        pelangganDAO.addData(pelanggan);

        // 2. Store data sewa
        HashSet<String> uniqueValues = new HashSet<>(lCkursi);
        lCkursi.setAll(uniqueValues);

        // confirm data
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/xml/transaksi/sewa/DetailTransaksiSewa.fxml"));
        Parent root = loader.load();
        DetailSewaController dsc = loader.getController();
        dsc.setField(UserInfo.username, new Timestamp(new Date().getTime()).toString(), nama, sewa_date.toString(), kelas, nopol, txtPhone.getText(), lCkursi.toString(), String.valueOf(harga), txtAlamat.getText(), cbJadwal.getSelectionModel().getSelectedItem());

        Scene scene = new Scene(root);
        stage.setTitle("Confirm Data");
        stage.setScene(scene);
        stage.showAndWait();

        if (!dsc.status) {
            System.out.println("rental batal");
            return;
        }

        Sewa sewa = new Sewa(
                id_pelanggan,
                nopol,
                user,
                new Timestamp(new Date().getTime()),
                java.sql.Date.valueOf(sewa_date),
                cbJadwal.getSelectionModel().getSelectedItem(),
                lCkursi.toString(),
                txtTujuan.getText(),
                no_transaksi,
                harga,
                ""
        );
        System.out.println(id_pelanggan);
        System.out.println(nopol);
        System.out.println(user);
        System.out.println(cbJadwal.getSelectionModel().getSelectedItem());
        System.out.println(lCkursi.toString());
        System.out.println(txtTujuan.getText());
        System.out.println(no_transaksi);
        System.out.println(harga);
        System.out.println("set sewa success");

        SewaDAO sewaDAO = new SewaDAO();
        sewaDAO.addData(sewa);

        // 3. Set Status mobil to beroperasi
        MobilDAO mobilDAO = new MobilDAO();
//        // jika kursi belum penuh, jam < dari jadwal set status "ready"
//        // jika kursi penuh set status mobil "sewa"
//        // jika kursi belum penuh, jam >= dari jadwal set status "sewa"

        // update status to sewa jika kursi dengan nopol XXXX == max_kursi
//        mobilDAO.updateStatus("Sewa", nopol);

//      // 4. back to home
//        Main main = new Main();
//        main.changeScene("view/xml/sewa/SewaTransaksi.fxml");
    }

    public double checkHarga(String kategori, String kelas, boolean isMember) {
        HashSet<String> uniqueValues = new HashSet<>(lCkursi);
        lCkursi.setAll(uniqueValues);

        // cek kelas, kategori, isMember
        double kHarga = (kelas == "Eksekutif") ? 220_000 : 180_000;
        double harga = (kategori == "mahasiswa") ? ((kHarga - 20_000) * lCkursi.size()) : kHarga * lCkursi.size();

        if (isMember) {
            harga = (harga * lCkursi.size()) - harga;
            return harga;
        } else {
            return harga;
        }
    }

    public void setIsMember(ActionEvent actionEvent) {
        isMember = ckIsMember.isSelected();
        txtIdentitas.setDisable(!isMember);
    }

    public void printHandler(ActionEvent actionEvent) {
        try {
            String templateFile = "jasper/sewa/tiket_sewa.jrxml";
            String compiledReportFile = "jasper/sewa/tiket_sewa.jasper";
            String pdfFileName = "report/sewa/tiket_sewa" + no_transaksi + ".pdf";
            JasperCompileManager.compileReportToFile(templateFile, compiledReportFile);

            // Fill Jasper Report with data
            Map<String, Object> parameters = new HashMap<>();
            // Set report parameters if needed
            parameters.put("noTransaksi", no_transaksi);
            // print report
            JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReportFile, parameters, JDBCConnection.getConnection());
            //export pdf
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFileName);
            // View the generated report
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void dpAction(ActionEvent actionEvent) {
        cbMobil.setValue(null);
        cbJadwal.setValue(null);
        cbClass.setValue(null);
        resetCbKursi();
    }
}
