public class Fach {

    private boolean empty = true;
    private Paket paket;
    private String id;

    public Fach(String id) {
        this.id = id;
    }

    public void setEmpty() {
        this.paket = null;
        this.empty = true;
    }

    public boolean getEmpty() {
        return this.empty;
    }

    public Paket getPaket() {
        return this.paket;
    }

    public String getId() {
        return this.id;
    }

    public void setPaket(Paket paket) {
        this.paket = paket;
        this.empty = false;
    }

}
