package Logic;

import Datalayer.Datalayer;
import java.sql.SQLException;
import java.util.ArrayList;

// Victor og Oliver
public class ListMediator {
    public static ArrayList<Kunde> kundeListe;
    public static ArrayList<Bil> bilListe;
    public static ArrayList<IgangvaerendeKoeb> igangvaerendekoebListe;


    public static ArrayList<IgangvaerendeKoeb> getIgangværendeKoebs() {
        try {
            updateIgangvaerendeKoebs();
        } catch (SQLException e) {
        }
        return igangvaerendekoebListe;
    }


    public static ArrayList<Kunde> getKundeListe() {
        try {
            updateKundeListe();
        } catch (SQLException e) {
        }
        return kundeListe;
    }


    public static ArrayList<Faktura> getFakturaListeByKunde(Kunde kunde) {
        ArrayList<Faktura> fakturas = new ArrayList<>();
        try {
            fakturas = getFakturasByCondition("kunde_id =" + kunde.getKunde_id());
        } catch (SQLException e) {
        }
        return fakturas;
    }

    public static ArrayList<Faktura> getFakturaById(int id) {
        ArrayList<Faktura> fakturas = new ArrayList<>();
        try {
            fakturas = getFakturasByCondition("faktura_id =" + id);
        } catch (SQLException e) {
        }
        return fakturas;
    }


    public static ArrayList<Bil> getBilListe() {
        try {
            updateBilListe();
        } catch (SQLException e) {
        }
        return bilListe;
    }

    public static ArrayList<Faktura> getFakturasByCondition(String condition) throws SQLException {
        Datalayer DL = new Datalayer("FerrariDB");
        ArrayList<Faktura> tempFakturas = DL.getFakturaListByCondition(condition);
        for (Faktura fakturaVar : tempFakturas) {
            fakturaVar.setDateFormated(fakturaVar.getDateFormated());
        }
        return tempFakturas;
    }

    public static void updateKundeListe() throws SQLException {
        Datalayer DL = new Datalayer("FerrariDB");
        ListMediator.kundeListe = DL.getKundeListByCondition("0=0");
    }


    public static void updateBilListe() throws SQLException {
        Datalayer DL = new Datalayer("FerrariDB");
        ListMediator.bilListe = DL.getBilListByCondition("0=0");
    }

    public static void updateIgangvaerendeKoebs() throws SQLException {
        ArrayList<IgangvaerendeKoeb> tempList = new ArrayList<>();
        for (Kunde kundeVar : getKundeListe()) {
            for (Faktura fakturaVar : getFakturaListeByKunde(kundeVar)) {
                IgangvaerendeKoeb ikVar = new IgangvaerendeKoeb();
                ikVar.setFaktura_id(fakturaVar.getFaktura_id());
                ikVar.setNavn(kundeVar.getNavn());
                ikVar.setModel(fakturaVar.getModel());
                ikVar.setStartDato(fakturaVar.getDateFormated());
                ikVar.setGodkendt(fakturaVar.isFakturaGodkendt());
                ikVar.setStatus(ikVar.isGodkendt());
                tempList.add(ikVar);
            }
        }
        igangvaerendekoebListe = tempList;
    }

}
