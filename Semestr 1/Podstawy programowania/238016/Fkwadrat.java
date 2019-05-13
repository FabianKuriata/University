
/**
 * Write a description of class Fkwadrat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fkwadrat
{
    // instance variables - replace the example below with your own
    public double a;
    public double b;
    public double c;
    public double delta;

    /**
     * Constructor for objects of class Fkwadrat
     */
    public Fkwadrat(double x, double y, double z)
    {
      a = x;
      b = y;
      c = z;
        
    }

 
    public String SprawdzJaka()
    {
      if(a == 0)
      {
          if(b == 0)
          {
              if(c == 0)
              {
                  return "Tożsamość";
              }
                else
                {
                    return "Zbiór pusty";
                }
          }
          else
          {
              return "Funkcja liniowa";
          }
      }
      else
      {
          return "Funkcja kwadratowa";
      }
    }
    public double ObliczDelte()
    {
        if(SprawdzJaka() == "Funkcja kwadratowa")
        {
            
            delta = b*b-4*a*c;
            return delta;
        }
        else
        {
            return 0;
        }
        
    }
    public double ObliczFunkcje()
    {
        if(SprawdzJaka() == "Funkcja kwadratowa")
        {
            ObliczDelte();
            if(delta > 0)
            {
                double x1,x2;
                x1 = ((-b + Math.sqrt(ObliczDelte()))/(2*a));
                x2 = ((-b - Math.sqrt(ObliczDelte()))/(2*a));
                System.out.println("X1="+x1);
                System.out.println("X2="+x2);
            }
            else if(delta == 0)
            {
                double x1;
                x1 = ((-b/(2*a)));
                System.out.println("X1="+x1);
               
            }
            else System.out.println("Delta mniejsza zero");
            return 0;
        }
        else if(SprawdzJaka() == "Funkcja liniowa")
        {
            double x;
            x = -c/b;
            System.out.println("X = "+ x);
        }
        else
        {
            System.out.println(SprawdzJaka());
            return 0;
        }
        return 0;
    }
    public static void main()
    {
        Fkwadrat funkcja = new Fkwadrat(2,5,2);
        funkcja.ObliczFunkcje();
    }
}
