package app.utility;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class PrinterInfo {

    public void getPrinterName() {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

        for (PrintService printService : printServices) {
            String printerName = printService.getName();

            // Memeriksa apakah printer thermal
            if (printerName.contains("thermal") || printerName.contains("Thermal")) {
                System.out.println("Nama Printer Thermal: " + printerName);
            }
        }
    }

    public static void main(String[] args) {
        PrinterInfo example = new PrinterInfo();
        System.out.println("start");
        example.getPrinterName();
    }
}
