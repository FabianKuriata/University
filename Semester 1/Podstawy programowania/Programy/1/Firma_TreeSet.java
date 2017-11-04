import java.util.TreeSet;
import java.util.Comparator;
import java.util.Iterator;

public class Firma_TreeSet
{
    TreeSet<Pracownik2> listaPrac = new TreeSet<Pracownik2>();
    
    public Firma_TreeSet()
    {
        
    }
    
    void zatrudnijRob(String n, double e, double s, double godz)
    {
        
        boolean zatrudniony = false;
        Iterator itr = listaPrac.iterator();
        while(itr.hasNext())
        {
            Object element = itr.next();      
            Pracownik2 pracownik = (Pracownik2) element;
            
            if(pracownik.getNazwisko().compareTo(n) == 0)
            {
                System.out.println("Pracownik juz jest zatrudniony");
                zatrudniony = false;
            }
            
            //System.out.println(robotnik.getNazwisko());
        }
        if(zatrudniony = true)
        {
            Robotnik2 rob = new Robotnik2(n,e,s,godz);
            listaPrac.add(rob);
        }
        
    }
    
    void zatrudnijUrz(String n, double e, double placa, double premia)
    {
        Urzednik2 urzednik = new Urzednik2("",0,0,0);
        boolean zatrudniony = false;
        Iterator itr = listaPrac.iterator();
        while(itr.hasNext())
        {
            Object element = itr.next();
            Pracownik2 pracownik = (Pracownik2) element;
            
            if(pracownik.getNazwisko().compareTo(n) == 0)
            {
                System.out.println("Pracownik juz jest zatrudniony");
                zatrudniony = false;
            }
            
            //System.out.println(urzednik.getNazwisko());
        }
        if(zatrudniony = true)
        {
            Urzednik2 urz = new Urzednik2(n,e,placa,premia);
            listaPrac.add(urz);
        }
        
    }
    
    void zwolnij(String nazwiskoP)
    {
       
       Iterator itr = listaPrac.iterator();
       while(itr.hasNext())
       {
           Object element = itr.next();
           Pracownik2 pracownik = (Pracownik2) element;
           if(pracownik.getNazwisko().compareTo(nazwiskoP) == 0)
           {
               listaPrac.remove(pracownik);
           }
       }
    }
    
    String ilu()
    {
        int robotnik = 0;
        int urzednik = 0;
        
        Iterator itr = listaPrac.iterator();
        while(itr.hasNext())
        {
            Object element = itr.next();
            
            if(element instanceof Robotnik2)
                robotnik++;
            else if(element instanceof Urzednik2)
                urzednik++;
        }
        
        return "Robotników: "+robotnik+"\nUrzedników: "+urzednik;
    }
    
    double wypRob()
    {
        double suma = 0;
        
        Iterator itr = listaPrac.iterator();
        while(itr.hasNext())
        {
            Object element = itr.next();
            
            if(element instanceof Robotnik2)
            {
                Robotnik2 robotnik = (Robotnik2) element;
                suma+=robotnik.wyplata();
            }
        }
        
        return suma;
    }
    
    double wypUrz()
    {
        double suma = 0;
        
        Iterator itr = listaPrac.iterator();
        while(itr.hasNext())
        {
            Object element = itr.next();
            
            if(element instanceof Urzednik2)
            {
                Urzednik2 urzednik = (Urzednik2) element;
                suma+=urzednik.wyplata();
            }
        }
        
        return suma;
    }
    
    double wyplata(String nazwiskoP)
    {
        double wyplata = 0;
        
        Iterator itr = listaPrac.iterator();
        while(itr.hasNext())
        {
            Object element = itr.next();
            Pracownik2 pracownik = (Pracownik2) element;
            
            if(element instanceof Robotnik2)
            {
                if(pracownik.getNazwisko().compareTo(nazwiskoP) == 0)
                {
                    Robotnik2 robotnik = (Robotnik2) element;
                    wyplata = robotnik.wyplata();
                }
            }
            else if(element instanceof Urzednik2)
            {
                if(pracownik.getNazwisko().compareTo(nazwiskoP) == 0)
                {
                    Urzednik2 urzednik = (Urzednik2) element;
                    wyplata = urzednik.wyplata();
                }
            }
        }
        
        return wyplata;
    }
    
    void wydrukPlac()
    {
        System.out.println("Nazwisko" + "  Wynagrodzenie");
        double[] wynagrodzenia = new double[listaPrac.size()];
        int k = 0;
        Iterator itr = listaPrac.iterator();
        while(itr.hasNext())
        {
            Object element = itr.next();
            Pracownik2 pracownik = (Pracownik2) element;
            wynagrodzenia[k] = wyplata(pracownik.getNazwisko()); 
            System.out.println(pracownik.getNazwisko() + "   " + wynagrodzenia[k]);
            k++;
        }
    }
    
    void wyswietl()
    {
        
        Iterator itr = listaPrac.iterator();
        while(itr.hasNext())
        {
            
            System.out.println(itr.next());
        }
    }
 
    
    public static void main(String[] args)
    {
        Firma_TreeSet bob = new Firma_TreeSet();
        bob.zatrudnijRob("Nowak",1,10,100);
        bob.zatrudnijRob("Maciborek",0.5,15,210);
        //bob.zatrudnijRob("Nowak",1,10,100);
        bob.zatrudnijUrz("Kowalski",1,1200,20);
        bob.zatrudnijUrz("Janeczko",1.2,2000,10);
        System.out.println(bob.ilu());
        //bob.zwolnij("Nowak");
        System.out.println();
        //System.out.println(bob.ilu());
        System.out.println();
        //System.out.println(bob.wyplata("Kowalski"));
        System.out.println("Suma wypłat robotników: " + bob.wypRob());
        System.out.println("Suma wypłat urzedników: " + bob.wypUrz());
        System.out.println();
        bob.wydrukPlac();
        
        
    }
    
}
