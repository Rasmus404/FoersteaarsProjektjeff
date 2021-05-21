package Presentation;

import Logic.Kunde;
import Logic.ListMediator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.SQLException;

public class KundeListePopup {

    OpretKoebSkaerm opretKoebSkaerm;

    public KundeListePopup(OpretKoebSkaerm opretKoebSkaerm) {
        this.opretKoebSkaerm = opretKoebSkaerm;
        Stage popupwindow = new Stage();

        //blocks touches outside the popupWindow
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Kundeliste");

        HBox root = new HBox();

        root.setAlignment(Pos.CENTER);

        TableView<Kunde> kundeTable = new TableView<>();
        TableColumn<Kunde, String> navnColumn = new TableColumn("Navn");
        TableColumn<Kunde, String> tlfColumn = new TableColumn("Telefon nr");
        TableColumn<Kunde, String> byColumn = new TableColumn("By");

        navnColumn.setCellValueFactory(new PropertyValueFactory<>("navn"));
        tlfColumn.setCellValueFactory(new PropertyValueFactory<>("tlf"));
        byColumn.setCellValueFactory(new PropertyValueFactory<>("by"));

        navnColumn.prefWidthProperty().bind(kundeTable.widthProperty().multiply(0.20));
        tlfColumn.prefWidthProperty().bind(kundeTable.widthProperty().multiply(0.20));
        byColumn.prefWidthProperty().bind(kundeTable.widthProperty().multiply(0.20)); //set to 20 from 30
        kundeTable.getColumns().add(navnColumn);
        kundeTable.getColumns().add(tlfColumn);
        kundeTable.getColumns().add(byColumn);
        kundeTable.setPrefSize(300, 250);

        ObservableList<Kunde> kundeListe = FXCollections.observableArrayList(ListMediator.getKundeListe());
        kundeTable.getItems().addAll(kundeListe);

        kundeTable.setOnMouseClicked(e -> {
                    if (kundeTable.getSelectionModel().getSelectedItem() != null) {
                        Kunde selectedKunde = kundeTable.getSelectionModel().getSelectedItem();
                        opretKoebSkaerm.setFields(selectedKunde);
                        popupwindow.close();
                    }
                }
        );

        Image icon = new Image("file:logo.png");
        popupwindow.getIcons().add(icon);

        root.getChildren().addAll(kundeTable);

        Scene scene1 = new Scene(root, 300, 250);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();
    }
}