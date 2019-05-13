

class Urzednik2 extends Pracownik2
{
    private double placaPodst;
    private double premia;
    public Urzednik2(String nazwisko, double etat, double placaPodst, double premia)
    {
        super(nazwisko, etat);
        this.placaPodst = placaPodst;
        this.premia = premia;
    }
    
    double wyplata()
    {
        double wyplata = 0;
        wyplata = super.getEtat()*(placaPodst+((premia/100)*placaPodst));
        return wyplata;
    }
         
    
    double getPlacaPodst()
    {
        return placaPodst;
    }
     
    double getPremia()
    {
        return premia;
    }
}
