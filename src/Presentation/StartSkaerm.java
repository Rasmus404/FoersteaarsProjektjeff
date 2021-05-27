package Presentation;

import Logic.LoginChecker;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.util.Stack;


public class StartSkaerm extends BorderPane{

    private Stack<Node> stack = new Stack<>();
    private VBox leftBorder;
    private Button opret, kunde, igangværende, bilListe, tilbage;

    private static StartSkaerm inst=null;

    public static StartSkaerm instance() {
        if (inst == null)
            inst = new StartSkaerm();

        return inst;
    }
    private StartSkaerm() {


        leftBorder = new VBox();
        leftBorder.setPrefWidth(750/ 4);
        leftBorder.setStyle("-fx-background-color: #8B0000");
        leftBorder.setAlignment(Pos.TOP_CENTER);
        leftBorder.setPadding(new Insets(5, 5, 5, 5));
        leftBorder.setSpacing(20);


        opret = new Button("Opret");
        opret.setOnAction(e -> pushNode(new OpretKoebSkaerm()));

        kunde = new Button("Kunde");
        kunde.setOnAction(e -> pushNode(new KundeSkaerm()));

        igangværende = new Button("Igangværende");
        igangværende.setOnAction(e -> pushNode(new IgangværendeSkaerm()));

        bilListe = new Button("Bil Liste");
        bilListe.setOnAction(e -> pushNode(new BilListeSkaerm()));

        tilbage = new Button("Log Ud");
        tilbage.setOnAction(e -> popNode());

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
        leftBorder.getChildren().addAll(logo, opret, kunde, igangværende, bilListe, usernameLabel, tilbage);
        logo.setPickOnBounds(true);
        logo.setOnMouseClicked(e -> setFocus(logo2));

        this.setLeft(leftBorder);

//        Image img2 = new Image("file:laferrari.jpg", 750, 750, false, false);


    }
    public void setFocus(Node focus) {
        this.setCenter(focus);
    }

    public void pushNode(Node focus){
        if(stack.empty()) {
            tilbage.setText("Tilbage");
        }
        stack.push(this.getCenter());
        setFocus(focus);
    }
    public void popNode(){
        if(stack.empty()){
           System.out.println("Logged ud");
        } else {
            setFocus(stack.pop());
            if(stack.empty()) {
                tilbage.setText("Log ud");
            }
        }

    }
}
