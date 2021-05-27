package Presentation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StartApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {


        Scene scene = new Scene(new LoginSkaerm(stage));
        stage.setWidth(750);
        stage.setHeight(535);
        stage.setScene(scene);
        stage.setTitle("Ferrari - FL");
        Image icon = new Image("file:logo.png");
        stage.getIcons().add(icon);
        stage.show();
    }
}
