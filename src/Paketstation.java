import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Paketstation {
    private ArrayList<Fach> faecher = new ArrayList<Fach>();
    private ArrayList<Integer> sendungsnummern = new ArrayList<Integer>();
    private ArrayList<Paket> pakete = new ArrayList<Paket>();
    private Terminal terminal;

    public Paketstation(int faecher) {
        // instantiate faecher
        for (int i = 0; i < faecher; i++) {
            this.faecher.add(new Fach(Integer.toString(i + 1)));
        }

        this.terminal = new Terminal();
    }

    public ArrayList<Fach> getFaecher() {
        return this.faecher;
    }

    public void setFaecher(ArrayList<Fach> faecher) {

        this.faecher = faecher;
    }

    public void paketEinlagern() {
        String empfaengerName = terminal.einlagernInput();
        Kunde kunde = new Kunde(empfaengerName);
        Fach fach = getFirstEmptyFach();
        Paket paket = new Paket(generateSendugnsnummer(), kunde, fach.getId());
        this.pakete.add(paket);
        terminal.eintlagernOutput();
        fach.setPaket(paket);
    }

    public void paketEntnehmen() {
        try {
            String empfaengerNameoderSendungsnummer = terminal.entnehmenrnInput();
            ArrayList<Paket> paketezuentnehmen = this.findPaket(empfaengerNameoderSendungsnummer);
            for (Paket paket : paketezuentnehmen) {
                Fach fachzuentleeren = this.findFach(paket);
                terminal.entnehmenrnOutput(paket);
                fachzuentleeren.setEmpty();
                pakete.remove(paket);
            }

        } catch (Exception e) {
            System.out.println("\r\nSomething went wrong.\n");
        }

    }

    private String generateSendugnsnummer() {
        int min = 1;
        int max = 9;
        int random_sendungsnummer = (int) Math.floor(Math.random() * (max - min + 1) + min);

        while (sendungsnummerExists(random_sendungsnummer)) {
            random_sendungsnummer = (int) Math.floor(Math.random() * (max - min + 1) + min);
        }

        this.sendungsnummern.add(random_sendungsnummer);
        return Integer.toString(random_sendungsnummer);
    }

    private boolean sendungsnummerExists(int sendungsnummer) {
        boolean exists = this.sendungsnummern.contains(sendungsnummer);
        return exists;
    }

    private Fach getFirstEmptyFach() {
        Fach empty = faecher.stream().filter(fach -> fach.getEmpty() == true).findAny().orElse(null);
        return empty;
    }

    private ArrayList<Paket> findPaket(String empfaengerNameoderSendungsnummer) {
        Stream<Paket> paket = pakete.stream()
                .filter(findPaket -> findPaket.getEmpfaenger().getName().equals(empfaengerNameoderSendungsnummer)
                        || findPaket.getSendungsnummer().equals(empfaengerNameoderSendungsnummer));
        ArrayList<Paket> paketeArray = paket.collect(Collectors.toCollection(ArrayList::new));
        return paketeArray;
    }

    private Fach findFach(Paket paket) {
        Fach fach = faecher.stream().filter(findFach -> findFach.getId().equals(paket.getFachId())).findAny()
                .orElse(null);
        return fach;
    }

    private void displayStatus() {
        terminal.status(this.faecher);
    }

    public void run() {
        boolean run = true;
        while (run) {
            try {
                int selected = terminal.menuOne();

                switch (selected) {
                    case 1:
                        this.paketEinlagern();
                        break;
                    case 2:
                        this.paketEntnehmen();
                        break;
                    case 3:
                        this.displayStatus();
                        System.out.print("numero 3");
                        break;
                    case 4:
                        run = false;
                        break;
                }

            } catch (Exception e) {
                System.err.println("\r\nPlease enter one of the above numbers\r\n");
            }
        }

    }

}