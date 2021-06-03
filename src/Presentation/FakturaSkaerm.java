package Presentation;

import Logic.Kunde;
import Logic.Faktura;
import Logic.ListMediator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

//Oliver
public class FakturaSkaerm extends GridPane {


    Kunde kunde;
    Faktura faktura;
    DecimalFormat df;


    public FakturaSkaerm(Kunde kunde, Faktura faktura) {

        this.faktura = faktura;
        this.kunde = kunde;
        this.df = new DecimalFormat("#,###.##", new DecimalFormatSymbols(Locale.GERMAN));


        this.setAlignment(Pos.TOP_LEFT);
        this.setHgap(20);
        this.setVgap(20);
        this.setPadding(new Insets(5, 10, 5, 35));
        this.getRowConstraints().add(new RowConstraints(75));
        this.getColumnConstraints().add(new ColumnConstraints(150));
        this.getColumnConstraints().add(new ColumnConstraints(200));
        this.getColumnConstraints().add(new ColumnConstraints(50));


        Text topLabel = new Text("Kvittering");
        topLabel.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 25));
        topLabel.setTextAlignment(TextAlignment.CENTER);
        this.add(topLabel, 0, 0, 2, 2);
        topLabel.setFill(Color.DARKRED);

        this.setStyle("-fx-background-image: url(\"ferrari.jpg\"); -fx-background-size: 900 620;");


        Label name = new Label("Navn: ");
        this.add(name, 0, 1);
        name.setAlignment(Pos.BASELINE_CENTER);
        name.setTextFill(Color.web("Darkred"));
        name.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));


        TextField nameLabel = new TextField("" + kunde.getNavn());
        this.add(nameLabel, 1, 1);
        nameLabel.setAlignment(Pos.BASELINE_CENTER);
        nameLabel.setDisable(true);
        nameLabel.setOpacity(1);
        nameLabel.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        nameLabel.setStyle("-fx-text-fill: white;" +
                "-fx-prompt-text-fill: white; -fx-font-size: 13px; -fx-background-color: darkred");


        Label adresse = new Label("Adresse: ");
        this.add(adresse, 0, 2);
        adresse.setAlignment(Pos.BASELINE_CENTER);
        adresse.setTextFill(Color.web("Darkred"));
        adresse.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));


        TextField adresseField = new TextField("" + kunde.getAdresse());
        this.add(adresseField, 1, 2);
        adresseField.setAlignment(Pos.BASELINE_CENTER);
        adresseField.setDisable(true);
        adresseField.setOpacity(1);
        adresseField.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        adresseField.setStyle("-fx-text-fill: white;" +
                "-fx-prompt-text-fill: white; -fx-font-size: 13px; -fx-background-color: darkred");

        Label telefon = new Label("Telefon: ");
        this.add(telefon, 0, 3);
        telefon.setAlignment(Pos.BASELINE_CENTER);
        telefon.setTextFill(Color.web("Darkred"));
        telefon.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));


        TextField tlfLabel = new TextField("" + kunde.getTlf());
        this.add(tlfLabel, 1, 3);
        tlfLabel.setAlignment(Pos.BASELINE_CENTER);
        tlfLabel.setDisable(true);
        tlfLabel.setOpacity(1);
        tlfLabel.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        tlfLabel.setStyle("-fx-text-fill: white;" +
                "-fx-prompt-text-fill: white; -fx-font-size: 13px; -fx-background-color: darkred");


        Label model = new Label("Model: ");
        this.add(model, 0, 4);
        model.setAlignment(Pos.BASELINE_CENTER);
        model.setTextFill(Color.web("Darkred"));
        model.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));


        TextField modelLabel = new TextField("" + faktura.getModel());
        this.add(modelLabel, 1, 4);
        modelLabel.setAlignment(Pos.BASELINE_CENTER);
        modelLabel.setDisable(true);
        modelLabel.setOpacity(1);
        modelLabel.setStyle("-fx-text-fill: white;" +
                "-fx-prompt-text-fill: white; -fx-font-size: 13px; -fx-background-color: darkred");


        Label cpr = new Label("CPR Nr: ");
        this.add(cpr, 0, 5);
        cpr.setAlignment(Pos.BASELINE_CENTER);
        cpr.setTextFill(Color.web("Darkred"));
        cpr.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));


        TextField cprLabel = new TextField("" + kunde.getCpr());
        this.add(cprLabel, 1, 5);
        cprLabel.setAlignment(Pos.BASELINE_CENTER);
        cprLabel.setDisable(true);
        cprLabel.setOpacity(1);
        cprLabel.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        cprLabel.setStyle("-fx-text-fill: white;" +
                "-fx-prompt-text-fill: white; -fx-font-size: 13px; -fx-background-color: darkred");


        Label leveringsadresse = new Label("Leveringsadresse: ");
        this.add(leveringsadresse, 0, 6);
        leveringsadresse.setAlignment(Pos.BASELINE_CENTER);
        leveringsadresse.setTextFill(Color.web("Darkred"));
        leveringsadresse.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));


        TextField laLabel = new TextField("" + kunde.getLeveringsadresse());
        this.add(laLabel, 1, 6);
        laLabel.setAlignment(Pos.BASELINE_CENTER);
        laLabel.setDisable(true);
        laLabel.setOpacity(1);
        laLabel.setStyle("-fx-text-fill: white;" +
                "-fx-prompt-text-fill: white; -fx-font-size: 13px; -fx-background-color: darkred");


        Label udbetalingsprocent = new Label("Udbetalingsprocent: ");
        this.add(udbetalingsprocent, 0, 7);
        udbetalingsprocent.setAlignment(Pos.BASELINE_CENTER);
        udbetalingsprocent.setTextFill(Color.web("Darkred"));
        udbetalingsprocent.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));


        TextField upLabel = new TextField(faktura.getUdbetalingsprocent() + "%");
        this.add(upLabel, 1, 7);
        upLabel.setAlignment(Pos.BASELINE_CENTER);
        upLabel.setDisable(true);
        upLabel.setOpacity(1);
        upLabel.setStyle("-fx-text-fill: white;" +
                "-fx-prompt-text-fill: white; -fx-font-size: 13px; -fx-background-color: darkred");


        Label rentesats = new Label("Rentesats: ");
        this.add(rentesats, 0, 8);
        rentesats.setAlignment(Pos.BASELINE_CENTER);
        rentesats.setTextFill(Color.web("Darkred"));
        rentesats.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));


        TextField rentesatsLabel = new TextField(df.format(100 * faktura.getRentesats()) + "%");
        this.add(rentesatsLabel, 1, 8);
        rentesatsLabel.setAlignment(Pos.BASELINE_CENTER);
        rentesatsLabel.setDisable(true);
        rentesatsLabel.setOpacity(1);
        rentesatsLabel.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        rentesatsLabel.setStyle("-fx-text-fill: white;" +
                "-fx-prompt-text-fill: white; -fx-font-size: 13px; -fx-background-color: darkred");


        Label totalPris = new Label("Total Pris: ");
        this.add(totalPris, 0, 9);
        totalPris.setAlignment(Pos.BASELINE_CENTER);
        totalPris.setTextFill(Color.web("Darkred"));
        totalPris.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));


        TextField tpLabel = new TextField(df.format(faktura.getTotalPris()));
        this.add(tpLabel, 1, 9);
        tpLabel.setAlignment(Pos.BASELINE_CENTER);
        tpLabel.setDisable(true);
        tpLabel.setOpacity(1);
        tpLabel.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        tpLabel.setStyle("-fx-text-fill: white;" +
                "-fx-prompt-text-fill: white; -fx-font-size: 13px; -fx-background-color: darkred");


        Label dato = new Label("Købsdato");
        this.add(dato, 0, 10);
        dato.setAlignment(Pos.BASELINE_CENTER);
        dato.setTextFill(Color.web("Darkred"));
        dato.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));


        TextField dateLabel = new TextField(faktura.getDateFormated());
        this.add(dateLabel, 1, 10);
        dateLabel.setAlignment(Pos.BASELINE_CENTER);
        dateLabel.setDisable(true);
        dateLabel.setOpacity(1);
        dateLabel.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        dateLabel.setStyle("-fx-text-fill: white;" +
                "-fx-prompt-text-fill: white; -fx-font-size: 13px; -fx-background-color: darkred");


//        Yusuf
        Button exportBtn = new Button("Export");
        this.add(exportBtn, 2, 10);
        exportBtn.setOnAction(e -> {
            try {
                exportCsv();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Button tilbagebetalingBtn = new Button("Se tilbagebetalingsplan");
        this.add(tilbagebetalingBtn, 3, 10);
        tilbagebetalingBtn.setOnAction(e -> new TilbagebetalingsPopup(this, this.faktura));
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

            for (Faktura faktura : ListMediator.getFakturaListeByKunde(kunde)) {

                String text = faktura.toString();

                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            writer.flush();
            writer.close();
        }
    }

    //Yusuf
}


