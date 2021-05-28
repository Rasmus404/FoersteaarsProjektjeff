package Logic;
import Datalayer.Datalayer;
import Datalayer.CreditRator;
import java.sql.SQLException;

//Victor
public class Kunde {
    int kunde_id;
    //int laan_id;
    String navn = "";
    String cpr = "";
    String tlf = "";
    String adresse = "";
    //String model = "";
    String by = "";
    String leveringsadresse = "";
    boolean leveringOenskes = false;
    String kreditVaerdighed;


    public int getKunde_id() {
        return kunde_id;
    }

    public void setKunde_id(int koeb_id) {
        this.kunde_id = koeb_id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getLeveringsadresse() {
       /* if (leveringsadresse.isEmpty() && leveringOenskes)
            return adresse;
        else*/
        return leveringsadresse;
    }

    public void setLeveringsadresse(String leveringsadresse) {
        this.leveringsadresse = leveringsadresse;
    }

    public void setLeveringOenskes() {
        this.leveringOenskes = true;
    }

    public String getKreditVaerdighed() {
        return kreditVaerdighed;
    }

    public void setKreditVaerdighed(String kreditVaerdighed) {
        this.kreditVaerdighed = kreditVaerdighed;
    }

    public void checkMyRKI(String cpr) {
        this.kreditVaerdighed = CreditRator.i().rate(cpr).name();
    }

    public void addToDatabase() throws SQLException {
        Datalayer DL = new Datalayer("FerrariDB");
        DL.addKoeb(this);
    }
    public void deleteFromDatabase() throws SQLException {
        Datalayer DL = new Datalayer("FerrariDB");
        DL.deleteKunde(this);
    }
}
