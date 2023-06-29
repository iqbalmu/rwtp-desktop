package app.controller.transaksi.rental;

import app.utility.PrintPreviewDialog;
import javafx.event.ActionEvent;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.image.WritableImage;

import java.io.IOException;

public class DetailRentalController {
    public Label txtNama;
    public Label txtPinjamDate;
    public Label txtKelas;
    public Label txtNopol;
    public Label txtNik;
    public Label txtBalikDate;
    public Label txtBiaya;
    public Label txtDateTransaksi;
    public Label txtUser;

    public boolean status;

    public void setField(String nama, String nik, String kelas, String nopol, String pinjamDate, String balikDate, String biaya, String no_transaksi, String date_transaksi){
        txtNama.setText(nama);
        txtNik.setText(nik);
        txtKelas.setText(kelas);
        txtNopol.setText(nopol);
        txtPinjamDate.setText(pinjamDate);
        txtBalikDate.setText(balikDate);
        txtBiaya.setText(biaya);
        txtUser.setText(no_transaksi);
        txtDateTransaksi.setText(date_transaksi);
    }

    public void cancelHandler(ActionEvent actionEvent) {
        status = false;
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    public void okeHandler(ActionEvent actionEvent) {
        status = true;
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

//    public void printHandler(ActionEvent actionEvent) {
////        Printer printer = Printer.getDefaultPrinter();
////        PageLayout pageLayout = printer.getDefaultPageLayout();
////
////        PrinterJob job = PrinterJob.createPrinterJob();
////        if (job != null) {
////            // Mendapatkan Scene aktif
////            Node n = (Node) actionEvent.getSource();
////            Stage stage = (Stage) n.getScene().getWindow();
////            Scene activeScene = stage.getScene();
////
////            // Mendapatkan root Node dari Scene aktif
////            Node root = activeScene.getRoot();
////
////            boolean success = job.printPage(pageLayout, root);
////            if (success) {
////                job.endJob();
////            }
////        }
////        try {
////            // Mendapatkan Scene aktif
////              Node n = (Node) actionEvent.getSource();
////              Stage stage = (Stage) n.getScene().getWindow();
////              Scene activeScene = stage.getScene();
////
////            // Membuat Snapshot dari Scene
////            WritableImage snapshot = activeScene.snapshot(null);
////
////            // Menampilkan pratinjau cetak dalam ImageView
////            PrintPreviewDialog.showPrintPreview(stage, snapshot);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//    }
}
