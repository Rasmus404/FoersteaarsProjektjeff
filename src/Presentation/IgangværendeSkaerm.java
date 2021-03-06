package Presentation;

import Logic.IgangvaerendeKoeb;
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

//Oliver og Yusuf
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


        TableView<IgangvaerendeKoeb> igangvaerendeTable = new TableView<>();
        TableColumn<IgangvaerendeKoeb, String> navnColumn = new TableColumn("Navn");
        TableColumn<IgangvaerendeKoeb, String> bilColumn = new TableColumn("Bil model");
        TableColumn<IgangvaerendeKoeb, String> datoColumn = new TableColumn("Start dato");
        TableColumn<IgangvaerendeKoeb, String> statusColumn = new TableColumn("Status");

        igangvaerendeTable.setStyle("-fx-selection-bar: red; -fx-selection-bar-non-focused: salmon; -fx-background-color: darkred; " +
                "-fx-text-fill: red;");

        navnColumn.setCellValueFactory(new PropertyValueFactory<>("navn"));
        bilColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        datoColumn.setCellValueFactory(new PropertyValueFactory<>("startDato"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        navnColumn.setStyle("-fx-alignment: CENTER;");
        bilColumn.setStyle("-fx-alignment: CENTER;");
        datoColumn.setStyle("-fx-alignment: CENTER;");
        statusColumn.setStyle("-fx-alignment: CENTER;");


        navnColumn.prefWidthProperty().bind(igangvaerendeTable.widthProperty().multiply(0.23));
        bilColumn.prefWidthProperty().bind(igangvaerendeTable.widthProperty().multiply(0.23));
        datoColumn.prefWidthProperty().bind(igangvaerendeTable.widthProperty().multiply(0.23));
        statusColumn.prefWidthProperty().bind(igangvaerendeTable.widthProperty().multiply(0.23));
        igangvaerendeTable.getColumns().add(navnColumn);
        igangvaerendeTable.getColumns().add(bilColumn);
        igangvaerendeTable.getColumns().add(datoColumn);
        igangvaerendeTable.getColumns().add(statusColumn);
        igangvaerendeTable.setPrefSize(560, 450);

        ObservableList<IgangvaerendeKoeb> obsListe = FXCollections.observableArrayList(ListMediator.getIgangværendeKoebs());
        igangvaerendeTable.getItems().addAll(obsListe);
        this.add(igangvaerendeTable, 0, 2, 3, 1);

        this.setStyle("-fx-background-image: url(\"ferrari.jpg\"); -fx-background-size: 900 620;");

        if (LoginChecker.isAdmin) {
            Button godkendButton = new Button("Godkend");
            this.add(godkendButton, 2, 1);
            godkendButton.setOnAction(e -> {
                if (igangvaerendeTable.getSelectionModel().getSelectedItem() != null) {
                    selectedFaktura = ListMediator.getFakturaById(igangvaerendeTable.getSelectionModel().getSelectedItem().getFaktura_id()).get(0);
                    selectedFaktura.setFakturaGodkendt(true);
                    try {
                        selectedFaktura.updateFakturaGodkendelseInDatabase();
                    } catch (SQLException ex) {
                    }
                    StartSkaermController.i().clearAndStartNew(new IgangværendeSkaerm());
                }
            });
        }


        FilteredList<IgangvaerendeKoeb> filteredList = new FilteredList(obsListe, p -> true);
        igangvaerendeTable.setItems(filteredList);

        ChoiceBox<String> choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("Navn", "Bil model", "Start dato", "Status");
        choiceBox.setValue("Navn");

        TextField soegefelt = new TextField();
        soegefelt.setPromptText("Søgefelt");
        this.add(soegefelt, 0, 1);
        soegefelt.setAlignment(Pos.TOP_LEFT);
        soegefelt.textProperty().addListener((obs, oldValue, newValue) -> {
            switch (choiceBox.getValue()) {

                case "Navn":
                    filteredList.setPredicate(p -> p.getNavn().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;

                case "Bil model":
                    filteredList.setPredicate(p -> p.getModel().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;

                case "Start dato":
                    filteredList.setPredicate(p -> p.getStartDato().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;

                case "Status":
                    filteredList.setPredicate(p -> p.getStatus().toLowerCase().contains(newValue.toLowerCase().trim()));
                    break;
            }
        });

        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal)
                -> {
            if (newVal != null) {
                soegefelt.setText("");
            }
        });
        this.add(choiceBox, 1, 1);


    }
}

