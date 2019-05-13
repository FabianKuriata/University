using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace WCF_app
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in both code and config file together.
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]
    public class mojKalkulator : IKalkulator
    {
        double suma = 0;
        public int Dodaj(int n1, int n2)
        {
            int result = n1 + n2;
            Console.WriteLine("Wywołano operację dodawania");
            Console.WriteLine("Otrzymano liczbę: " + n1 + " oraz liczbę: " + n2);
            Console.WriteLine("Wynik operacji: " + result);
            return result;
        }
        public int Odejmij(int n1, int n2)
        {
            int result = n1 - n2;
            Console.WriteLine("Wywołano operację odejmowania");
            Console.WriteLine("Otrzymano liczbę: " + n1 + " oraz liczbę: " + n2);
            Console.WriteLine("Wynik operacji: " + result);
            return result;
        }
        public int Pomnoz(int n1, int n2)
        {
            int result = n1 * n2;
            Console.WriteLine("Wywołano operację mnożenia");
            Console.WriteLine("Otrzymano liczbę: " + n1 + " oraz liczbę: " + n2);
            Console.WriteLine("Wynik operacji: " + result);
            return result;
        }
        public double Podziel(double n1, double n2)
        {
            double result = 0;
            
            result = n1 / n2;

            if(double.IsInfinity(result))
            {
                throw new FaultException("Dzielenie przez zero");
            }
           
            Console.WriteLine("Wywołano operację dzielenia");
            Console.WriteLine("Otrzymano liczbę: " + n1 + " oraz liczbę: " + n2);
            Console.WriteLine("Wynik operacji: " + result);
            return result;
        }
        public int Reszta(int n1, int n2)
        {
            int result = 0;
            try
            {
                result = n1 % n2;
            }
            catch (DivideByZeroException e)
            {
                throw new FaultException(e.Message);
            }
            Console.WriteLine("Wywołano operację dzielenia");
            Console.WriteLine("Otrzymano liczbę: " + n1 + " oraz liczbę: " + n2);
            Console.WriteLine("Wynik operacji: " + result);
            return result;
        }

        public double Sumuj(double n1)
        {
            suma += n1;
            Console.WriteLine("Wywołano operację sumowania");
            Console.WriteLine("Wynik operacji: " + suma);
            return suma;
        }
    }
}
