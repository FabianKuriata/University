
class Pracownik 
{
    private String nazwisko;
    private double etat;
    public Pracownik(String nazwisko, double etat)
    {
        this.nazwisko = nazwisko;
        this.etat = etat;
        
    }
    
    String getNazwisko()
    {
        return nazwisko;
    }
    
    double getEtat()
    {
        return etat;
    }
   
}