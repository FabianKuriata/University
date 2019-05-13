public class CzyPierwsza
{
    
    
    public CzyPierwsza()
    {
        
    }
    
    public static boolean czyPierwsza(int x)
    {
        double p = Math.sqrt(x);
        
            for(int i=2; i<= p; i++)
            {
                if(x%i == 0)
                {
                    //System.out.println("false");
                    return false;
                }
            }
       
        //System.out.println("true");
        return true;
    }
    public static int ilePierwszych(int poczatkowa, int koncowa)
    {
       int suma = 0;
       for(int i = poczatkowa; i <=koncowa; i++)
       {
           if(czyPierwsza(i) == true)
                suma+=1;
       }
       System.out.println("Ilosc pierwszych w przedziale rowna się:" + suma);
       return suma;
    }
    public static void main()
    {
        czyPierwsza(5);
        czyPierwsza(4);
        ilePierwszych(2,20);
        
    }
}
