package Presentation;

import Logic.Faktura;
import Logic.ListMediator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import Logic.Kunde;

import java.sql.SQLException;

public class KundeInfoSkaerm extends GridPane {
    Kunde kunde;
    public KundeInfoSkaerm(Kunde kunde) {
        this.kunde = kunde;

        this.setAlignment(Pos.CENTER);
        this.setHgap(20);
        this.setVgap(20);
        this.setPadding(new Insets(3, 20, 10, 35));
        this.getColumnConstraints().add(new ColumnConstraints(90));
        this.getColumnConstraints().add(new ColumnConstraints(160));
        this.getColumnConstraints().add(new ColumnConstraints(200));

        TextField kundeNavn = new TextField();
        this.add(kundeNavn, 1, 0);
        kundeNavn.setAlignment(Pos.TOP_RIGHT);
        kundeNavn.setDisable(true);
        kundeNavn.setOpacity(1);
        kundeNavn.setText(kunde.getNavn());
        kundeNavn.setStyle("-fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-prompt-text-fill: white; -fx-font-size: 13px; -fx-background-color: darkred");



        Label navnLabel = new Label("Navn:");
        navnLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        this.add(navnLabel, 0, 0);
        navnLabel.setAlignment(Pos.TOP_LEFT);
        navnLabel.setStyle("-fx-text-fill: darkred; -fx-font-weight: bold;");


        TextField kundeTelefon = new TextField();
        this.add(kundeTelefon, 1, 1);
        kundeTelefon.setAlignment(Pos.TOP_RIGHT);
        kundeTelefon.setDisable(true);
        kundeTelefon.setOpacity(1);
        kundeTelefon.setText(kunde.getTlf());
        kundeTelefon.setStyle("-fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-prompt-text-fill: white; -fx-font-size: 13px; -fx-background-color: darkred");



        Label telefonLabel = new Label("Telefon:");
        telefonLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        this.add(telefonLabel, 0, 1);
        telefonLabel.setAlignment(Pos.TOP_LEFT);
        telefonLabel.setStyle("-fx-text-fill: darkred; -fx-font-weight: bold;");

        TextField kundeAdresse = new TextField();
        this.add(kundeAdresse, 1, 2);
        kundeAdresse.setAlignment(Pos.TOP_RIGHT);
        kundeAdresse.setDisable(true);
        kundeAdresse.setOpacity(1);
        kundeAdresse.setText(kunde.getAdresse());
        kundeAdresse.setStyle("-fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-prompt-text-fill: white; -fx-font-size: 13px; -fx-background-color: darkred");



        Label adresseLabel = new Label("Adresse:");
        adresseLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 15));
        this.add(adresseLabel, 0, 2);
        adresseLabel.setAlignment(Pos.TOP_LEFT);
        adresseLabel.setStyle("-fx-text-fill: darkred; -fx-font-weight: bold;");


        Button sletbtn = new Button("Slet");
        this.add(sletbtn,2,0);
        sletbtn.setOnAction(e -> {
            try {
                kunde.deleteFromDatabase();
            } catch (SQLException e1) {
            }
            StartSkaermController.i().clearAndStartNew(new KundeSkaerm());
        } );

        sletbtn.setStyle("-fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-font-size: 12px; -fx-background-color: darkred");


        this.setStyle("-fx-background-image: url(\"ferrari.jpg\"); -fx-background-size: 900 620;");


        TableView<Faktura> kundeHistorik = new TableView();
        TableColumn<Faktura, String> Model = new TableColumn("Model");
        TableColumn<Faktura, Double> Koebspris = new TableColumn("Købsbilpris");
        TableColumn<Faktura, Double> Koebsdato = new TableColumn("Købsdato");

        Model.setCellValueFactory(new PropertyValueFactory<>("model"));
        Koebspris.setCellValueFactory(new PropertyValueFactory<>("bilPris"));
        Koebsdato.setCellValueFactory(new PropertyValueFactory<>("koebsdato")); //ændres købsdato

        Model.prefWidthProperty().bind(kundeHistorik.widthProperty().multiply(0.30));
        Koebspris.prefWidthProperty().bind(kundeHistorik.widthProperty().multiply(0.30));
        Koebsdato.prefWidthProperty().bind(kundeHistorik.widthProperty().multiply(0.30));

        kundeHistorik.getColumns().add(Model);
        kundeHistorik.getColumns().add(Koebspris);
        kundeHistorik.getColumns().add(Koebsdato);
        
        kundeHistorik.setPrefSize(560, 340);

        ObservableList<Faktura> fakturaObs = FXCollections.observableArrayList(ListMediator.getFakturaListeByKunde(kunde));
        kundeHistorik.getItems().addAll(fakturaObs);
        this.add(kundeHistorik, 0, 3, 3, 1);

        kundeHistorik.setOnMouseClicked(event -> {
                    if (kundeHistorik.getSelectionModel().getSelectedItem() != null) {
                        Faktura selectedFaktura = kundeHistorik.getSelectionModel().getSelectedItem();
                        StartSkaermController.i().pushNode(new KvitteringSkaerm(kunde, selectedFaktura));
                    }
                }
        );

    }
}
