package Datalayer;

import Logic.Bil;
import Logic.Faktura;
import Logic.Kunde;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class DatalayerTest {

    private Datalayer dl;
    Kunde kunde;
    Faktura faktura;
    Bil bil;

    @BeforeEach
    void setUp() throws SQLException {
        dl = new Datalayer("FerrariDB");

        kunde = new Kunde();
        kunde.setNavn("Tester");
        kunde.setBy("TestBy");
        kunde.setAdresse("TestAddresse");
        kunde.setTlf("12341234");
        kunde.setCpr("1234123412");

        faktura = new Faktura();
        faktura.setKoebsdato(new Date(1621936097));
        faktura.setKunde_id(kunde.getKunde_id());
        faktura.setLoebetid(40);
        faktura.setUdbetalingsprocent(50);
        faktura.calcRentesats();

        bil = new Bil("Test1", (double) 1);

    }

    @Test
    @DisplayName("Access to database")
    void databaseAccessTest() throws SQLException {
        assertTrue( Datalayer.loadJdbcDriver());
        assertTrue( Datalayer.openConnection("FerrariDB"));
    }


    @Test
    void addKoeb() {

        assertTrue( dl.addKoeb(kunde));
    }

    @Test
    void addFaktura() {


        assertTrue(dl.addFaktura(faktura));
    }

    @Test
    void addBil() {

        assertTrue(dl.addBil(bil));
    }

    @Test
    void getKundeListByCondition() {
        assertNotEquals(0, dl.getKundeListByCondition("0=0").size());
    }

    @Test
    void getFakturaListByCondition() {
        assertNotEquals(0, dl.getFakturaListByCondition("0=0").size());
    }

    @Test
    void getBilListByCondition() {
        assertNotEquals(0, dl.getBilListByCondition("0=0").size());
    }


}