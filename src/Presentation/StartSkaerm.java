package Presentation;

import Logic.LoginChecker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class StartSkaerm extends BorderPane{

    private Stack<Node> stack = new Stack<>();
    private VBox leftBorder;
    Button opret, kunde, igangværende, bilListe, tilbage;
    Stage stage;






     StartSkaerm(Stage stage) {
        this.stage = stage;
        StartSkaermController.i().setStartSkaerm(this);

        leftBorder = new VBox();
        leftBorder.setPrefWidth(750 / 4);
        leftBorder.setStyle("-fx-background-color: #8B0000");
        leftBorder.setAlignment(Pos.TOP_CENTER);
        leftBorder.setPadding(new Insets(5, 5, 5, 5));
        leftBorder.setSpacing(20);


        opret = new Button("Opret");
        opret.setOnAction(e -> StartSkaermController.i().pushNode(new OpretKundeSkaerm()));
        opret.setStyle("-fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-font-size: 12px; -fx-background-color: black");


        kunde = new Button("Kunder");
        kunde.setOnAction(e -> StartSkaermController.i().pushNode(new KunderSkaerm()));
        kunde.setStyle("-fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-font-size: 12px; -fx-background-color: black");


        igangværende = new Button("Igangværende");
        igangværende.setOnAction(e -> StartSkaermController.i().pushNode(new IgangværendeSkaerm()));
        igangværende.setStyle("-fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-font-size: 12px; -fx-background-color: black");


        bilListe = new Button("Bil Liste");
        bilListe.setOnAction(e -> StartSkaermController.i().pushNode(new BilSkaerm()));
        bilListe.setStyle("-fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-font-size: 12px; -fx-background-color: black");


        tilbage = new Button("Log Ud");
        tilbage.setOnAction(e -> StartSkaermController.i().popNode());
        tilbage.setStyle("-fx-text-fill: white; -fx-font-weight: bold;" +
                "-fx-font-size: 12px; -fx-background-color: black");


        opret.setPrefWidth(leftBorder.getPrefWidth() / 1.1);
        kunde.setPrefWidth(opret.getPrefWidth());
        igangværende.setPrefWidth(opret.getPrefWidth());
        bilListe.setPrefWidth(opret.getPrefWidth());

        Label usernameLabel = new Label("\n\n\nDu er logget ind som\n" + LoginChecker.username);
        usernameLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        usernameLabel.setAlignment(Pos.BOTTOM_CENTER);
        usernameLabel.setTextFill(Color.web("#FFFFFF"));

        Image img2 = new Image("file:Laferrariedit.jpg");
        ImageView logo2 = new ImageView(img2);
        logo2.setImage(img2);

        logo2.fitWidthProperty().bind(this.widthProperty());
        logo2.fitHeightProperty().bind(this.heightProperty());

        this.setCenter(logo2);

        Image img = new Image("file:logo.png");
        ImageView logo = new ImageView(img);
        logo.setImage(img);
        stage.setTitle("Ferrari - FL");
        Image icon = new Image("file:logo.png");
        stage.getIcons().add(icon);
        leftBorder.getChildren().addAll(logo, opret, kunde, igangværende, bilListe, usernameLabel, tilbage);
        logo.setPickOnBounds(true);
        logo.setOnMouseClicked(e -> StartSkaermController.i().setFocus(logo2));

        this.setLeft(leftBorder);

//        Image img2 = new Image("file:laferrari.jpg", 750, 750, false, false);


    }


}
