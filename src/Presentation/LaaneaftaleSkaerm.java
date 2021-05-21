package Presentation;

import Logic.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class LaaneaftaleSkaerm extends GridPane {

    Kunde kunde;
    Faktura faktura;
    TextField loebetidField;
    TextField udbetalingsProcentField;
    TextField bilPrisField;
    TextField prisField;
    TextField renteField;
    TextField mdrUdbetalingField;
    LaaneaftaleComboBox modelValg;
    DecimalFormat df;


    public LaaneaftaleSkaerm (Kunde kunde) {
        LaaneAftaleThread laaneAftaleThread = new LaaneAftaleThread(this);
        this.kunde = kunde;
        this.faktura = new Faktura(kunde);
        this.df = new DecimalFormat("#,###.##", new DecimalFormatSymbols(Locale.GERMAN));

        this.setAlignment(Pos.TOP_LEFT);
        this.setHgap(30);
        this.setVgap(30);
        this.setPadding(new Insets(5, 10, 5, 35));
        this.getRowConstraints().add(new RowConstraints(75));
        this.getColumnConstraints().add(new ColumnConstraints(150));
        this.getColumnConstraints().add(new ColumnConstraints(150));
        this.getColumnConstraints().add(new ColumnConstraints(260));

        Text topLabel = new Text("Rente Aftale - " + kunde.getNavn());
        topLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
        this.add(topLabel, 0, 0,2,2);
        topLabel.setFill(Color.DARKRED);


        // model
        Label model = new Label("Model:");
        this.add(model, 0, 1);
        model.setAlignment(Pos.BASELINE_CENTER);
        model.setTextFill(Color.web("#8B0000"));
        model.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));
        //model


        modelValg = new LaaneaftaleComboBox(ListMediator.getBilListe());
        modelValg.setMinWidth(150);
        modelValg.setEditable(false);
        //model.setMaxWidth();
        this.add(modelValg, 1, 1);
        modelValg.setFocusTraversable(false);
        modelValg.setPromptText("Model");

        //udbetalingsprocent
        Label bilPris = new Label("Bil pris:");
        this.add(bilPris, 0, 2);
        bilPris.setAlignment(Pos.BASELINE_CENTER);
        bilPris.setTextFill(Color.web("#8B0000"));
        bilPris.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));



        bilPrisField = new TextField();
        this.add(bilPrisField,1,2);
        bilPrisField.setAlignment(Pos.BASELINE_CENTER);
        bilPrisField.setDisable(true);




        //udbetalingsprocent
        Label udbetalingsprocent = new Label("Udbetalingsprocent:");
        this.add(udbetalingsprocent, 0, 3);
        udbetalingsprocent.setAlignment(Pos.BASELINE_CENTER);
        udbetalingsprocent.setTextFill(Color.web("#8B0000"));
        udbetalingsprocent.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));



        udbetalingsProcentField = new TextField();
        this.add(udbetalingsProcentField,1,3);
        udbetalingsProcentField.setAlignment(Pos.BASELINE_CENTER);
        udbetalingsProcentField.setDisable(false);


        //løbetid
        Label loebetid = new Label("Løbetid:");
        this.add(loebetid, 0, 4);
        loebetid.setAlignment(Pos.BASELINE_CENTER);
        loebetid.setTextFill(Color.web("#8B0000"));
        loebetid.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));



        loebetidField = new TextField();
        this.add(loebetidField,1,4);
        loebetidField.setAlignment(Pos.BASELINE_CENTER);
        loebetidField.setDisable(false);


        //rentesats
        Label rentesats = new Label("Rentesats");
        this.add(rentesats, 0, 5);
        rentesats.setAlignment(Pos.BASELINE_CENTER);
        rentesats.setTextFill(Color.web("#8B0000"));
        rentesats.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));



        renteField = new TextField();
        this.add(renteField,1,5);
        renteField.setAlignment(Pos.BASELINE_CENTER);
        renteField.setDisable(true);


        //månedlig udbetaling
        Label maanedligudbetaling = new Label("Månedligudbetaling");
        this.add(maanedligudbetaling, 0, 6);
        maanedligudbetaling.setAlignment(Pos.BASELINE_CENTER);
        maanedligudbetaling.setTextFill(Color.web("#8B0000"));
        maanedligudbetaling.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));



        mdrUdbetalingField = new TextField();
        this.add(mdrUdbetalingField,1,6);
        mdrUdbetalingField.setAlignment(Pos.BASELINE_CENTER);
        mdrUdbetalingField.setDisable(true);


        // totalpris
        Label totalpris = new Label("Total Pris:");
        this.add(totalpris, 0, 7);
        totalpris.setAlignment(Pos.BASELINE_CENTER);
        totalpris.setTextFill(Color.web("#8B0000"));
        totalpris.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));


        prisField = new TextField();
        this.add(prisField,1,7);
        prisField.setAlignment(Pos.BASELINE_CENTER);
        prisField.setDisable(true);



        Button nextButton = new Button("Videre");
        Font NBSize = new Font(15);
        nextButton.setFont(NBSize);
        this.add(nextButton, 2, 7);
        nextButton.setAlignment(Pos.BASELINE_RIGHT);
        nextButton.setOnAction(e -> {
            laaneAftaleThread.stop();
            StartSkaerm.instance().pushNode(new FakturaGodkendelse(kunde, faktura));
        });
        laaneAftaleThread.start();


    }
    public void updateValues() {
        faktura.setLoebetid(Double.parseDouble(loebetidField.getText()));
        faktura.setUdbetalingsprocent(Double.parseDouble(udbetalingsProcentField.getText()));
        faktura.calcRentesats();
        renteField.setText(df.format( 100 *  faktura.calcRentesats()) + "%");
        mdrUdbetalingField.setText(df.format(faktura.getMdrUdbetaling()));
        prisField.setText(df.format(faktura.getTotalPris()));
    }

    public boolean fieldCheck() {

        return(udbetalingsProcentField.getText().isEmpty() || loebetidField.getText().isEmpty());
    }
    public boolean carCheck() {
        return(modelValg.getValue() == null);

    }
    public void updateCarValue() {
        faktura.setModel(modelValg.getBilNavn());
        faktura.setBilPris(modelValg.getBilPris());
        bilPrisField.setText(df.format(faktura.getBilPris()));
    }
}
