package Presentation;

import javafx.scene.*;

import java.util.Stack;

import javafx.scene.image.Image;
import javafx.stage.*;

//Victor
public class StartSkaermController {
    private Stack<Node> stack = new Stack<>();
    StartSkaerm startSkaerm;
    private static StartSkaermController inst = null;

    public static StartSkaermController i() {
        if (inst == null)
            inst = new StartSkaermController();

        return inst;
    }

    private StartSkaermController() {
    }

    public void setStartSkaerm(StartSkaerm startSkaerm) {
        this.startSkaerm = startSkaerm;
    }

    public void setFocus(Node focus) {
        startSkaerm.setCenter(focus);
    }

    public void pushNode(Node focus) {
        if (stack.empty()) {
            startSkaerm.tilbage.setText("Tilbage");
        }
        stack.push(startSkaerm.getCenter());
        setFocus(focus);
    }

    public void popNode() {
        if (stack.empty()) {

            Scene scene = new Scene(new LoginSkaerm(new Stage()));
            Stage stage = new Stage();
            stage.setWidth(750);
            stage.setHeight(535);
            stage.setScene(scene);
            stage.setTitle("Ferrari - FL");
            Image icon = new Image("file:logo.png");
            stage.getIcons().add(icon);
            stage.show();
            startSkaerm.stage.close();


        } else {
            setFocus(stack.pop());
            if (stack.empty()) {
                startSkaerm.tilbage.setText("Log ud");
            }
        }
    }

    public void clearAndStartNew(Node focus) {
        startSkaerm.setCenter(focus);
        stack.clear();
        startSkaerm.tilbage.setText("Log ud");
    }
}
