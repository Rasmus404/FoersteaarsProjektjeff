package Presentation;

import javafx.application.Application;
import java.sql.SQLException;

public class Main  {

    public Main() throws SQLException {
    }

    public static void main(String[] args) throws SQLException {
        Application.launch(StartApp.class, args);
    }
}
