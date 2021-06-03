package Logic;


// Victor
public class Tilbagebetaling {
    double laaneBeloeb;
    double maanedligUdbetaling;
    double maaned;
    double saldo;


    public Tilbagebetaling(Faktura faktura, double maaned) {
        this.maaned = maaned;
        this.laaneBeloeb = faktura.getLaaneBeloeb();
        this.maanedligUdbetaling = faktura.getMdrUdbetaling();

    }

    public double getMaanedligUdbetaling() {
        return maanedligUdbetaling;
    }

    public double getMaaned() {
        return maaned;
    }

    public double getSaldo() {
        return saldo;
    }


    public void saldoCalc() {
        this.saldo = maanedligUdbetaling * maaned;

    }

    @Override
    public String toString() {
        return "TilbageBetaling{" +
                "MånedligUdbetaling=" + maanedligUdbetaling +
                ", Saldo=" + saldo +
                ", MånederTilbage=" + maaned +
                '}';
    }
}
