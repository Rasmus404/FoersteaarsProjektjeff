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

//Victor og Oliver
class DatalayerTest {

    private Datalayer dl;
    Kunde kunde;
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