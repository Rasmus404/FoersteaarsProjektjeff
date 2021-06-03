package Presentation;

import Logic.Faktura;
import Logic.Tilbagebetaling;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

//Victor og Yusuf
public class TilbagebetalingsPopup {
    FakturaSkaerm baggrundsSkaerm;
    Faktura faktura;


    public TilbagebetalingsPopup(FakturaSkaerm baggrundsSkaerm, Faktura faktura) {
        this.baggrundsSkaerm = baggrundsSkaerm;
        this.faktura = faktura;
        Stage popupwindow = new Stage();


        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Tilbagebetalingsplan");

        VBox root = new VBox();

        root.setAlignment(Pos.CENTER);

        TableView<Tilbagebetaling> betalingTable = new TableView<>();
        TableColumn<Tilbagebetaling, Double> udbetalingColumn = new TableColumn("Udbetaling");
        TableColumn<Tilbagebetaling, Double> saldoColumn = new TableColumn("Saldo");
        TableColumn<Tilbagebetaling, Double> maanedColumn = new TableColumn("Måneder tilbage");

        udbetalingColumn.setCellValueFactory(new PropertyValueFactory<>("maanedligUdbetaling"));
        saldoColumn.setCellValueFactory(new PropertyValueFactory<>("saldo"));
        maanedColumn.setCellValueFactory(new PropertyValueFactory<>("maaned"));

        betalingTable.setPrefSize(400, 300);
        udbetalingColumn.prefWidthProperty().bind(betalingTable.widthProperty().multiply(0.20));
        saldoColumn.prefWidthProperty().bind(betalingTable.widthProperty().multiply(0.20));
        maanedColumn.prefWidthProperty().bind(betalingTable.widthProperty().multiply(0.20));
        betalingTable.getColumns().add(udbetalingColumn);
        betalingTable.getColumns().add(saldoColumn);
        betalingTable.getColumns().add(maanedColumn);


        for (double i = faktura.getLoebetid(); i >= 0; i -= 1) {
            Tilbagebetaling tilbageBetaling = new Tilbagebetaling(this.faktura, i);
            tilbageBetaling.saldoCalc();
            betalingTable.getItems().add(tilbageBetaling);
        }


        Button exportBtn = new Button("Eksporter til CSV");
        exportBtn.setOnAction(e -> {
            try {
                exportCsv();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        Image icon = new Image("file:logo.png");
        popupwindow.getIcons().add(icon);

        root.getChildren().addAll(betalingTable, exportBtn);

        Scene scene1 = new Scene(root, 300, 250);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();
    }

    public void exportCsv() throws Exception {
        Writer writer = null;
        Stage stage = new Stage();
        try {
            FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("*csv", "*"));
            fc.setTitle("Gem som");
            File file = fc.showSaveDialog(stage);
            writer = new BufferedWriter(new FileWriter(file));

            for (double i = faktura.getLoebetid(); i >= 0; i -= 1) {
                Tilbagebetaling tilbageBetaling = new Tilbagebetaling(this.faktura, i);
                tilbageBetaling.saldoCalc();
                String text = tilbageBetaling.toString();
                writer.write(text);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            writer.flush();
            writer.close();
        }
    }
}
