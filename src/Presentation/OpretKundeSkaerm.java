package Presentation;

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
import Logic.Kunde;


public class OpretKundeSkaerm extends GridPane {

    Label errorLabel;
    Label cprLabel;
    Button kundeButton;
    KundeListePopup kundeListePopup;
    TextField firstname;
    TextField cpr;
    TextField telefon;
    TextField adresse;
    TextField by;
    TextField leveringsadresse;
    Kunde kunde;


    public OpretKundeSkaerm() {


        this.setAlignment(Pos.TOP_LEFT);
        this.setHgap(25);
        this.setVgap(30);
        this.setPadding(new Insets(5, 15, 5, 35));
        this.getRowConstraints().add(new RowConstraints(75));
        this.getColumnConstraints().add(new ColumnConstraints(150));
        this.getColumnConstraints().add(new ColumnConstraints(150));
        this.getColumnConstraints().add(new ColumnConstraints(260));


        Text topLabel = new Text("Opret nyt køb");
        topLabel.setFill(Color.DARKRED);
        topLabel.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 25));
        this.add(topLabel, 0, 0, 2, 2);


        // Fornavn

        firstname = new TextField();
        this.add(firstname, 0, 1);
        firstname.setAlignment(Pos.BASELINE_CENTER);
        firstname.setFocusTraversable(false);
        firstname.setPromptText("Navn");

        firstname.setStyle("-fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-prompt-text-fill: white; -fx-font-size: 13px; -fx-background-color: darkred");


        kundeButton = new Button("Kunde Liste");
        this.add(kundeButton, 1, 1);
        kundeButton.setOnAction(e -> kundeListePopup = new KundeListePopup(this));
        kundeButton.setStyle("-fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-font-size: 13px; -fx-background-color: darkred");


        // cpr
        cpr = new TextField();
        this.add(cpr, 0, 2);
        cpr.setAlignment(Pos.BASELINE_CENTER);
        cpr.setFocusTraversable(false);
        cpr.setPromptText("CPR nr");
        cpr.setStyle("-fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-prompt-text-fill: white; -fx-font-size: 13px; -fx-background-color: darkred");
        cpr.setOpacity(1);


        cpr.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (cpr.getText().matches("^\\d{10}$")) {
                    new CprService(cpr, cprLabel).start();
                }

            }
        });


        cprLabel = new Label();
        this.add(cprLabel, 1,2);
        cprLabel.setStyle("-fx-text-fill: white");


        //telefon
        telefon = new TextField();
        this.add(telefon, 0, 3);
        telefon.setAlignment(Pos.BASELINE_CENTER);
        telefon.setFocusTraversable(false);
        telefon.setPromptText("Telefon nr");
        telefon.setStyle("-fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-prompt-text-fill: white; -fx-font-size: 13px; -fx-background-color: darkred");


        //adresse
        adresse = new TextField();
        this.add(adresse, 0, 4);
        adresse.setAlignment(Pos.BASELINE_CENTER);
        adresse.setFocusTraversable(false);
        adresse.setPromptText("Adresse");
        adresse.setStyle("-fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-prompt-text-fill: white; -fx-font-size: 13px; -fx-background-color: darkred");


        by = new TextField();
        this.add(by, 0, 5);
        by.setAlignment(Pos.BASELINE_CENTER);
        by.setFocusTraversable(false);
        by.setPromptText("By");
        by.setStyle("-fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-prompt-text-fill: white; -fx-font-size: 13px; -fx-background-color: darkred");


        errorLabel = new Label();
        this.add(errorLabel, 2, 6);
        errorLabel.setTextFill(Color.WHITE);
        errorLabel.setAlignment(Pos.BASELINE_LEFT);
        errorLabel.setStyle("-fx-font-weight: bold;" +
                "-fx-font-size: 13px;");


        //leveringsadresse
        leveringsadresse = new TextField();
        this.add(leveringsadresse, 0, 7);
        leveringsadresse.setAlignment(Pos.BASELINE_CENTER);
        leveringsadresse.setFocusTraversable(false);
        leveringsadresse.setPromptText("Leveringsadresse");
        leveringsadresse.setDisable(true);
        leveringsadresse.setStyle("-fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-prompt-text-fill: white; -fx-font-size: 13px; -fx-background-color: darkred");

//        this.setStyle("-fx-background-image: url(\"ferrari.jpg\"); -fx-background-size: 900 400;");
        this.setStyle("-fx-background-image: url(\"ferrari.jpg\"); -fx-background-size: 900 620;");


        CheckBox check = new CheckBox("Ønsket levering");
        this.add(check, 1, 7);
        check.setAlignment(Pos.BOTTOM_RIGHT);
        check.setOnAction(e -> {
            if (check.isSelected()) {
                leveringsadresse.setDisable(false);
            } else {
                leveringsadresse.clear();
                leveringsadresse.setDisable(true);
            }
        });

        Button nextButton = new Button("Videre");
        Font NBSize = new Font(15);
        nextButton.setFont(NBSize);
        this.add(nextButton, 2, 7);
        nextButton.setAlignment(Pos.BASELINE_RIGHT);
        //Victor
        nextButton.setOnAction(e -> {
                    if (kundeIsValid()) {
                        kundeInit();
                        kunde.setNavn(firstname.getText());
                        kunde.setCpr(cpr.getText());
                        kunde.setTlf(telefon.getText());
                        kunde.setAdresse(adresse.getText());
                        kunde.setBy(by.getText());
                        kunde.setKreditVaerdighed(cprLabel.getText());

                        if (check.isSelected() && leveringsadresse.getText().isEmpty()) {
                            kunde.setLeveringOenskes();
                        } else if (check.isSelected() && !leveringsadresse.getText().isEmpty()) {
                            kunde.setLeveringsadresse(leveringsadresse.getText());
                        } else {
                            kunde.setLeveringsadresse("");
                        }
                        StartSkaermController.i().pushNode(new LaaneaftaleSkaerm(kunde));
                    }
                }
        );
        nextButton.setStyle("-fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-font-size: 16px; -fx-background-color: darkred");


    }

    public void warningOnRKI() {
        errorLabel.setText("Kundens kreditværdighed er for ringe");
    }

    public void errorOnFields() {
        errorLabel.setText("Værdier er ikke indtastet korrekt");
    }

    //Yusuf
    public void setFields(Kunde kunde) {
        firstname.setText(kunde.getNavn());
        cpr.setText(kunde.getCpr());
        telefon.setText(kunde.getTlf());
        adresse.setText(kunde.getAdresse());
        by.setText(kunde.getBy());
        leveringsadresse.setText(kunde.getLeveringsadresse());
        new CprService(cpr, cprLabel).start();
    }

    //Victor
    public boolean kundeIsValid() {
        if (firstname.getText().isEmpty() ||
                !cpr.getText().matches("^\\d{10}$") ||
                telefon.getText().isEmpty() ||
                adresse.getText().isEmpty() ||
                by.getText().isEmpty()
        ) {
            errorOnFields();
            return false;
        } else if (cprLabel.getText().equals("Kreditværdighed er: D") /*CreditRator.i().rate(this.cpr.getText()) == Rating.D*/) {
            warningOnRKI();
            return false;
        }
        return true;
    }

    private void kundeInit() {
        if (kunde == null) {
            kunde = new Kunde();
        }
    }

    //Victor
    private class CprService extends Service<String> {
        TextField cprField;


        private CprService(TextField cprTextField, Label cprLabel) {
            this.cprField = cprTextField;

            setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                     cprLabel.setText("Kreditværdighed er: " +  event.getSource().getValue().toString());
                }
            });
        }

        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws InterruptedException {
                    kundeInit();
                    kunde.setCpr(cprField.getText());
                    kunde.checkMyRKI(kunde.getCpr());
                    return kunde.getKreditVaerdighed();
                }

            };
        }
    }

}

