package Logic;
import Datalayer.Datalayer;
import javax.xml.crypto.Data;
import java.util.Date;
import Datalayer.InterestRate;
import Datalayer.Rating;
import Datalayer.CreditRator;
import java.sql.SQLException;

//Victor
public class Faktura {

    int faktura_id;
    int kunde_id;
    String model;
    double bilPris; // fra database
    double udbetalingsprocent; //indsat værdi
    double loebetid; // indsat værdi
    double rki;
    double rentesats;
    boolean fakturaGodkendt;
    Date koebsdato;
    Kunde kunde;


    //Datalayer constructor
    public Faktura() {
    }

    //Presentation constructor
    public Faktura(Kunde kunde) {
        this.kunde = kunde;
        check(kunde);
    }


    public void check(Kunde kunde) {
        rkiCheck(kunde.getKreditVaerdighed());
        loebeTidCheck();
        udbetalingsProcentCheck();
    }


    //Check for Rentesats components
    public void rkiCheck(String kreditVaerdighed) {

        switch (kreditVaerdighed) {
            case "A":
                this.rki = 0.01;
                break;
            case "B":
                this.rki = 0.02;
                break;
            case "C":
                this.rki = 0.03;
                break;
            case "D":
                break;
        }
    }

    public double loebeTidCheck() {
        if ((loebetid > 36)) {
            return 0.01;
        } else return 0;
    }

    public double udbetalingsProcentCheck() {
        if (udbetalingsprocent < 50) {
            return 0.01;
        } else return 0;
    }


    //udregninger
    public double getTotalPris() {
        return getMdrUdbetaling() * getLoebetid() + getUdbetaling();
    }

    public double getMdrUdbetaling() {
        return getLaaneBeloeb() * calcMaanedligRente() / (1 - Math.pow((1 + calcMaanedligRente()), -getLoebetid()));

    }

    public double getLaaneBeloeb() {
        return getBilPris() - getUdbetaling();
    }

    public double calcRentesats() {
        setRentesats(rki + new bankDailyRate().getDailyRate() * 0.01 + loebeTidCheck() + udbetalingsProcentCheck());
        return getRentesats();
    }

    public double calcMaanedligRente() {
        return Math.pow((1 + calcRentesats()), 1 / 12.0) - 1;
    }

    public double getUdbetaling() {
        return bilPris * udbetalingsprocent / 100;
    }

    //Database
    public void addToDatabase() throws SQLException {
        Datalayer DL = new Datalayer("FerrariDB");
        DL.addFaktura(this);
    }

    public void deleteFromDatabase() throws SQLException {
        Datalayer DL = new Datalayer("FerrariDB");
        DL.deleteFaktura(this);
    }

    //DateFormating
    public String getDateFormated() {
        StringBuilder sb = new StringBuilder(koebsdato.toString());
        return sb.substring(8, 9) + sb.substring(3, 7) + sb.substring(24); // dow (mon dd) hh:mm:ss zzz (yyyy)

    }


    //Getters and Setters
    public double getRentesats() {
        return rentesats;
    }

    public void setRentesats(Double rentesats) {
        this.rentesats = rentesats;
    }

    public double getBilPris() {
        return bilPris;
    }

    public void setBilPris(double bilPris) {
        this.bilPris = bilPris;
    }

    public double getUdbetalingsprocent() {
        return udbetalingsprocent;
    }

    public void setUdbetalingsprocent(double udbetalingsprocent) {
        this.udbetalingsprocent = udbetalingsprocent;
    }

    public double getLoebetid() {
        return loebetid;
    }

    public void setLoebetid(double loebetid) {
        this.loebetid = loebetid;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setKunde_id(int kunde_id) {
        this.kunde_id = kunde_id;
    }

    public int getKunde_id() {
        return kunde_id;
    }

    public void setFaktura_id(int id) {
        this.faktura_id = id;
    }

    public int getFaktura_id() {
        return faktura_id;
    }

    public void setFakturaGodkendt(boolean fakturaGodkendt){
        this.fakturaGodkendt = fakturaGodkendt;
    }

    public void setFakturaGodkendtByPris(double pris) {
        if(pris>1500000) {
            this.fakturaGodkendt = false;
        } else {
            this.fakturaGodkendt = true;
        }
    }

    public boolean isFakturaGodkendt(){
        return fakturaGodkendt;
    }

    public Date getKoebsdato() {
        return koebsdato;
    }

    public long getKoebsdatoToLong() {
        return koebsdato.getTime();
    }

    public void setKoebsdato(Date koebsdato) {
        this.koebsdato = koebsdato;
    }
}


