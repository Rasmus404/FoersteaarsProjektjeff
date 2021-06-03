package Presentation;

import Logic.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
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
    BilerComboBox modelValg;
    DecimalFormat df;


    public LaaneaftaleSkaerm (Kunde kunde) {
        LaaneAftaleThread laaneAftaleThread = new LaaneAftaleThread(this);
        this.kunde = kunde;
        this.faktura = new Faktura(kunde);
        faktura.check(kunde);
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
        topLabel.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 25));
        this.add(topLabel, 0, 0, 2, 2);
        topLabel.setFill(Color.DARKRED);


        // model
        Label model = new Label("Model:");
        this.add(model, 0, 1);
        model.setAlignment(Pos.BASELINE_CENTER);
        model.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        //model
        model.setStyle("-fx-text-fill: darkred;");


        modelValg = new BilerComboBox(ListMediator.getBilListe());
        modelValg.setMinWidth(150);
        modelValg.setEditable(false);

        //model.setMaxWidth();
        this.add(modelValg, 1, 1);
        modelValg.setFocusTraversable(false);
        modelValg.setPromptText("Model");


        modelValg.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (newValue != null) {
                    updateCarValue();
                }
            }
        });
        // -----changeListener() -- laaneaftaleSkaerm.updateCarValue(); -----

        //udbetalingsprocent
        Label bilPris = new Label("Bil pris:");
        this.add(bilPris, 0, 2);
        bilPris.setAlignment(Pos.BASELINE_CENTER);
        bilPris.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        bilPris.setStyle("-fx-text-fill: darkred;");


        bilPrisField = new TextField();
        this.add(bilPrisField, 1, 2);
        bilPrisField.setAlignment(Pos.BASELINE_CENTER);
        bilPrisField.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        bilPrisField.setDisable(true);
        bilPrisField.setOpacity(1);
        bilPrisField.setStyle("-fx-text-fill: white;" +
                " -fx-background-color: darkred");


        //udbetalingsprocent
        Label udbetalingsprocent = new Label("Udbetalingsprocent:");
        this.add(udbetalingsprocent, 0, 3);
        udbetalingsprocent.setAlignment(Pos.BASELINE_CENTER);
        udbetalingsprocent.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        udbetalingsprocent.setStyle("-fx-text-fill: darkred;");


        udbetalingsProcentField = new TextField();
        this.add(udbetalingsProcentField, 1, 3);
        udbetalingsProcentField.setAlignment(Pos.BASELINE_CENTER);
        udbetalingsProcentField.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        udbetalingsProcentField.setDisable(false);
        udbetalingsProcentField.setStyle("-fx-text-fill: white;" +
                " -fx-background-color: #da614e");


        udbetalingsProcentField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue && !loebetidField.getText().isEmpty() && !carCheck()){
                   new valueService().start();
                }

            }
        });
        // ----changeListener() -- notify(fakturaValueListener) ----

        //løbetid
        Label loebetid = new Label("Løbetid:");
        this.add(loebetid, 0, 4);
        loebetid.setAlignment(Pos.BASELINE_CENTER);
        loebetid.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        loebetid.setStyle("-fx-text-fill: darkred;");


        loebetidField = new TextField();
        this.add(loebetidField, 1, 4);
        loebetidField.setAlignment(Pos.BASELINE_CENTER);
        loebetidField.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        loebetidField.setDisable(false);
        loebetidField.setStyle("-fx-text-fill: white;" +
                " -fx-background-color: #da614e");


        loebetidField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (oldValue && !udbetalingsProcentField.getText().isEmpty() && !carCheck()){
                    new valueService().start();
                }

            }
        });
        // ----changeListener() -- notify(fakturaValueListener) ----

        //rentesats
        Label rentesats = new Label("Rentesats");
        this.add(rentesats, 0, 5);
        rentesats.setAlignment(Pos.BASELINE_CENTER);
        rentesats.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        rentesats.setStyle("-fx-text-fill: darkred;");


        renteField = new TextField();
        this.add(renteField, 1, 5);
        renteField.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        renteField.setAlignment(Pos.BASELINE_CENTER);
        renteField.setDisable(true);
        renteField.setOpacity(1);
        renteField.setStyle("-fx-text-fill: white;" +
                " -fx-background-color: darkred");


        //månedlig udbetaling
        Label maanedligudbetaling = new Label("Månedligudbetaling");
        this.add(maanedligudbetaling, 0, 6);
        maanedligudbetaling.setAlignment(Pos.BASELINE_CENTER);
        maanedligudbetaling.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        maanedligudbetaling.setStyle("-fx-text-fill: darkred;");


        mdrUdbetalingField = new TextField();
        this.add(mdrUdbetalingField, 1, 6);
        mdrUdbetalingField.setAlignment(Pos.BASELINE_CENTER);
        mdrUdbetalingField.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        mdrUdbetalingField.setDisable(true);
        mdrUdbetalingField.setOpacity(1);
        mdrUdbetalingField.setStyle("-fx-text-fill: white;" +
                " -fx-background-color: darkred");


        // totalpris
        Label totalpris = new Label("Total Pris:");
        this.add(totalpris, 0, 7);
        totalpris.setAlignment(Pos.BASELINE_CENTER);
        totalpris.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        totalpris.setStyle("-fx-text-fill: darkred;");


        prisField = new TextField();
        this.add(prisField, 1, 7);
        prisField.setAlignment(Pos.BASELINE_CENTER);
        prisField.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 13));
        prisField.setDisable(true);
        prisField.setOpacity(1);
        prisField.setStyle("-fx-text-fill: white;" +
                " -fx-background-color: darkred");


        Button nextButton = new Button("Videre");
        Font NBSize = new Font(15);
        nextButton.setFont(NBSize);
        this.add(nextButton, 2, 7);
        nextButton.setAlignment(Pos.BASELINE_RIGHT);
        nextButton.setOnAction(e -> {
            //laaneAftaleThread.stop();
            StartSkaermController.i().pushNode(new FakturaGodkendelse(kunde, faktura));
        });
        //laaneAftaleThread.start();
        nextButton.setStyle("-fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-font-size: 16px; -fx-background-color: darkred;");

        this.setStyle("-fx-background-image: url(\"ferrari.jpg\"); -fx-background-size: 900 620;");


    }

    public void updateValues() {
        faktura.setLoebetid(Double.parseDouble(loebetidField.getText()));
        faktura.setUdbetalingsprocent(Double.parseDouble(udbetalingsProcentField.getText()));
        faktura.calcRentesats();
        renteField.setText(df.format(100 * faktura.calcRentesats()) + "%");
        mdrUdbetalingField.setText(df.format(faktura.getMdrUdbetaling()));
        prisField.setText(df.format(faktura.getTotalPris()));
    }

    public boolean fieldCheck() {

        return(udbetalingsProcentField.getText().isEmpty() || loebetidField.getText().isEmpty()); // ----changeListener() -- laaneaftaleSkaerm.updateValues() ----
    }
    public boolean carCheck() {
        return(modelValg.getValue() == null); // -----changeListener() -- laaneaftaleSkaerm.updateCarValue(); -----

    }

    public void updateCarValue() {
        faktura.setModel(modelValg.getBilNavn());
        faktura.setBilPris(modelValg.getBilPris());
        bilPrisField.setText(df.format(faktura.getBilPris()));
    }

    private class valueService extends Service<String> {
        private valueService() {
            setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    updateValues();
                }
            });
        }

        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws InterruptedException {
                    faktura.setDailyBankRate(new BankDailyRate().getDailyRate());
                    return "";
                }

            };
        }
    }

}

