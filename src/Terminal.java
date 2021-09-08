import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Terminal {

    public int menuOne() {
        ausgabe_menu();
        System.out.print("Bitte Eingabe tätigen: ");
        int selected = new Scanner(System.in).nextInt();
        return selected;
    }

    public void ausgabe_menu() {
        String message = String.format(Locale.GERMANY, "\n%s\r\n%s\r\n%s\r\n%s\r\n%s\r\n", "Paketstation Menü",
                "(1) Paket einlagern", "(2) Paket entnehmen", "(3) Pakete listen", "(4) beenden");
        System.out.print(message);
    }

    public String einlagernInput() {
        System.out.print("Bitte Empfänger eingeben: ");
        String empfaengerName = new Scanner(System.in).nextLine();
        return empfaengerName;
    }

    public void einlagernOutput() {
        System.out.println("Paket wurde eingelagert.\r\n");
    }

    public String entnehmenInput() {
        System.out.print("Bitte Empfänger oder Nummer eingeben: ");
        String empfaengerNameoderSendungsnummer = new Scanner(System.in).nextLine();
        return empfaengerNameoderSendungsnummer;
    }

    public void entnehmenOutput(Paket paket) {
        String message = String.format(Locale.GERMANY, "Paket mit der Nr. %s  von %s entnommen\r\n",
                paket.getSendungsnummer(), paket.getEmpfaenger().getName());
        System.out.print(message);
    }

    public void status(ArrayList<Fach> faecher) {
        String header = String.format(Locale.GERMANY, "\r\n%-15s\r\n%-15s%-15s%-15s\r\n", "Pakete der Paketstation ",
                "Fach", "Empfänger", "Sendungsnr");
        System.out.println(header);

        for (Fach fach : faecher) {
            String message = String.format(Locale.GERMANY, "%-15s%-15s%-15s", fach.getId(), (fach.getPaket() != null) ? fach.getPaket().getEmpfaenger().getName() : "leer", (fach.getPaket() != null) ? fach.getPaket().getSendungsnummer() : "-");           
            System.out.println(message);
        }
    }
}
