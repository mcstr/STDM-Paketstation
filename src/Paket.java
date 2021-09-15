public class Paket {
    private String sendungsnummer;
    private Kunde empfaenger;
    private String fachId;

    public Paket(String sendungsnummer, Kunde empfaenger, String fachId) {
        this.sendungsnummer = sendungsnummer;
        this.empfaenger = empfaenger;
        this.fachId = fachId;
    }

    public String getSendungsnummer() {
        return this.sendungsnummer;
    }

    public Kunde getEmpfaenger() {
        return this.empfaenger;
    }

    public String getFachId() {
        return fachId;
    }

}
