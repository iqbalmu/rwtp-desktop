package app.utility;

import jpos.JposException;
import jpos.POSPrinter;
import jpos.POSPrinterConst;

public class PrintThermal {

    public static void print(String textToPrint) {
        POSPrinter printer;

        try {
            // Membuka dan mengklaim printer thermal
            printer = new POSPrinter();
            printer.open("micro-printer"); // Ganti "PrinterName" dengan nama printer yang sebenarnya
            printer.claim(5000);
            printer.setDeviceEnabled(true);

            // Menggunakan metode printer API untuk mengirim perintah pencetakan
            printer.printNormal(POSPrinterConst.PTR_S_RECEIPT, textToPrint);

            // Melepas dan menutup printer thermal
            printer.release();
            printer.close();
        } catch (JposException e) {
            System.out.println("Kesalahan saat mencetak: " + e.getMessage());
        }
    }
}
