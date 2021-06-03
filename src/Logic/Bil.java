package Logic;

import Datalayer.Datalayer;
import java.sql.SQLException;


public class Bil {

    private String bilNavn;
    private double pris;

    DecimalFormat df = new DecimalFormat("#.00");


    public Bil(String bilNavn, Double pris){
        this.bilNavn = bilNavn;
        this.pris = pris;

    }

    public String getBilNavn() {
        return bilNavn;
    }

    public void setBilNavn(String bilNavn) {
        this.bilNavn = bilNavn;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }
    public void addToDatabase()throws SQLException {
        Datalayer DL = new Datalayer("FerrariDB");
        DL.addBil(this);
    }

    public void deleteFromDatabase() throws SQLException {
        Datalayer DL = new Datalayer("FerrariDB");
        DL.deleteBil(this);
    }


    public String toString(){
        return getBilNavn();
    }


}




