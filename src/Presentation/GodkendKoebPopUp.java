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

public class GodkendKoebPopUp {

    IgangværendeSkaerm igangværendeSkaerm;

    public GodkendKoebPopUp(IgangværendeSkaerm igangværendeSkaerm){
        this.igangværendeSkaerm = igangværendeSkaerm;
        Stage GodkendPopup = new Stage();

        GodkendPopup.initModality(Modality.APPLICATION_MODAL);
        GodkendPopup.setTitle("Godkend køb");

        HBox root = new HBox();

        root.setAlignment(Pos.CENTER);


        Button jaKnap = new Button("Ja");
        //jaKnap.setOnAction(e -> );


        Button nejKnap = new Button("Nej");
        //nejKnap.setOnAction(e -> );

        root.getChildren().addAll(jaKnap,nejKnap);

        Image icon = new Image("file:logo.png");
        GodkendPopup.getIcons().add(icon);


        Scene scene1 = new Scene(root, 150, 100);
    }

}
