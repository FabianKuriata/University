public abstract class Pracownik2 implements Comparable<Pracownik2>
{
    private String nazwisko;
    private double etat;
    public Pracownik2(String nazwisko, double etat)
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
    public int compareTo(Pracownik2 pracownik)
    {
        return nazwisko.compareTo(pracownik.getNazwisko());
    }        
}