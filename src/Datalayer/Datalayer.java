package Datalayer;

import Logic.Bil;
import Logic.Faktura;
import Logic.Kunde;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Datalayer {


    private static Connection connection;

    public Datalayer(String databaseName) throws SQLException {
        loadJdbcDriver();
        openConnection(databaseName);
    }

     static boolean loadJdbcDriver() {
        try {
            //System.out.println("Loading JDBC driver...");

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //System.out.println("JDBC driver loaded");

            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load JDBC driver!");
            return false;
        }
    }

     static boolean openConnection(String databaseName) {
        String connectionString =
                "jdbc:sqlserver://localhost:1433;" +
                        "instanceName=SQLEXPRESS;" +
                        "databaseName=" + databaseName + ";" +
                        "integratedSecurity=true;";

        connection = null;

        try {
            //System.out.println("Connecting to database...");

            connection = DriverManager.getConnection(connectionString);

            //System.out.println("Connected to database");

            return true;
        } catch (SQLException e) {
            System.out.println("Could not connect to database!");
            System.out.println(e.getMessage());

            return false;
        }
    }

    //Create
    public boolean addKoeb(Kunde kunde) {
        try {
            String sql = "INSERT INTO kunde VALUES  " +
                    "('" + kunde.getNavn()
                    + "','" + kunde.getCpr()
                    + "','" + kunde.getTlf()
                    + "','" + kunde.getAdresse()
                    + "','" + kunde.getBy()
                    + "','" + kunde.getLeveringsadresse() + "')";

            System.out.println(sql);
            Statement statement = connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);
            ResultSet resultSet =
                    statement.executeQuery("SELECT SCOPE_IDENTITY()");
            if (resultSet.next()) {
                int autoKey = resultSet.getInt(1);

                kunde.setKunde_id(autoKey);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addFaktura(Faktura faktura) {
        try {
            String sql = "INSERT INTO Faktura VALUES  " +
                    "('" + faktura.isFakturaGodkendt()
                    + "','" + faktura.getBilPris()
                    + "','" + faktura.getModel()
                    + "','" + faktura.getUdbetalingsprocent()
                    + "','" + faktura.getLoebetid()
                    + "','" + faktura.calcRentesats()
                    + "','" + faktura.getKoebsdatoToLong()
                    + "','" + faktura.getKunde_id() + "')";

            System.out.println(sql);
            Statement statement = connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);
            ResultSet resultSet =
                    statement.executeQuery("SELECT SCOPE_IDENTITY()");
            if (resultSet.next()) {
                int autoKey = resultSet.getInt(1);

                faktura.setFaktura_id(autoKey);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addBil(Bil bil) {
        try {
            String sql = "INSERT INTO Bil VALUES  " +
                    "('" + bil.getBilNavn()
                    + "','" + bil.getPris() + "')";

            System.out.println(sql);
            Statement statement = connection.createStatement();
            int affectedRows = statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    //Read

    public ArrayList<Kunde> getKundeListByCondition(String condition) {
        ArrayList<Kunde> kundetable = new ArrayList<>(20);

        System.out.println("condition: " + condition);
        try {
            String sql = "SELECT * FROM kunde WHERE " + condition;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // iteration starter 'before first'
            while (resultSet.next()) {
                // hent data fra denne række
                Kunde kunde = new Kunde();
                kunde.setKunde_id(resultSet.getInt("kunde_id"));
                kunde.setNavn(resultSet.getString("navn"));
                kunde.setCpr(resultSet.getString("cpr_nr"));
                kunde.setTlf(resultSet.getString("telefon_nr"));
                kunde.setAdresse(resultSet.getString("adresse"));
                kunde.setBy(resultSet.getString("city"));
                kunde.setLeveringsadresse(resultSet.getString("leverings_adresse"));
                kundetable.add(kunde);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kundetable;
    }

    public ArrayList<Faktura> getFakturaListByCondition(String condition) {
        ArrayList<Faktura> fakturas = new ArrayList<>();
        System.out.println("condition: " + condition);
        try {
            String sql = "SELECT * FROM Faktura WHERE " + condition;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // iteration starter 'before first'
            while (resultSet.next()) {
                // hent data fra denne række
                Faktura faktura = new Faktura();
                faktura.setBilPris(Double.parseDouble(resultSet.getString("købsbilpris")));
                faktura.setModel(resultSet.getString("model"));
                faktura.setUdbetalingsprocent(Double.parseDouble(resultSet.getString("udbetalingsprocent")));
                faktura.setLoebetid(Double.parseDouble(resultSet.getString("loebetid")));
                faktura.setRentesats(Double.parseDouble(resultSet.getString("rentesats")));
                faktura.setKoebsdato(new Date(Long.parseLong(resultSet.getString("koebsdato"))));
                faktura.setKunde_id(resultSet.getInt("kunde_id"));
                fakturas.add(faktura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return fakturas;
    }

    public ArrayList<Bil> getBilListByCondition(String condition) {
        ArrayList<Bil> biler = new ArrayList<>(20);

        System.out.println("condition: " + condition);
        try {
            String sql = "SELECT * FROM Bil WHERE " + condition;

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // iteration starter 'before first'
            while (resultSet.next()) {
                // hent data fra denne række
                Bil bil = new Bil(resultSet.getString("bil_navn"), Double.parseDouble(resultSet.getString("bilpris")));
                biler.add(bil);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return biler;
    }
    public void updateFakturaGodkendelse(Faktura faktura) {
        try {
            String sql = "UPDATE Faktura SET faktura_godkendt= '" + faktura.isFakturaGodkendt() +
                    "' WHERE faktura_id= '" + faktura.getFaktura_id() + "'";

            Statement statement = connection.createStatement();

            int affectedRows = statement.executeUpdate(sql);

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }




    //Delete
    public void deleteKunde(Kunde kunde) {
        deleteFakturaByKunde(kunde);

        try {
            String sql = "DELETE FROM kunde WHERE kunde_id='" + kunde.getKunde_id() + "';";
            // get statement object
            Statement statement = connection.createStatement();
            // execute sql statement
            int affectedRows = statement.executeUpdate(sql);

            ResultSet resultSet = statement.executeQuery("SELECT SCOPE_IDENTITY()");

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
        public void deleteFaktura(Faktura faktura){
            try {
                String sql = "DELETE FROM Faktura WHERE faktura_id='" + faktura.getFaktura_id() + "';";
                // get statement object
                Statement statement = connection.createStatement();
                // execute sql statement
                int affectedRows = statement.executeUpdate(sql);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public void deleteFakturaByKunde(Kunde kunde) {
            try {
                String sql = "DELETE FROM Faktura WHERE kunde_id='" + kunde.getKunde_id() + "';";
                // get statement object
                Statement statement = connection.createStatement();
                // execute sql statement
                int affectedRows = statement.executeUpdate(sql);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
            public void deleteBil(Bil bil){
                try {
                    String sql = "DELETE FROM Bil WHERE bil_navn='" + bil.getBilNavn() + "';";
                    // get statement object
                    Statement statement = connection.createStatement();
                    // execute sql statement
                    int affectedRows = statement.executeUpdate(sql);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }



