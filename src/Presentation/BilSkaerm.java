package Presentation;

import Logic.Bil;
import Logic.ListMediator;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.control.*;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import java.sql.SQLException;
import java.util.ArrayList;

//Oliver og Yusuf
public class BilSkaerm extends GridPane {
    Bil bil;
    TextField modelText;
    TextField prisText;
    Label errorLabel;
    public int selectedIndex = -1;

    public BilSkaerm() {

        this.setHgap(10);
        this.setVgap(3);
        this.setPadding(new Insets(3, 20, 10, 20));
        this.getColumnConstraints().add(new ColumnConstraints(50));
        this.getColumnConstraints().add(new ColumnConstraints(110));
        this.getColumnConstraints().add(new ColumnConstraints(40));
        this.getColumnConstraints().add(new ColumnConstraints(110));
        this.getColumnConstraints().add(new ColumnConstraints(70));


        Text topLabel = new Text("Bil liste");
        topLabel.setFont(Font.font("Tahoma", FontWeight.EXTRA_BOLD, 25));
        topLabel.setFill(Color.DARKRED);
        this.add(topLabel, 3, 0, 1, 1);
        topLabel.setTextAlignment(TextAlignment.CENTER);

        errorLabel = new Label();
        this.add(errorLabel, 4, 0, 2, 1);


        TableView<Bil> bilTable = new TableView<Bil>();
        TableColumn navnColumn = new TableColumn("Model");
        TableColumn prisColumn = new TableColumn("Pris");
        navnColumn.setCellValueFactory(new PropertyValueFactory<>("bilNavn"));
        prisColumn.setCellValueFactory(new PropertyValueFactory<>("pris"));
        navnColumn.prefWidthProperty().bind(bilTable.widthProperty().multiply(0.5));
        prisColumn.prefWidthProperty().bind(bilTable.widthProperty().multiply(0.5));
        bilTable.getColumns().add(navnColumn);
        bilTable.getColumns().add(prisColumn);
        bilTable.setPrefSize(500, 350);

        this.setStyle("-fx-background-image: url(\"ferrari.jpg\"); -fx-background-size: 900 620;");
        bilTable.setStyle("-fx-selection-bar: red; -fx-selection-bar-non-focused: salmon; -fx-background-color: darkred; " +
                "-fx-text-fill: red;");

        bilTable.setOnMouseClicked(event -> {
            Bil selectedBil = bilTable.getSelectionModel().getSelectedItem();
            selectedIndex = bilTable.getSelectionModel().getSelectedIndex();
            modelText.setText(selectedBil.getBilNavn());
            prisText.setText("" + selectedBil.getPris());
        });


        if (ListMediator.getBilListe() != null) {
            bilTable.getItems().addAll(FXCollections.observableArrayList(ListMediator.getBilListe()));
        }


        this.add(bilTable, 0, 3, 7, 1);

        Label modelLabel = new Label("Model");
        this.add(modelLabel, 0, 2);
        modelLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 10));

        modelText = new TextField();
        this.add(modelText, 1, 2);

        Label prisLabel = new Label("Pris");
        this.add(prisLabel, 2, 2);
        prisLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 10));

        prisText = new TextField();
        this.add(prisText, 3, 2);

        Button add = new Button("Tilføj bil");
        Font addSize = new Font(10);
        add.setFont(addSize);
        this.add(add, 4, 2);
        add.setOnAction(e -> {
            if (bilIsValid()) {
                Bil bil = new Bil(modelText.getText(), Double.parseDouble(prisText.getText()));
                try {
                    bil.addToDatabase();
                } catch (SQLException e1) {
                }
                bilTable.getItems().clear();
                bilTable.getItems().addAll(FXCollections.observableArrayList(ListMediator.getBilListe()));
            }
        });

        Button delete = new Button("Slet bil");
        Font deleteSize = new Font(10);
        delete.setFont(deleteSize);
        this.add(delete, 5, 2);
        delete.setOnAction(e -> {
            try {
                bil.deleteFromDatabase();
            } catch (SQLException e1) {
            }
            bilTable.getItems().clear();
            bilTable.getItems().addAll(FXCollections.observableArrayList(ListMediator.getBilListe()));
        });

        this.setAlignment(Pos.CENTER_LEFT);


        bilTable.setOnMouseClicked(event -> {
            if (bilTable.getSelectionModel().getSelectedItem() != null) {
                this.bil = bilTable.getSelectionModel().getSelectedItem();
            }
        });
    }


    public void errorOnFields() {
        errorLabel.setText("Værdier er ikke indtastet korrekt");
    }

    public void warningBilExists() {
        errorLabel.setText("Bilen er allerede i systemet");
    }

    public boolean bilExists() {
        ArrayList<Bil> bilList = ListMediator.getBilListe();
        if (bilList != null) {
            for (Bil bilVar : bilList) {
                if (modelText.getText().equals(bilVar.getBilNavn()) && Double.parseDouble(prisText.getText()) == bilVar.getPris()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean bilIsValid() {

        if (modelText.getText().isEmpty() || !prisText.getText().matches("^\\d+$")) {
            errorOnFields();
            return false;
        } else if (bilExists()) {
            warningBilExists();
            return false;
        }
        return true;


    }

}


