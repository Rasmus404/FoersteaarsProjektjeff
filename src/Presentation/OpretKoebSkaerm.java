package Presentation;

import Datalayer.CreditRator;
import Datalayer.Rating;
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


public class OpretKoebSkaerm extends GridPane {

    Label errorLabel;
    Button kundeButton;
    KundeListePopup kundeListePopup;
    TextField firstname;
    TextField cpr;
    TextField telefon;
    TextField adresse;
    TextField by;
    TextField leveringsadresse;

    public OpretKoebSkaerm() {



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
        topLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 25));
        this.add(topLabel, 0, 0,2,2);



        // Fornavn

        firstname = new TextField();
        this.add(firstname, 0, 1);
        firstname.setAlignment(Pos.BASELINE_CENTER);
        firstname.setFocusTraversable(false);
        firstname.setPromptText("Navn");


        kundeButton = new Button("Kunde Liste");
        this.add(kundeButton,1,1);
        kundeButton.setOnAction(e -> kundeListePopup = new KundeListePopup(this));



        // cpr
        cpr = new TextField();
        this.add(cpr, 0, 2);
        cpr.setAlignment(Pos.BASELINE_CENTER);
        cpr.setFocusTraversable(false);
        cpr.setPromptText("CPR nr");

        //telefon
        telefon = new TextField();
        this.add(telefon, 0, 3);
        telefon.setAlignment(Pos.BASELINE_CENTER);
        telefon.setFocusTraversable(false);
        telefon.setPromptText("Telefon nr");

        //adresse
        adresse = new TextField();
        this.add(adresse, 0, 4);
        adresse.setAlignment(Pos.BASELINE_CENTER);
        adresse.setFocusTraversable(false);
        adresse.setPromptText("Adresse");

        by = new TextField();
        this.add(by, 0, 5);
        by.setAlignment(Pos.BASELINE_CENTER);
        by.setFocusTraversable(false);
        by.setPromptText("By");

        errorLabel = new Label();
        this.add(errorLabel, 2, 6);
        errorLabel.setTextFill(Color.web("#FF0000"));
        errorLabel.setAlignment(Pos.BASELINE_LEFT);

        //leveringsadresse
        leveringsadresse = new TextField();
        this.add(leveringsadresse, 0, 7);
        leveringsadresse.setAlignment(Pos.BASELINE_CENTER);
        leveringsadresse.setFocusTraversable(false);
        leveringsadresse.setPromptText("Leveringsadresse");
        leveringsadresse.setDisable(true);


        CheckBox check = new CheckBox("Ønsket levering");
        this.add(check, 1, 7);
        check.setAlignment(Pos.BOTTOM_RIGHT);
        check.setOnAction(e-> {
            if (check.isSelected()) {
                leveringsadresse.setDisable(false);}
            else {
                leveringsadresse.clear();
                leveringsadresse.setDisable(true);
            }
        });

        Button nextButton = new Button("Videre");
        Font NBSize = new Font(15);
        nextButton.setFont(NBSize);
        this.add(nextButton, 2, 7);
        nextButton.setAlignment(Pos.BASELINE_RIGHT);
        nextButton.setOnAction(e ->{
            if(kundeIsValid()) {
            Kunde kunde = new Kunde();
            kunde.setNavn(firstname.getText());
            kunde.setCpr(cpr.getText());
            kunde.setTlf(telefon.getText());
            kunde.setAdresse(adresse.getText());
            kunde.setBy(by.getText());


            if (check.isSelected() && leveringsadresse.getText().isEmpty()) {
                kunde.setLeveringOenskes();
            } else if(check.isSelected() && !leveringsadresse.getText().isEmpty()){
                kunde.setLeveringsadresse(leveringsadresse.getText());
            } else {
                kunde.setLeveringsadresse("");
            }
                StartSkaerm.instance().pushNode(new LaaneaftaleSkaerm(kunde));
            }
            }
        );
    }
    public void warningOnRKI(){
        errorLabel.setText("Kundens kreditværdighed er for ringe");
    }

    public void errorOnFields(){
        errorLabel.setText("Værdier er ikke indtastet korrekt");
    }

    public void setFields(Kunde kunde) {
        firstname.setText(kunde.getNavn());
        cpr.setText(kunde.getCpr());
        telefon.setText(kunde.getTlf());
        adresse.setText(kunde.getAdresse());
        by.setText(kunde.getBy());
        leveringsadresse.setText(kunde.getLeveringsadresse());
    }
    public boolean kundeIsValid() {
        if (firstname.getText().isEmpty() ||
                !cpr.getText().matches("^\\d{10}$") ||  //tjekker fra første karakter om der er 10 tal frem til den sidste
                telefon.getText().isEmpty() ||
                adresse.getText().isEmpty() ||
                by.getText().isEmpty()
        ) {
            errorOnFields();
            return false;
        } else if (CreditRator.i().rate(this.cpr.getText()) == Rating.D) {
            warningOnRKI();
            return false;
        }
        return true;
    }
}

