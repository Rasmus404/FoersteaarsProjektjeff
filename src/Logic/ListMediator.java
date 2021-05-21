package Logic;

import Datalayer.Datalayer;

import java.sql.SQLException;
import java.util.ArrayList;

public class ListMediator {
    public static ArrayList<Kunde> kundeListe;
    public static ArrayList<Faktura> fakturaListe;
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

    public static ArrayList<Faktura> getFakturaListe() {
        try {
            updateFakturaListe();
        } catch (SQLException e) {
        }
        return fakturaListe;
    }

    public static ArrayList<Faktura> getFakturaListeByKunde(Kunde kunde) {
        ArrayList<Faktura> fakturas = new ArrayList<>();
        try {
            fakturas = getFakturasByCondition("kunde_id =" + kunde.getKunde_id());
        } catch (SQLException e) {
        }
        return fakturas;
    }

    public static void setLaanListe(ArrayList<Faktura> laanListe) {
        ListMediator.fakturaListe = laanListe;
    }

    public static ArrayList<Bil> getBilListe() {
        try {
            updateBilListe();
        } catch (SQLException e) {
        }
        return bilListe;
    }

    public static void setBilListe(ArrayList<Bil> bilListe) {
        ListMediator.bilListe = bilListe;
    }

    public static ArrayList<Faktura> getFakturasByCondition(String condition) throws SQLException {
        Datalayer DL = new Datalayer("FerrariDB");
        return DL.getFakturaListByCondition(condition);
    }

    public static void updateKundeListe() throws SQLException {
        Datalayer DL = new Datalayer("FerrariDB");
        ListMediator.kundeListe = DL.getKundeListByCondition("0=0");
    }

    public static void updateFakturaListe() throws SQLException {
        Datalayer DL = new Datalayer("FerrariDB");
        ListMediator.fakturaListe = DL.getFakturaListByCondition("0=0");
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
