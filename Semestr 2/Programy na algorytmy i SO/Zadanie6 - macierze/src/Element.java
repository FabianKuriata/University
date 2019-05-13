//package pl.ct8.monteth;

public class Element {
    private int wartosc;
    private Element nextK;
    private Element nextW;
    private int kolumna;
    private int wiersz;

    public Element(int wartosc, int kolumna, int wiersz) {
        this.wartosc = wartosc;
        this.nextK = null;
        this.nextW = null;
        this.kolumna = kolumna;
        this.wiersz = wiersz;
    }

    public String getValueString() {
        String str = String.format(" %3d ", wartosc);
        return str;
    }

    public String toString() {
        String str = String.format(" [%3d|%-3d|%-3d] ", wartosc, kolumna, wiersz);
        return str;
    }

    public int getKolumna() {
        return kolumna;
    }

    public void setKolumna(int kolumna) {
        this.kolumna = kolumna;
    }

    public int getWiersz() {
        return wiersz;
    }

    public void setWiersz(int wiersz) {
        this.wiersz = wiersz;
    }

    public int getWartosc() {
        return wartosc;
    }

    public void setWartosc(int w) {
        wartosc = w;
    }

    public boolean hasNextK() {
        boolean has = true;
        if (nextK == null) {
            has = false;
        }
        return has;
    }

    public boolean hasNextW() {
        boolean has = true;
        if (nextW == null) {
            has = false;
        }
        return has;
    }

    public Element getNextK() {
        return nextK;
    }

    public Element getNextW() {
        return nextW;
    }

    public void setNextK(Element k) {
        nextK = k;
    }

    public void setNextW(Element w) {
        nextW = w;
    }

}
