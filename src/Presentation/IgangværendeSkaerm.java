package Presentation;

import Logic.IgangvaerendeKoeb;
import Logic.Kunde;
import Logic.ListMediator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import Logic.LoginChecker;
import Logic.Faktura;

import java.sql.SQLException;

public class IgangværendeSkaerm extends GridPane {

    Faktura selectedFaktura;

    public IgangværendeSkaerm() {

        this.setAlignment(Pos.CENTER);
        this.setHgap(20);
        this.setVgap(3);
        this.setPadding(new Insets(3, 3, 3, 3));
        this.getColumnConstraints().add(new ColumnConstraints(200));
        this.getColumnConstraints().add(new ColumnConstraints(160));
        this.getColumnConstraints().add(new ColumnConstraints(200));

        Text topLabel = new Text("Igangværende køb");
        topLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 25));
        topLabel.setFill(Color.DARKRED);
        this.add(topLabel, 1, 0, 1, 1);
        topLabel.setTextAlignment(TextAlignment.CENTER);

        Button Soeg = new Button("Søg");
        Font SoegSize = new Font(10);
        Soeg.setFont(SoegSize);
        this.add(Soeg, 1, 1);
        Soeg.setAlignment(Pos.TOP_RIGHT);

        if(LoginChecker.getAdminStatus()){
            Button godkendButton = new Button();
            this.add(godkendButton, 2, 1);
            godkendButton.setOnAction(ev->  new GodkendKoebPopUp(this){
                //dialogBox.godkend(selectedFaktura);
            });
        }


        TableView<IgangvaerendeKoeb> kundeTable = new TableView<>(); // Kundens navn, Bil model, Dato, Status
        TableColumn<IgangvaerendeKoeb, String> navnColumn = new TableColumn("Navn");
        TableColumn<IgangvaerendeKoeb, String> bilColumn = new TableColumn("Bil model");
        TableColumn<IgangvaerendeKoeb, String> datoColumn = new TableColumn("Start dato");
        TableColumn<IgangvaerendeKoeb, String> statusColumn = new TableColumn("Status");

        navnColumn.setCellValueFactory(new PropertyValueFactory<>("navn"));
        bilColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        datoColumn.setCellValueFactory(new PropertyValueFactory<>("startDato"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        navnColumn.setStyle("-fx-alignment: CENTER;");
        bilColumn.setStyle("-fx-alignment: CENTER;");
        datoColumn.setStyle("-fx-alignment: CENTER;");
        statusColumn.setStyle("-fx-alignment: CENTER;");


        navnColumn.prefWidthProperty().bind(kundeTable.widthProperty().multiply(0.23));
        bilColumn.prefWidthProperty().bind(kundeTable.widthProperty().multiply(0.23));
        datoColumn.prefWidthProperty().bind(kundeTable.widthProperty().multiply(0.23));
        statusColumn.prefWidthProperty().bind(kundeTable.widthProperty().multiply(0.23));  //
        kundeTable.getColumns().add(navnColumn);
        kundeTable.getColumns().add(bilColumn);
        kundeTable.getColumns().add(datoColumn);
        kundeTable.getColumns().add(statusColumn);
        kundeTable.setPrefSize(560, 450);

        ObservableList<IgangvaerendeKoeb> obsListe = FXCollections.observableArrayList(ListMediator.getIgangværendeKoebs());
        kundeTable.getItems().addAll(obsListe);
        this.add(kundeTable, 0, 2, 3, 1);


        this.setStyle("-fx-background-image: url(\"ferrari.jpg\"); -fx-background-size: 900 620;");

//---------------------------------------------------
        FilteredList<IgangvaerendeKoeb> flKunde = new FilteredList(obsListe, p -> true); // Flytter data til en filtered list
        kundeTable.setItems(flKunde); //Sætter tableviewets items ved brug af vores filtered list

        ChoiceBox<String> choiceBox = new ChoiceBox();  //"Navn", "Bil model", "Start dato", "Status";
        choiceBox.getItems().addAll("Navn", "Bil model", "Start dato", "Status");
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

                case "Bil model":
                    flKunde.setPredicate(p -> p.getModel().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;

                case "Start dato":
                    flKunde.setPredicate(p -> p.getStartDato().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;

                case "Status":
                    flKunde.setPredicate(p -> p.getStatus().toLowerCase().contains(newValue.toLowerCase().trim()));
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

//---------------------------------------------------



        Button nextButton = new Button("Videre");
        Font NBSize = new Font(15);
        nextButton.setFont(NBSize);
        this.add(nextButton, 1, 2);
        nextButton.setOnAction(e -> StartSkaermController.i().pushNode(new KundeInfoSkaerm(null)));
    }
}
