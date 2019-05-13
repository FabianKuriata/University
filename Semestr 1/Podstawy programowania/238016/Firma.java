
public class Firma
{
    Pracownik[] listaPrac;
    int zatrudPrac = 0;
    int lPrac;
    public Firma(int lPrac)
    {
        this.lPrac = lPrac;
        listaPrac = new Pracownik[lPrac];
     
    }
    
    void zatrudnijRob(String n, double e, double s, double godz)
    {
        boolean zatrudniony = false;
        for(int i = 0; i<zatrudPrac; i++)
        {
            if(n == listaPrac[i].getNazwisko())
            zatrudniony = true;
        }
        if(zatrudniony == false)
        {
            Robotnik rob = new Robotnik(n,e,s,godz);
            listaPrac[zatrudPrac] = rob;
            zatrudPrac++;
        }
        else
        {
            System.out.println("Pracownik o podanym nazwisku jest już zatrudniony");
        }
        
    }
    
    void zatrudnijUrz(String n, double e, double placa, double premia)
    {
        
         boolean zatrudniony = false;
        for(int i = 0; i<zatrudPrac; i++)
        {
            if(n == listaPrac[i].getNazwisko())
            zatrudniony = true;
        }
        if(zatrudniony == false)
        {
            Urzednik urz = new Urzednik(n,e,placa,premia);
            listaPrac[zatrudPrac] = urz;
            zatrudPrac++;
        }
        else
        {
            System.out.println("Pracownik o podanym nazwisku jest już zatrudniony");
        }
    }
    
    void zwolnij(String nazwiskoP)
    {
        Pracownik[] lista2 = new Pracownik[lPrac];
        int indeks = -1;
        int k = 0;
        for(int i=0; i<zatrudPrac; i++)
        {
            if(listaPrac[i].getNazwisko() == nazwiskoP)
            {
                listaPrac[i] = null;
                indeks = i;
            }
        }
        for(int i=0; i<zatrudPrac; i++)
        {
            if(i!=indeks)
            {
                lista2[k] = listaPrac[i];
                k++;
            }
        }
        zatrudPrac--;
        for(int i=0; i<zatrudPrac; i++)
        {
            listaPrac[i] = lista2[i];
        }
    }
    
    String ilu()
    {
        int robotnik = 0;
        int urzednik = 0;
        for(int i=0; i<zatrudPrac; i++)
        {
            if(listaPrac[i] instanceof Robotnik)
            {
                robotnik++;
            }
            else
            {
                if(listaPrac[i] instanceof Urzednik)
                    urzednik++;
            }
        }
        
        return "Robotników: "+robotnik+"\nUrzedników: "+urzednik; 
    }
    
    double wypRob()
    {
        double suma = 0;
        
        for(int i=0; i<zatrudPrac; i++)
        {
            if(listaPrac[i] instanceof Robotnik)
            {
                Robotnik robotnik = (Robotnik)listaPrac[i];
                suma+= robotnik.wyplata();
            }
        }
        
        return suma;
    }
    
    double wypUrz()
    {
        double suma = 0;
        
        for(int i=0; i<zatrudPrac; i++)
        {
            if(listaPrac[i] instanceof Urzednik)
            {
                Urzednik urzednik = (Urzednik)listaPrac[i];
                suma+= urzednik.wyplata();
            }
        }
        
        return suma;
    }
    
    double wyplata(String nazwiskoP)
    {
        int indeks = -1;
        double wyplata = 0;
        for(int i=0; i<zatrudPrac; i++)
        {
            if(listaPrac[i].getNazwisko() == nazwiskoP)
            {
                indeks = i;
            }
        }
        if(indeks >= 0)
        {
            if(listaPrac[indeks] instanceof Robotnik)
            {
                Robotnik robotnik = (Robotnik)listaPrac[indeks];
                wyplata = robotnik.wyplata();
            }
            else 
            {
                if(listaPrac[indeks] instanceof Urzednik)
                {
                    Urzednik urzednik = (Urzednik)listaPrac[indeks];
                    wyplata = urzednik.wyplata();
                }
            }
        }
        else
        System.out.println("Nie ma takiego pracownika");
        
        return wyplata;
    }
    
    void wydrukPlac()
    {   
        System.out.println("Nazwisko" + "  Wynagrodzenie");
        double[] wynagrodzenia = new double[zatrudPrac];
        for(int i=0; i < zatrudPrac; i++)
        {
            wynagrodzenia[i] = wyplata(listaPrac[i].getNazwisko());
            System.out.println(listaPrac[i].getNazwisko() +"    " + wynagrodzenia[i]);
        }        
    }
    
    public static void main(String[] args)
    {
        Firma bob = new Firma(100);
        bob.zatrudnijRob("Nowak",1,10,100);
        bob.zatrudnijRob("Maciborek",0.5,15,230);
        //bob.zatrudnijRob("Nowak",1,10,10);
        bob.zatrudnijUrz("Kowalski",1,2000,10);
        bob.zatrudnijUrz("Janykowycz",1.25,3000,7.5);
        //bob.zatrudnijUrz("Kowalski",1,2000,10);
        System.out.println(bob.ilu());
        System.out.println();
        bob.zwolnij("Nowak");
        //System.out.println(bob.wyplata("Nowak"));
        System.out.println();
        System.out.println(bob.ilu());
        //System.out.println(bob.wypRob());
        System.out.println();
        //System.out.println(bob.wypUrz());
        System.out.println();
        bob.wydrukPlac();
        
        
    }
    
}




