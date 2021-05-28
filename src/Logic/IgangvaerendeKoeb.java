package Logic;

public class IgangvaerendeKoeb {



    int faktura_id;
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

    public int getFaktura_id() {
        return faktura_id;
    }

    public void setFaktura_id(int faktura_id) {
        this.faktura_id = faktura_id;
    }

    public String getStatus(){
        return status;
    }


}
