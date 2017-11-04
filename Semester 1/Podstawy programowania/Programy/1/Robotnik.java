class Robotnik extends Pracownik
{
    double przeprGodz;
    double stawka;
    double nadgodziny;
    final int LIMIT = 200;
    public Robotnik(String nazwisko, double etat, double stawka, double przeprGodz)
    {
        super(nazwisko, etat);
        this.stawka = stawka;
        this.przeprGodz = przeprGodz;
        
        if(przeprGodz > LIMIT)
        {
            this.nadgodziny = przeprGodz - LIMIT;
        }
    }
    
    double wyplata()
    {
        double dodatek = 0;
        double wyplata = 0;
        dodatek = super.getEtat()*(nadgodziny*0.5*stawka);
        if(dodatek > 0)
        wyplata = (super.getEtat()*(stawka*LIMIT))+dodatek;
        else wyplata = super.getEtat()*(stawka*przeprGodz);
        
        return wyplata;
    }
}
