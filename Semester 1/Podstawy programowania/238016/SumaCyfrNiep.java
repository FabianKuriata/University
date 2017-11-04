
/**
 * Write a description of class SumaNieparzystych here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SumaCyfrNiep
{
    private int n;

    public SumaCyfrNiep(int nat)
    {
        n = nat;
    }
    
    int sumaCyfr()
    {   
        int suma = 0;
        
        while(n!=0)
        {
            if((n%10)%2 == 1)
            {
                suma+= n%10;
            }
            n/=10;
        }
        System.out.println(suma);
        return suma;
    }
    
    public static void main()
    {
        SumaCyfrNiep niep = new SumaCyfrNiep(1);
        niep.sumaCyfr();
    }
}
