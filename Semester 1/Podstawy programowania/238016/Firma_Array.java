import java.util.ArrayList;


public class Firma_Array
{
    ArrayList<Pracownik> listaPrac = new ArrayList<Pracownik>();
    
    public Firma_Array()
    {
        
    }
    
    void zatrudnijRob(String n, double e, double s, double godz)
    {
        boolean zatrudniony = false;
        for(Pracownik i : listaPrac)
        {
           if(i.getNazwisko() == n)
           zatrudniony = true;
        }
        if(zatrudniony == false)
        {
            Robotnik rob = new Robotnik(n,e,s,godz);
            listaPrac.add(rob);   
        }
        else
        {
            System.out.println("Pracownik o podanym nazwisku już jest zatrudniony");
        }
        //System.out.println(listaPrac.get(0).getNazwisko());
    }
    
    void zatrudnijUrz(String n, double e, double placa, double premia)
    {
        boolean zatrudniony = false;
        for(Pracownik i : listaPrac)
        {
           if(i.getNazwisko() == n)
           zatrudniony = true;
        }
        if(zatrudniony == false)
        {
            Urzednik urz = new Urzednik(n,e,placa,premia);
            listaPrac.add(urz);   
        }
        else
        {
            System.out.println("Pracownik o podanym nazwisku już jest zatrudniony");
        }
        //System.out.println(listaPrac.get(0).getNazwisko());
    }
    
    void zwolnij(String nazwiskoP)
    {
        Pracownik temp = new Pracownik("",0);
        for(Pracownik i : listaPrac)
        {
            if(i.getNazwisko() == nazwiskoP)
            {
                
                temp = i;
               //System.out.println("jestem");
            }
        }
        listaPrac.remove(temp);
    }
    
    String ilu()
    {
        int robotnik = 0;
        int urzednik = 0;
        
        for(Pracownik i : listaPrac)
        {
            if(i instanceof Robotnik)
                robotnik++;
            else if(i instanceof Urzednik)
                urzednik++;
        }
        
        return "Robotników: "+robotnik+"\nUrzedników: "+urzednik;
    }
    
    double wypRob()
    {
        double suma = 0;
        
        for(Pracownik i : listaPrac)
        {
            if(i instanceof Robotnik)
            {
                Robotnik robotnik = (Robotnik)i;
                suma+=robotnik.wyplata();
            }
                
        }
        
        return suma;
    }
    
    double wypUrz()
    {
        double suma = 0;
        
        for(Pracownik i : listaPrac)
        {
            if(i instanceof Urzednik)
            {
                Urzednik urzednik = (Urzednik)i;
                suma+=urzednik.wyplata();
            }
                
        }
        
        return suma;
    }
   
    double wyplata(String nazwiskoP)
    {
        double wyplata = 0;
        
        for(Pracownik i : listaPrac)
        {
            if(i.getNazwisko() == nazwiskoP)
            {
                if(i instanceof Robotnik)
                {
                    Robotnik robotnik = (Robotnik)i;
                    wyplata = robotnik.wyplata();
                }
                
                if(i instanceof Urzednik)
                {
                    Urzednik urzednik = (Urzednik)i;
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
        for(Pracownik i : listaPrac)
        {
            wynagrodzenia[k] = wyplata(i.getNazwisko());
            System.out.println(i.getNazwisko()+"   "+wynagrodzenia[k]);
            k++;
        }
    }
    
    public static void main(String[] args)
    {
        Firma_Array bob = new Firma_Array();
        bob.zatrudnijRob("Nowak",1,10,100);
        bob.zatrudnijRob("Dąbek",1,15,210);
        //bob.zatrudnijRob("Nowak",1,10,100);
        bob.zatrudnijUrz("Kowalski",1,1000,10);
        bob.zatrudnijUrz("Januszek",0.75,3000,20);
        bob.zwolnij("Nowak");
        System.out.println(bob.ilu());
        System.out.println("Płace robotników: "+bob.wypRob());
        System.out.println("Płace urzedników: "+bob.wypUrz());
        //System.out.println(bob.wyplata("Nowak"));
        System.out.println();
        bob.wydrukPlac();
        
    }
    
}

