
public class Tablica2           
{
    int wierszy;
    int kolumn;
    int zakres;
    int[][]tab;
    int minimum;
    public Tablica2(int wierszy,int kolumn, int zakres)
    {
        this.wierszy = wierszy;
        this.kolumn = kolumn;
        this.zakres = zakres;
        
        tab = new int[wierszy][kolumn];
        this.minimum = minimum;
        for(int i=0; i<tab.length; i++)
        {
            for(int j=0; j<tab[i].length; j++)
            {
                tab[i][j] = (int)(Math.random()*zakres+1);
            }
        }
    }
    
    void wyswietl()
    {
        for(int i=0; i<wierszy; i++)
        {
            for(int j=0; j<kolumn; j++)
            {
                System.out.print(tab[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    void wyswietlWiersz(int wiersz)
    {
        for(int i=0; i<kolumn; i++)
        {
            System.out.print(tab[wiersz-1][i] + " ");
        }
    }
    
    void wyswietlKolumne(int kolumna)
    {
        for(int i=0; i<wierszy; i++)
        {
            System.out.println(tab[i][kolumna-1]);
        }
    }
    
    int sumaElementow()
    {
        int suma = 0;
        
        for(int i=0; i<wierszy; i++)
        {
            for(int j=0; j<kolumn; j++)
            {
                suma+=tab[i][j];
            }
        }
        return suma;
        
    }
    
    int[] minimum()
    {
        int[] indeks = new int[2];
        minimum = tab[0][0];
        for(int i=0; i<wierszy; i++)
        {
            for(int j=0; j<kolumn; j++)
            {
                if(tab[i][j]<minimum)
                {
                    minimum = tab[i][j];
                    indeks[0] = i;
                    indeks[1] = j;
                }
                
            }
        }
        return indeks;
    }
    
    int[] czyWystepuje(int wartosc)
    {
        int[] indeks = new int[2];
        indeks[0] = -1;
        indeks[1] = -1;
        for(int i=0; i<wierszy; i++)
        {
            for(int j=0; j<kolumn && indeks[0] == -1; j++)
            {  
                    indeks[0] = (tab[i][j] == wartosc ? i:-1);
                    indeks[1] = (tab[i][j] == wartosc ? j:-1);              
            }
            
        }
        return indeks;
    }
    
    public int minKol()
    {
        int suma = 0;
        int minSuma = 0;
        int nrKol = 0;
        for(int i=0; i<kolumn; i++)
        {
            for(int j=0; j<wierszy; j++)
            {
                suma+=tab[j][i];//   Transponowanie tablicy
                
            }
            if(i==0)
                minSuma = suma;
            if(suma < minSuma)
            {
                minSuma = suma;
                nrKol = i;
            }
            suma = 0;
        }
        System.out.println("Numer kolumny:" + (nrKol+1));
        return minSuma;
    }
    
    public int maxWie()
    {
        int suma = 0;
        int maxSuma = 0;
        int nrWie = 0;
        for(int i=0; i<wierszy; i++)
        {
            for(int j=0; j<kolumn; j++)
            {
                suma+=tab[i][j];
                
            }
            if(i==0)
                maxSuma = suma;
            if(suma > maxSuma)
            {
                maxSuma = suma;
                nrWie = i;
            }
            suma = 0;
        }
        System.out.println("Numer wiersza: "+ (nrWie+1));
        return maxSuma;
    }
    
    void usunWiK()
    {
        int wiersz = minimum()[0];
        int kolumna = minimum()[1];
        int pominWiersz = 0;
        int[][]tab2 = new int[wierszy][kolumn];
        
        for(int i=0; i<wierszy; i++)
        {
            for(int j=0; j<kolumn; j++)
            {
                if(j == kolumna || i == wiersz)
                tab[i][j] = -1;
            }
        }
        
        for(int i=0; i<wierszy; i++)
        {
            if(tab[i][0] == -1 && tab[i][1] == -1)
            pominWiersz = i;
            for(int j=0,l=0;j<kolumn; j++)
            {
                if(tab[i][j] == -1)
                {
                    
                }
                else
                {
                    tab2[i][l] = tab[i][j];
                    l++;
                }
            }
            
        }
        
        for(int i=pominWiersz; i<wierszy-1; i++)
        {
            for(int j=0; j<kolumn; j++)
            {
                tab[i][j] = tab[i+1][j];
                tab2[i][j] = tab2[i+1][j];
            }
        }
        wierszy -=1;
        
        kolumn -=1;
        for(int i=0; i<wierszy; i++)
        {
                
             for(int j=0; j<kolumn; j++)
             {
                tab[i][j] = tab2[i][j];
             }
                 
                 
        }
        
    }
    public static void main(String[] args)
    {
        Tablica2 tab = new Tablica2(6,4,8);
        tab.wyswietl();
        System.out.println("Suma="+tab.sumaElementow());
        //System.out.println();
        //tab.wyswietlWiersz(3);
        //System.out.println();
        //tab.wyswietlKolumne(4);
        System.out.println("Minimum: " + tab.tab[tab.minimum()[0]][tab.minimum()[1]] + "\nIndeksy minimum: [" + tab.minimum()[0]+"]["+tab.minimum()[1]+"]");
        System.out.println("Indeks szukanej wartosci: ["+tab.czyWystepuje(3)[0] + "][" + tab.czyWystepuje(3)[1] + "]"); 
        System.out.println("Suma: " + tab.minKol());
        System.out.println("Suma: " + tab.maxWie());
        tab.usunWiK();
        tab.wyswietl();
    }
}
