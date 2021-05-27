package Presentation;

import Logic.Kunde;
import Logic.Faktura;
import Logic.ListMediator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;


import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class FakturaGodkendelse extends GridPane {

    Kunde kunde;
    Faktura faktura;
    DatePicker dp;

    public FakturaGodkendelse (Kunde kunde, Faktura faktura) {

        this.kunde = kunde;
        this.faktura = faktura;


        this.setAlignment(Pos.TOP_LEFT);
        this.setHgap(30);
        this.setVgap(30);
        this.setPadding(new Insets(5, 10, 5, 35));
        this.getRowConstraints().add(new RowConstraints(75));
        this.getColumnConstraints().add(new ColumnConstraints(150));
        this.getColumnConstraints().add(new ColumnConstraints(150));
        this.getColumnConstraints().add(new ColumnConstraints(260));

        Text topLabel = new Text("Godkendelse af Faktura: - " + kunde.getNavn());
        topLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
        this.add(topLabel, 0, 0,2,2);
        topLabel.setFill(Color.DARKRED);

        // model
        Label model = new Label("Model:");
        this.add(model, 0, 1);
        model.setAlignment(Pos.BASELINE_CENTER);
        model.setTextFill(Color.web("Darkred"));
        model.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));


        TextField modelValgt = new TextField(faktura.getModel());
        this.add(modelValgt, 1, 1);
        modelValgt.setAlignment(Pos.BASELINE_CENTER);
        modelValgt.setDisable(true);
        modelValgt.setOpacity(1);
        modelValgt.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        modelValgt.setStyle("-fx-text-fill: white;" +
                "-fx-prompt-text-fill: white; -fx-background-color: darkred");

        //Bil pris
        Label bilPris = new Label("Bilpris:");
        this.add(bilPris, 0, 2);
        bilPris.setAlignment(Pos.BASELINE_CENTER);
        bilPris.setTextFill(Color.web("darkred"));
        bilPris.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));

        TextField bilPrisField = new TextField("" + faktura.getBilPris());
        this.add(bilPrisField,1,2);
        bilPrisField.setAlignment(Pos.BASELINE_CENTER);
        bilPrisField.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD,13));
        bilPrisField.setDisable(true);
        bilPrisField.setOpacity(1);
        bilPrisField.setStyle("-fx-text-fill: white;" +
                "-fx-prompt-text-fill: white; -fx-background-color: darkred");

        //udbetalingsprocent textfield
        Label udbetalingsprocent = new Label("Udbetalingsprocent:");
        this.add(udbetalingsprocent, 0, 3);
        udbetalingsprocent.setAlignment(Pos.BASELINE_CENTER);
        udbetalingsprocent.setTextFill(Color.web("darkred"));
        udbetalingsprocent.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));

        TextField ubField = new TextField("" + faktura.getUdbetalingsprocent());
        this.add(ubField,1,3);
        ubField.setAlignment(Pos.BASELINE_CENTER);
        ubField.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD,13));
        ubField.setDisable(true);
        ubField.setOpacity(1);
        ubField.setOpacity(1);
        ubField.setStyle("-fx-text-fill: white;" +
                "-fx-prompt-text-fill: white; -fx-background-color: darkred");


        //loebetid
        Label loebetid = new Label("Løbetid:");
        this.add(loebetid, 0, 4);
        loebetid.setAlignment(Pos.BASELINE_CENTER);
        loebetid.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD,13));
        loebetid.setTextFill(Color.web("darkred"));
        loebetid.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));

        TextField loebetidField = new TextField("" + faktura.getLoebetid());
        this.add(loebetidField,1,4);
        loebetidField.setAlignment(Pos.BASELINE_CENTER);
        loebetidField.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD,13));
        loebetidField.setDisable(true);
        loebetidField.setOpacity(1);
        loebetidField.setStyle("-fx-text-fill: white;" +
                "-fx-prompt-text-fill: white; -fx-background-color: darkred");



        //rentesats
        Label rentesats = new Label("Rentesats");
        this.add(rentesats, 0, 5);
        rentesats.setAlignment(Pos.BASELINE_CENTER);
        rentesats.setTextFill(Color.web("darkred"));
        rentesats.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));


        TextField renteField = new TextField("" + faktura.calcRentesats( )); //Har bare gjort den til 10 så der ikke opstår nogen fejl
        this.add(renteField,1,5);
        renteField.setAlignment(Pos.BASELINE_CENTER);
        renteField.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD,13));
        renteField.setDisable(true);
        renteField.setOpacity(1);
        renteField.setStyle("-fx-text-fill: white;" +
                "-fx-prompt-text-fill: white; -fx-background-color: darkred");


        //månedlig udbetaling
        Label maanedligudbetaling = new Label("Månedligudbetaling");
        this.add(maanedligudbetaling, 0, 6);
        maanedligudbetaling.setAlignment(Pos.BASELINE_CENTER);
        maanedligudbetaling.setTextFill(Color.web("darkred"));
        maanedligudbetaling.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));

        TextField mdrUdbetalingField = new TextField("" + faktura.getMdrUdbetaling());
        this.add(mdrUdbetalingField,1,6);
        mdrUdbetalingField.setAlignment(Pos.BASELINE_CENTER);
        mdrUdbetalingField.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD,13));
        mdrUdbetalingField.setDisable(true);
        mdrUdbetalingField.setOpacity(1);
        mdrUdbetalingField.setStyle("-fx-text-fill: white;" +
                "-fx-prompt-text-fill: white; -fx-background-color: darkred");

        // totalpris
        Label totalpris = new Label("Totalpris");
        this.add(totalpris, 0, 7);
        totalpris.setAlignment(Pos.BASELINE_CENTER);
        totalpris.setTextFill(Color.web("darkred"));
        totalpris.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));

        TextField prisField = new TextField("" + faktura.getTotalPris());
        this.add(prisField,1,7);
        prisField.setAlignment(Pos.BASELINE_CENTER);
        prisField.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD,13));
        prisField.setDisable(true);
        prisField.setOpacity(1);
        prisField.setStyle("-fx-text-fill: white;" +
                "-fx-prompt-text-fill: white; -fx-background-color: darkred");

        this.setStyle("-fx-background-image: url(\"ferrari.jpg\"); -fx-background-size: 900 620;");




        //Dato
        Label datoLabel = new Label("Start dato");
        this.add(datoLabel, 0, 8);
        datoLabel.setAlignment(Pos.BASELINE_CENTER);
        datoLabel.setTextFill(Color.web("darkred"));
        datoLabel.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));

        this.dp = new DatePicker();
        dp.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Den valgte dato er: " + dp.getValue());
            }
        }));
        dp.setValue(LocalDate.now());
        dp.getEditor().clear();
        dp.setValue(null);
        this.add(dp,1,8);

        Button confirmBtn = new Button("Godkend");
        Font NBSize = new Font(15);
        confirmBtn.setFont(NBSize);
        this.add(confirmBtn, 2, 8);
        confirmBtn.setAlignment(Pos.BASELINE_RIGHT);
        confirmBtn.setOnAction(e-> {
            faktura.setKoebsdato(Date.from(dp.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            Kunde kundeVar = kundeExists(kunde);
            if(kundeVar == null) {
                try {
                    kunde.addToDatabase()
                    ;
                } catch (SQLException e2) {
                }
                faktura.setFakturaGodkendtByPris(faktura.getBilPris());
                faktura.setKunde_id(kunde.getKunde_id());
                try {
                    faktura.addToDatabase();
                } catch (SQLException e1) {
                }
            } else {
                faktura.setKunde_id(kundeVar.getKunde_id());
                try {
                    faktura.addToDatabase();
                } catch (SQLException e1) {
                }
            }
                StartSkaermController.i().clearAndStartNew(new KundeSkaerm());
            }
        );
        confirmBtn.setStyle("-fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-font-size: 16px; -fx-background-color: darkred");
    }
    public Kunde kundeExists(Kunde kunde) {

        ArrayList<Kunde> kundeList = ListMediator.getKundeListe();
        if(kundeList != null) {
            for (Kunde kundeVar : kundeList) {
                if (kunde.getNavn().equals(kundeVar.getNavn()) && kunde.getCpr().equals(kundeVar.getCpr())) {
                    return kundeVar;
                }
            }
        }
        return null;
    }
}
