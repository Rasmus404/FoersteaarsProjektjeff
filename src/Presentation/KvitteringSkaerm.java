package Presentation;

import Logic.Kunde;
import Logic.Faktura;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


public class KvitteringSkaerm extends GridPane {

    Kunde kunde;
    Faktura faktura;
    DecimalFormat df;

    public KvitteringSkaerm(Kunde kunde, Faktura faktura) {

        this.faktura = new Faktura(kunde);
        this.kunde = kunde;
        this.df = new DecimalFormat("#,###.##", new DecimalFormatSymbols(Locale.GERMAN));


        this.setAlignment(Pos.TOP_LEFT);
        this.setHgap(20);
        this.setVgap(20);
        this.setPadding(new Insets(5, 10, 5, 35));
        this.getRowConstraints().add(new RowConstraints(75));
        this.getColumnConstraints().add(new ColumnConstraints(150));
        this.getColumnConstraints().add(new ColumnConstraints(150));
        this.getColumnConstraints().add(new ColumnConstraints(260));


        Text topLabel = new Text("Kvittering");
        topLabel.setFill(Color.DARKRED);
        topLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 25));
        topLabel.setTextAlignment(TextAlignment.CENTER);
        this.add(topLabel, 0, 0, 2, 2);


        Label name = new Label("Navn: ");
        this.add(name, 0, 1);
        name.setAlignment(Pos.BASELINE_CENTER);

        Label nameLabel = new Label("" + kunde.getNavn());
        this.add(nameLabel,1,1);
        nameLabel.setAlignment(Pos.BASELINE_CENTER);
        nameLabel.setDisable(true);


        Label adresse = new Label("Adresse: ");
        this.add(adresse, 0, 2);
        adresse.setAlignment(Pos.BASELINE_CENTER);

        Label adresseLabel = new Label("" + kunde.getAdresse());
        this.add(adresseLabel,1,2);
        adresseLabel.setAlignment(Pos.BASELINE_CENTER);
        adresseLabel.setDisable(true);


        Label telefon = new Label("Telefon: ");
        this.add(telefon, 0, 3);
        telefon.setAlignment(Pos.BASELINE_CENTER);

        Label tlfLabel = new Label("" + kunde.getTlf());
        this.add(tlfLabel,1,3);
        tlfLabel.setAlignment(Pos.BASELINE_CENTER);
        tlfLabel.setDisable(true);


        Label model = new Label("Model: ");
        this.add(model, 0, 4);
        model.setAlignment(Pos.BASELINE_CENTER);

        Label modelLabel = new Label("" + faktura.getModel());
        this.add(modelLabel,1,4);
        modelLabel.setAlignment(Pos.BASELINE_CENTER);
        modelLabel.setDisable(true);


        Label cpr = new Label("CPR Nr: ");
        this.add(cpr, 0, 5);
        cpr.setAlignment(Pos.BASELINE_CENTER);

        Label cprLabel = new Label("" + kunde.getCpr());
        this.add(cprLabel,1,5);
        cprLabel.setAlignment(Pos.BASELINE_CENTER);
        cprLabel.setDisable(true);


        Label leveringsadresse = new Label("Leveringsadresse: ");
        this.add(leveringsadresse, 0, 6);
        leveringsadresse.setAlignment(Pos.BASELINE_CENTER);

        Label laLabel = new Label("" + kunde.getLeveringsadresse());
        this.add(laLabel,1,6);
        laLabel.setAlignment(Pos.BASELINE_CENTER);
        laLabel.setDisable(true);


        Label udbetalingsprocent = new Label("Udbetalingsprocent: ");
        this.add(udbetalingsprocent, 0, 7);
        udbetalingsprocent.setAlignment(Pos.BASELINE_CENTER);

        Label upLabel = new Label( faktura.getUdbetalingsprocent() + "%");
        this.add(upLabel,1,7);
        upLabel.setAlignment(Pos.BASELINE_CENTER);
        upLabel.setDisable(true);


        Label rentesats = new Label("Rentesats: ");
        this.add(rentesats, 0, 8);
        rentesats.setAlignment(Pos.BASELINE_CENTER);

        Label rentesatsLabel = new Label(df.format(100 * faktura.getRentesats()) + "%");
        this.add(rentesatsLabel,1,8);
        rentesatsLabel.setAlignment(Pos.BASELINE_CENTER);
        rentesatsLabel.setDisable(true);


        Label totalPris = new Label("Total Pris: ");
        this.add(totalPris, 0 , 9);
        totalPris.setAlignment(Pos.BASELINE_CENTER);

        Label tpLabel = new Label(df.format(faktura.getTotalPris()));
        this.add(tpLabel,1,9);
        tpLabel.setAlignment(Pos.BASELINE_CENTER);
        tpLabel.setDisable(true);


        Label dato = new Label("Købsdato");
        this.add(dato, 0, 10);
        dato.setAlignment(Pos.BASELINE_CENTER);

        Label dateLabel = new Label(faktura.getDateFormated());
        this.add(dateLabel, 1, 10);
        dateLabel.setAlignment(Pos.BASELINE_CENTER);
        dateLabel.setDisable(true);

    }

}
