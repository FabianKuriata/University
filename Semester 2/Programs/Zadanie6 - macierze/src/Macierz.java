import java.util.Random;

public class Macierz {
    private Element head;
    private int sizeW;
    private int sizeK;

    public Macierz(int w, int k) {
        this.head = new Element(0, 0, 0);
        this.sizeW = w;
        this.sizeK = k;
        Element current = head;
        for (int i = 0; i < sizeW; i++) {
            current.setNextK(new Element(0, i + 1, 0));
            current = current.getNextK();
        }
        current = head;
        for (int i = 0; i <= sizeK; i++) {
            current.setNextW(new Element(0, 0, i + 1));
            current = current.getNextW();
        }
    }

   

    public void printValues() {
        System.out.println();
        int j = 1;
        while (j <= this.sizeK) {
            String line = "";
            Element current = this.head;
            while (current.getWiersz() < j) {
                current = current.getNextW();
            }

            for (int i = 0; i <= this.sizeW; i++) {
                if (current.getKolumna() == i) {
                    if (i > 0) {
                        line += current.getValueString();
                    }
                    if (current.hasNextK()) {
                        current = current.getNextK();
                    }
                } else {
                    String space = "   0 ";
                    line += space;
                }
            }
            System.out.println(line);
            j++;
        }
        System.out.println();
    }

    public void insertElement(int wartosc, int kolumna, int wiersz) {
        Element nowy = new Element(wartosc, kolumna, wiersz);
        Element current = head;

        while (current.getWiersz() < wiersz) {
            current = current.getNextW();
        }

        while (current.getKolumna() < kolumna && current.hasNextK() && current.getNextK().getKolumna() < kolumna) {
            current = current.getNextK();
        }

        if (current.hasNextK() && current.getNextK().getKolumna() > kolumna) {
            nowy.setNextK(current.getNextK());
        } else if (current.hasNextK() && current.getNextK().getKolumna() == kolumna && current.getNextK().hasNextK()) {
            nowy.setNextK(current.getNextK().getNextK());
        }
        current.setNextK(nowy);

        current = head;
        while (current.getKolumna() < kolumna) {
            current = current.getNextK();
        }

        while (current.getWiersz() < wiersz && current.hasNextW() && current.getNextW().getWiersz() < wiersz) {
            current = current.getNextW();
        }

        if (current.hasNextW() && current.getNextW().getWiersz() > wiersz) {
            nowy.setNextW(current.getNextW());
        } else if (current.hasNextW() && current.getNextW().getWiersz() > wiersz && current.getNextW().hasNextW()) {
            nowy.setNextW(current.getNextW().getNextW());
        }
        current.setNextW(nowy);

    }

    public static Macierz dodajMacierze(Macierz a, Macierz b) {
        Macierz result = new Macierz(a.sizeW, a.sizeK);
        result.dodajMacierz(a);
        result.dodajMacierz(b);
        return result;
    }

    public void dodajMacierz(Macierz b) {
        int i = 1;
        while (i <= this.sizeK) {
            Element current = b.head;
            while (current.getWiersz() < i) {
                current = current.getNextW();
            }
            for (int k = 0; k <= this.sizeW; k++) {
                if (current.getKolumna() == k) {
                    if (k > 0) {
                        this.dodajElement(current.getWartosc(), current.getKolumna(), current.getWiersz());
                    }
                    if (current.hasNextK()) {
                        current = current.getNextK();
                    }
                }
            }
            i++;
        }
    }

    public void dodajElement(int wartosc, int kolumna, int wiersz) {
        Element nowy = new Element(wartosc, kolumna, wiersz);
        Element current = head;
        while (current.getWiersz() < wiersz) {
            current = current.getNextW();
        }
        while (current.getKolumna() < kolumna && current.hasNextK() && current.getNextK().getKolumna() < kolumna) {
            current = current.getNextK();
        }
        if (current.hasNextK() && current.getNextK().getKolumna() > kolumna) {
            nowy.setNextK(current.getNextK());
            current.setNextK(nowy);
        } else if (current.hasNextK() && current.getNextK().getKolumna() == kolumna) {
            Element last = current;
            current = current.getNextK();
            current.setWartosc(current.getWartosc() + nowy.getWartosc());
            if (current.getWartosc() == 0){
                if (current.hasNextK()){
                    last.setNextK(current.getNextK());
                }else {
                    last.setNextK(null);
                }
            }
        } else {
            current.setNextK(nowy);
        }

        current = head;
        while (current.getKolumna() < kolumna) {
            current = current.getNextK();
        }
        while (current.getWiersz() < wiersz && current.hasNextW() && current.getNextW().getWiersz() < wiersz) {
            current = current.getNextW();
        }
        if (current.hasNextW() && current.getNextW().getWiersz() > wiersz) {
            Element last = current;
            nowy.setNextW(current.getNextW());
            current.setNextW(nowy);
            if (current.getWartosc() == 0){
                if (current.hasNextK()){
                    last.setNextK(current.getNextK());
                }else {
                    last.setNextK(null);
                }
            }
        } else{
            current.setNextW(nowy);
        }
    }
    public void wyswietlElementy() {
        System.out.println("\n [Val|Col|Row]");
        int j = 0;
        while (j <= this.sizeK) {
            Element current = this.head;
            while (current.getWiersz() < j) {
                current = current.getNextW();
            }
            for (int i = 0; i <= this.sizeW; i++) {
                if (current.getKolumna() == i) {
                    if (current.getKolumna() != 0 && current.getWiersz() != 0){
                        System.out.println(current);
                    }
                    if (current.hasNextK()) {
                        current = current.getNextK();
                    }
                }
            }
            j++;
        }
        System.out.println();
    }

  
}