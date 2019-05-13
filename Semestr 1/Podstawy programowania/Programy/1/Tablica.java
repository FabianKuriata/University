
public class Tablica    
{
    
    int rozmiar;
    int zakres;
    int []tab;
    
    public Tablica(int rozmiar, int zakres)
    {
        this.rozmiar = rozmiar;
        this.zakres = zakres;
        tab = new int[rozmiar];
        
        for(int i = 0; i < tab.length; i++)
        {
            tab[i] = (int)(Math.random()*(zakres+1));
        }
        
    }
    
    void wyswietl()
    {
        for(int i = 0; i < rozmiar; i++)
        {
            System.out.print(tab[i] + " ");
        }
        System.out.println();
    }
    
    int policzSume()
    {
        int suma = 0;
        for(int i = 0; i < tab.length; i++)
        {
            suma+=tab[i];
        }
        return suma;
    }
    
    boolean czyRozno()
    {
        boolean jest = true;  // jest różnowartościowa
             for( int i =0; i< rozmiar && jest ; i++)
             {
                 //System.out.println(tab[i]);
                 for( int j=i+1; j < rozmiar && jest ; j++) 
                 { 
                     jest=tab[i] != tab[j];
                 }
             }
             return jest;

    }
    int usunElement(int wartosc)
    {
        int[]tab2;
        tab2 = new int[rozmiar];
        
        for(int i=0; i<rozmiar; i++)
        {
            if(tab[i] == wartosc)
            {
                tab[i]=zakres+3;
                //break;
            }
            
        }
        int j=0;
        for(int i=0; i<rozmiar; i++)
        {
            if(tab[i]!=zakres+3)
            {
                tab2[j] = tab[i];
                j++;
            }
        }
      
        for(int i=0; i<rozmiar;i++)
        {
            tab[i] = tab2[i];
        }
        if(rozmiar==j)
        {
            return 0;
        }
        else
        {
            rozmiar = j; return 1;
        }
        
    }    
    
    void bezPowtorzen()
    {
        int[]tab3;
       
        tab3 = new int[rozmiar];
        int usunieto = 0;
        int j = 0;
        for(int i=usunieto; i<rozmiar;j++ )
        {
            tab3[j] = tab[i];
           // System.out.println(rozmiar);
            
            if(usunElement(tab[i]) > 0)
            {
                usunieto = 0;
              
            }
            else usunieto += 1;
           //wyswietl();
        }
        rozmiar = j;
        for(int i=0; i<rozmiar; i++)
        {
            tab[i] = tab3[i];
        }
        
        
       
    }
    
    public static void main()
    {
        Tablica tab = new Tablica(10,6);
        tab.wyswietl();
        System.out.println("Suma: "+tab.policzSume());
        System.out.println("Czy roznowartościowa: "+tab.czyRozno());
       
        tab.bezPowtorzen();
        System.out.println("Usuniecie powtorzen");
        tab.wyswietl();
        
        tab.usunElement(3);
        tab.wyswietl();
        
    }
    
    
}
