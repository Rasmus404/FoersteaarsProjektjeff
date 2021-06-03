package Presentation;


import javafx.scene.control.ComboBox;
import Logic.Bil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import java.util.ArrayList;

//Victor
public class BilerComboBox extends ComboBox {

    ObservableList<Bil> biler;

    public BilerComboBox(ArrayList<Bil> bilerObj) {
        this.biler = FXCollections.observableArrayList(bilerObj);
        this.getItems().addAll(this.biler);
    }

    public String getBilNavn() {
        return ((Bil) this.getSelectionModel().getSelectedItem()).getBilNavn();
    }

    public double getBilPris() {
        return ((Bil) this.getSelectionModel().getSelectedItem()).getPris();
    }
}
