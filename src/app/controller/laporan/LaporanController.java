package app.controller.laporan;

import app.utility.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.util.converter.LocalDateTimeStringConverter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class LaporanController implements Initializable {

    public DatePicker startDate;
    public DatePicker endDate;
    public ComboBox<String> cbJenis;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> jenis = FXCollections.observableArrayList(
                "Laporan Pemasukan",
                "Laporan Pengeluaran",
                "Laporan Mobil",
                "Laporan Sopir"

        );

        cbJenis.setItems(jenis);
    }

    public void jasperPrint(String start, String end, String jenis){
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDateTimeStringConverter converter = new LocalDateTimeStringConverter();
            String formattedDateTime = converter.toString(currentDateTime).replace("/", "_").replace(",","_").replace(":","_").replace(" ","");

            String templateFile = "jasper/"+jenis+"/"+jenis+"_report.jrxml";
            String compiledReportFile = "jasper/"+jenis+"/"+jenis+"_report.jasper";
            String pdfFileName = "report/"+jenis+"/"+formattedDateTime +"_"+ jenis + "_report.pdf";
            JasperCompileManager.compileReportToFile(templateFile, compiledReportFile);

            // Fill Jasper Report with data
            Map<String, Object> parameters = new HashMap<>();
            // Set report parameters if needed
            parameters.put("starDate", startDate);
            parameters.put("endDate", endDate);
            // print report
            JasperPrint jasperPrint = JasperFillManager.fillReport(compiledReportFile, parameters, JDBCConnection.getConnection());
            //export pdf
            JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFileName);
            // View the generated report
            JasperViewer.viewReport(jasperPrint, false);
        }catch (JRException e){
            e.printStackTrace();
        }

    }

    public void handlePrint(ActionEvent actionEvent) {
        String start = startDate.getValue().toString();
        String end = endDate.getValue().toString();
        String jenis = cbJenis.getValue();

        if (Objects.equals(jenis, "Laporan Pemasukan")) {
            jenis = "pemasukan";
            jasperPrint(start, end, jenis);
        } else if (Objects.equals(jenis, "Laporan Pengeluaran")) {
            jenis = "pengeluaran";
            jasperPrint(start, end, jenis);
        } else if (Objects.equals(jenis, "Laporan Mobil")) {
            jenis = "mobil";
            jasperPrint(start, end, jenis);
        } else if (Objects.equals(jenis, "Laporan Sopir")) {
            jenis = "sopir";
            jasperPrint(start, end, jenis);
        }
    }

}
