package Presentation;

import Logic.Kunde;
import Logic.ListMediator;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.sql.SQLException;
import java.util.ArrayList;

///*

public class KundeSkaerm extends GridPane {

    public KundeSkaerm() {

        this.setHgap(20);
        this.setVgap(3);
        this.setPadding(new Insets(3, 3, 3, 3));
        this.getColumnConstraints().add(new ColumnConstraints(200));
        this.getColumnConstraints().add(new ColumnConstraints(160));
        this.getColumnConstraints().add(new ColumnConstraints(200));

        Text topLabel = new Text("Kunde");
        topLabel.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 25));
        topLabel.setFill(Color.DARKRED);
        this.add(topLabel, 1, 0,1,1);
        topLabel.setTextAlignment(TextAlignment.CENTER);

        Button Soeg = new Button("Søg");
        Font SoegSize = new Font(10);
        Soeg.setFont(SoegSize);
        this.add(Soeg, 1, 1);
        Soeg.setAlignment(Pos.TOP_RIGHT);


        TableView<Kunde> kundeTable = new TableView<>();
        TableColumn<Kunde, String> navnColumn = new TableColumn("Navn");
        TableColumn<Kunde, String> tlfColumn = new TableColumn("Telefon nr");
        TableColumn<Kunde, String> byColumn = new TableColumn("By");

        navnColumn.setCellValueFactory(new PropertyValueFactory<>("navn"));
        tlfColumn.setCellValueFactory(new PropertyValueFactory<>("tlf"));
        byColumn.setCellValueFactory(new PropertyValueFactory<>("by"));

        navnColumn.setStyle("-fx-alignment: CENTER;");
        tlfColumn.setStyle("-fx-alignment: CENTER;");
        byColumn.setStyle( "-fx-alignment: CENTER;");

        this.setStyle("-fx-background-image: url(\"ferrari.jpg\"); -fx-background-size: 900 620;");



        navnColumn.prefWidthProperty().bind(kundeTable.widthProperty().multiply(0.30));
        tlfColumn.prefWidthProperty().bind(kundeTable.widthProperty().multiply(0.30));
        byColumn.prefWidthProperty().bind(kundeTable.widthProperty().multiply(0.30));
        kundeTable.getColumns().add(navnColumn);
        kundeTable.getColumns().add(tlfColumn);
        kundeTable.getColumns().add(byColumn);
        kundeTable.setPrefSize(500, 450);

        ObservableList<Kunde> kundeListe = FXCollections.observableArrayList(ListMediator.getKundeListe());
        kundeTable.getItems().addAll(kundeListe);
        this.add(kundeTable, 0, 2,3,1);


        FilteredList<Kunde> flKunde = new FilteredList(kundeListe, p -> true); // Flytter data til en filtered list
        kundeTable.setItems(flKunde); //Sætter tableviewets items ved brug af vores filtered list

        ChoiceBox<String> choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("Navn", "Telefon nr", "By");
        choiceBox.setValue("Navn"); //det den starter med

        TextField soegefelt = new TextField();
        soegefelt.setPromptText("Søgefelt");
        this.add(soegefelt, 0, 1);
        soegefelt.setAlignment(Pos.TOP_LEFT);
        soegefelt.textProperty().addListener((obs, oldValue, newValue) -> {
            switch(choiceBox.getValue()){

                case "Navn":
                    flKunde.setPredicate(p -> p.getNavn().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;

                case "Telefon nr":
                    flKunde.setPredicate(p -> p.getTlf().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;

                case "By":
                    flKunde.setPredicate(p -> p.getBy().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
            }
        });

        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {//resetter vores table og textfield når noget nyt er selected
            if (newVal != null) {
                soegefelt.setText(""); //soegefelt.setText("");
            }
        });
        this.add(choiceBox,1,1);




        this.setAlignment(Pos.CENTER_LEFT);

        kundeTable.setOnMouseClicked(event -> {
            if (kundeTable.getSelectionModel().getSelectedItem() != null) {
                Kunde selectedKunde = kundeTable.getSelectionModel().getSelectedItem();
                StartSkaermController.i().pushNode(new KundeInfoSkaerm(selectedKunde));
            }
        }
        );
    }

}


