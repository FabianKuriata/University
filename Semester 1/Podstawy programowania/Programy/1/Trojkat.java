
/**
 * Write a description of class Trojkat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Trojkat
{
    // instance variables - replace the example below with your own
    double a;
    double b;
    double c;

    /**
     * Constructor for objects of class Trojkat
     */
    public Trojkat(double bokA, double bokB, double bokC)
    {
        // initialise instance variables
        a = bokA;
        b = bokB;
        c = bokC;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public boolean CzyTrojkat()
    {
        // put your code here
        if(a+b>c && b+c>a && c+a>b)
        return true;
        else return false;
    }
    public double Pole()
    {
        if(CzyTrojkat() == true)
        {
            double p;
            double pole;
            p = (a+b+c)/2;
            pole = Math.sqrt(p*(p-a)*(p-b)*(p-c));
            return pole;
        }
        System.out.println("Nie ma takiego trójkąta");
        return 0;
    }
    public String JakiTrojkatK()
    {
        double d;
        if(CzyTrojkat() == true)
        {
                 if(a>b && a>c)
            {
                d = a;
                a = c;
                c = d;
            }
            else if(b>c)
                {
                 d = b;
                 b = c;
                 c = d;
                }
            if(c*c > a*a+b*b)
            {
                return "Rozwartokątny";
            }
            else if(c*c == a*a+b*b)
                    return "Prostokątny";
            else return "Ostrokątny";
        }
        else return "Nie ma takiego trójkąta";
             
    }
    public String JakiTrojkatB()
    {
        if(a == b && b == c)
        {
            return "Równoboczny";
        }
        else if(a == b || b == c || a == c)
                return "Równoramienny";
        else return "Różnoboczny";
    }
    public static void main()
    {
        Trojkat trojkat = new Trojkat(5,4,3);
        System.out.println(trojkat.Pole());
        System.out.println(trojkat.JakiTrojkatK());
        System.out.println(trojkat.JakiTrojkatB());
    }
}
