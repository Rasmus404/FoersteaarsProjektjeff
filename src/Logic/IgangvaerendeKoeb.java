package Logic;

public class IgangvaerendeKoeb {

    String navn;
    String model;
    String startDato;
    boolean godkendt;
    String status;

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getStartDato() {
        return startDato;
    }

    public void setStartDato(String startDato) {
        this.startDato = startDato;
    }

    public boolean isGodkendt() {
        return godkendt;
    }

    public void setGodkendt(boolean godkendt) {
        this.godkendt = godkendt;
    }

    public void setStatus(boolean godkendt){
        if(godkendt){
            status = "Godkendt";
        }
        else {
            status = "Mangler Godkendelse";
        }
    }


    public String getStatus(){
        return status;
    }


}
