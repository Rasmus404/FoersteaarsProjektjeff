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
        model.setTextFill(Color.web("#8B0000"));
        model.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));

        Label modelValgt = new Label(faktura.getModel());
        this.add(modelValgt, 1, 1);
        modelValgt.setAlignment(Pos.BASELINE_CENTER);
        modelValgt.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));

        //Bil pris
        Label bilPris = new Label("Bilpris:");
        this.add(bilPris, 0, 2);
        bilPris.setAlignment(Pos.BASELINE_CENTER);
        bilPris.setTextFill(Color.web("#8B0000"));
        bilPris.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));

        Label bilPrisField = new Label("" + faktura.getBilPris());
        this.add(bilPrisField,1,2);
        bilPrisField.setAlignment(Pos.BASELINE_CENTER);
        bilPrisField.setDisable(true);

        //udbetalingsprocent textfield
        Label udbetalingsprocent = new Label("Udbetalingsprocent:");
        this.add(udbetalingsprocent, 0, 3);
        udbetalingsprocent.setAlignment(Pos.BASELINE_CENTER);
        udbetalingsprocent.setTextFill(Color.web("#8B0000"));
        udbetalingsprocent.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));

        Label ubField = new Label("" + faktura.getUdbetalingsprocent());
        this.add(ubField,1,3);
        ubField.setAlignment(Pos.BASELINE_CENTER);
        ubField.setDisable(true);

        //loebetid
        Label loebetid = new Label("Løbetid:");
        this.add(loebetid, 0, 4);
        loebetid.setAlignment(Pos.BASELINE_CENTER);
        loebetid.setTextFill(Color.web("#8B0000"));
        loebetid.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));

        Label loebetidField = new Label("" + faktura.getLoebetid());
        this.add(loebetidField,1,4);
        loebetidField.setAlignment(Pos.BASELINE_CENTER);
        loebetidField.setDisable(true);

        //rentesats
        Label rentesats = new Label("Rentesats");
        this.add(rentesats, 0, 5);
        rentesats.setAlignment(Pos.BASELINE_CENTER);
        rentesats.setTextFill(Color.web("#8B0000"));
        rentesats.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));

        Label renteField = new Label("" + faktura.calcRentesats( )); //Har bare gjort den til 10 så der ikke opstår nogen fejl
        this.add(renteField,1,5);
        renteField.setAlignment(Pos.BASELINE_CENTER);
        renteField.setDisable(true);

        //månedlig udbetaling
        Label maanedligudbetaling = new Label("Månedligudbetaling");
        this.add(maanedligudbetaling, 0, 6);
        maanedligudbetaling.setAlignment(Pos.BASELINE_CENTER);
        maanedligudbetaling.setTextFill(Color.web("#8B0000"));
        maanedligudbetaling.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));

        Label mdrUdbetalingField = new Label("" + faktura.getMdrUdbetaling());
        this.add(mdrUdbetalingField,1,6);
        mdrUdbetalingField.setAlignment(Pos.BASELINE_CENTER);
        mdrUdbetalingField.setDisable(true);

        // totalpris
        Label totalpris = new Label("Totalpris");
        this.add(totalpris, 0, 7);
        totalpris.setAlignment(Pos.BASELINE_CENTER);
        totalpris.setTextFill(Color.web("#8B0000"));
        totalpris.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));

        Label prisField = new Label("" + faktura.getTotalPris());
        this.add(prisField,1,7);
        prisField.setAlignment(Pos.BASELINE_CENTER);
        prisField.setDisable(true);


        //Dato
        Label datoLabel = new Label("Start dato");
        this.add(datoLabel, 0, 8);
        datoLabel.setAlignment(Pos.BASELINE_CENTER);
        datoLabel.setTextFill(Color.web("#8B0000"));
        datoLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 13));

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
