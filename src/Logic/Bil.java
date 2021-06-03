package Logic;

import Datalayer.Datalayer;
import java.sql.SQLException;


//Rasmus og Oliver
public class Bil {

    private String bilNavn;
    private double pris;

    public Bil(String bilNavn, Double pris) {
        this.bilNavn = bilNavn;
        this.pris = pris;

    }

    public String getBilNavn() {
        return bilNavn;
    }

    public double getPris() {
        return pris;
    }

    public void addToDatabase() throws SQLException {
        Datalayer DL = new Datalayer("FerrariDB");
        DL.addBil(this);
    }

    public void deleteFromDatabase() throws SQLException {
        Datalayer DL = new Datalayer("FerrariDB");
        DL.deleteBil(this);
    }

    @Override
    public String toString() {
        return getBilNavn();
    }


}




