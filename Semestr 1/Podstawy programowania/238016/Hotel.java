import java.util.Random;

class Hotel
{
    public String nazwa;
    int pieter;
    int tab[][] = new int[pieter][];
    int liczbaPokoi[];
    public Hotel(String nazwa, int pieter, int liczbaPokoi[])
    {
        this.nazwa = nazwa;
        this.pieter = pieter;
        for(int i=0;i<tab.length;i++)
        {
            for(int j=0;j<tab[i].length;j++)
            {
                
            }
            tab[i] = new int[liczbaPokoi[i]];
        }
        
        
    }
    void wyswietl()
    {
        for(int i=0;i<5;i++)
        {
            System.out.println(liczbaPokoi[i]);
        }
    }
    public static void main()//String[] args
    {
      
       int liczbaPokoi[]=new int[5];
       liczbaPokoi=new int[]{3,5,10,4,8};
       Hotel Basia = new Hotel("Basia",5,liczbaPokoi);
       System.out.println(Basia.nazwa);
       Random losowa = new Random();
       Basia.wyswietl();
       do{
        }
        while(false);
       
       for(int i = 0; i<10;i++)
       {
        
        //a = losowa.nextInt(101)+1;
        //a = (int) (Math.random()*100);
        //System.out.println(a); 
        
        
       }
       // int c = (x<y) ? x : y;
       
    }
}