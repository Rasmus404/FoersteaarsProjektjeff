package Datalayer;

import Logic.Bil;
import Logic.Faktura;
import Logic.Kunde;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatalayerTest {

    @org.junit.jupiter.api.Test
    void addKoeb() throws SQLException {
        Datalayer dl = new Datalayer("FerrariDB");

    }
}