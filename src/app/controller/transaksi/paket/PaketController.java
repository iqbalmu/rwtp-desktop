package app.controller.transaksi.paket;

import app.Main;
import app.dao.MobilDAO;
import app.dao.PaketDAO;
import app.model.Mobil;
import app.model.UserInfo;
import app.model.transaksi.Paket;
import app.utility.JDBCConnection;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PaketController implements Initializable {
    public TextField nama_pengirim;
    public TextField hp_pengirim;
    public TextField nama_penerima;
    public TextField alamat_penerima;
    public TextField hp_penerima;
    public TextField kuantitas;
    public ComboBox<Mobil> cbMobil;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbMobil.setItems(getMobil());
    }


    final String noTransaksi = UUID.randomUUID().toString();
    public void btnSaveHandler(ActionEvent actionEvent) throws IOException {
        System.out.println("Store Data");

        String mNopol = cbMobil.getSelectionModel().getSelectedItem().toString();
        String nopol = mNopol.substring(0, mNopol.indexOf("|")-1);
        String namaPengirim = nama_pengirim.getText();
        String hpPengirim = hp_pengirim.getText();
        String namaPenerima = nama_penerima.getText();
        String hpPenerima = hp_penerima.getText();
        String alamatPenerima = alamat_penerima.getText();
        int kuantitasTxt = Integer.parseInt(kuantitas.getText());
        int bayar = 30000 * kuantitasTxt; // untuk harga nanti dinamis dari database tabel harga
        String keterangan = "";
        Timestamp date = new Timestamp(new Date().getTime());

        //confirm data
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/xml/transaksi/paket/DetailTransaksiPaket.fxml"));
        Parent root = loader.load();
        DetailPaketController dpc = loader.getController();
        dpc.setField(
                namaPengirim,
                hpPengirim,
                namaPenerima,
                hpPenerima,
                alamatPenerima,
                String.valueOf(kuantitasTxt),
                String.valueOf(bayar),
                UserInfo.username,
                date.toString()
        );

        Scene scene = new Scene(root);
        stage.setTitle("Confirm Data");
        stage.setScene(scene);
        stage.showAndWait();

        if (!dpc.status) {
            System.out.println("Paket Batal");
            return;
        }

        if (namaPengirim.isEmpty() || hpPengirim.isEmpty() || namaPenerima.isEmpty() || hpPenerima.isEmpty() || alamatPenerima.isEmpty() || kuantitasTxt < 1){
            System.out.println("Lengkapi Data Paket");
        }else{
            PaketDAO paketDAO = new PaketDAO();
            paketDAO.addData(new Paket(noTransaksi, nopol, namaPengirim, hpPengirim, namaPenerima,hpPenerima, alamatPenerima, kuantitasTxt, bayar, keterangan, date));
        }

//        back to AllTransaction;
//        Main main = new Main();
//        main.changeScene("view/xml/main.fxml");
    }

    public ObservableList<Mobil> getMobil() {
        ObservableList<Mobil> mobils;
        MobilDAO mobilDAO = new MobilDAO();
        mobils = mobilDAO.getMobilReady();
        return mobils;
    }

    public void btnPrint(ActionEvent actionEvent){
        try {
            String templateFile = "jasper/paket/nota_paket.jrxml";
            String compiledReportFile = "jasper/paket/nota_paket.jasper";
            String pdfFileName = "report/paket/nota_paket_" + noTransaksi + ".pdf";
            JasperCompileManager.compileReportToFile(templateFile, compiledReportFile);

            // Fill Jasper Report with data
            Map<String, Object> parameters = new HashMap<>();
            // Set report parameters if needed
            parameters.put("noTransaksi", noTransaksi);
            // print report
            JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReportFile, parameters, JDBCConnection.getConnection());
            //export pdf
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFileName);
            // View the generated report
            JasperViewer.viewReport(jasperPrint, false);
        }catch (JRException e){
            e.printStackTrace();
        }

//        String jrxmlFileName = "report/nota_paket.jrxml";
//        String jasperFileName = "report/nota_paket.jasper";
//        String pdfFileName = "report/nota_report_" + noTransaksi + ".pdf";
//        try {
//            JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);
//
//            Connection conn = JDBCConnection.getConnection();
//
//            System.out.println(noTransaksi);
//            HashMap<String, Object> hm = new HashMap<String, Object>();
//            hm.put("id_paket", noTransaksi);
//
//            JasperPrint jprint = JasperFillManager.fillReport(jasperFileName, hm, conn);
//
//            JasperExportManager.exportReportToPdfFile(jprint, pdfFileName);
//
//            System.out.println("Done exporting reports to pdf");
//        } catch (Exception e) {
//            System.out.print("Exception:" + e);
//        }
    }
}
