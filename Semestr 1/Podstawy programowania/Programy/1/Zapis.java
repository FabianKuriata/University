import java.util.*;
import java.io.*;
public class Zapis
{
    int lWierszy;
    int lKolumn;
    double zakres;
    double[][] tab;
    
    public Zapis(int lWierszy,int lKolumn,int zakres)
    {
        this.lWierszy = lWierszy;
        this.lKolumn = lKolumn;
        this.zakres = zakres;
        
        tab = new double[lWierszy][lKolumn];
        Random generator = new Random();
        for(int i=0;i<lWierszy;i++)
        {
            for(int j=0;j<lKolumn;j++)
            {
                tab[i][j] = generator.nextDouble()*zakres;
            }
        }
    }
    
    void wyswietl()
    {
        for(int i=0; i<tab.length; i++)
        {
            for(int j=0; j<tab[i].length; j++)
            {
                System.out.print(tab[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    void zapisz(double[][] tab)
    {
        File plik = new File("tablica.txt");
        FileInputStream bob = new FileInputStream(bob);
        plik.write();
        BufferedReader wej = new BufferedReader(tab);
    }
    
    public static void main(String []args)
    {
        Zapis tablica = new Zapis(5,5,10);
        
        tablica.wyswietl();
    }
}
