package app.utility;

import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PrintPreviewDialog {
    public static void showPrintPreview(Stage primaryStage, WritableImage snapshot) throws IOException {
        File tempFile = File.createTempFile("print_preview", ".png");

        // Menyimpan Snapshot ke file temporer
        ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", tempFile);

        // Menampilkan pratinjau cetak dalam ImageView
        ImageView imageView = new ImageView(tempFile.toURI().toString());
        imageView.setFitWidth(300);
        imageView.setFitHeight(500);

        // Membuat Scene dan Stage untuk pratinjau cetak
        Stage printPreviewStage = new Stage();
        printPreviewStage.setTitle("Print Preview");
        printPreviewStage.setScene(new Scene(new StackPane(imageView)));
        printPreviewStage.initOwner(primaryStage);

        // Menutup file temporer ketika pratinjau cetak ditutup
        printPreviewStage.setOnCloseRequest(event -> tempFile.delete());

        // Menampilkan pratinjau cetak
        printPreviewStage.show();
    }
}
