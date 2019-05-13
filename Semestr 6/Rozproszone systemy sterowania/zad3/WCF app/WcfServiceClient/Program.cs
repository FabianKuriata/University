using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WcfServiceClient.ServiceReference1;

namespace WcfServiceClient
{
    class Program
    {
        static void Main(string[] args)
        {
            // Utworzenie instancji WCF proxy
            //KalkulatorClient mojKlient = new KalkulatorClient();
            KalkulatorClient klient1 = new KalkulatorClient("WSHttpBinding_IKalkulator");
            KalkulatorClient klient2 = new KalkulatorClient("mojEndpoint2");
            KalkulatorClient klient3 = new KalkulatorClient("mojEndpoint3");
            KalkulatorClient klient4 = new KalkulatorClient("NetTcpBinding_IKalkulator");

            // Wywolanie operacji uslugi
            // Operacja dodaj
            int value1 = 10;
            int value2 = 20;

           
            double result = klient1.Dodaj(value1, value2);
            Console.WriteLine("Wynik dodawania: " + result);

            // Operacja odejmij
            result = klient1.Odejmij(value1, value2);
            Console.WriteLine("Wynik odejmowania: " + result);

            // Operacja Pomnoz
            result = klient1.Pomnoz(value1, value2);
            Console.WriteLine("Wynik mnożenia: " + result);

            // Operacja Podziel
            double divVal1 = 30.0;
            double divVal2 = 10.5;

            result = klient1.Podziel(divVal1, divVal2);
            Console.WriteLine("Wynik dzielenia: " + result);
            int res2;
            int moduloVal1 = 10;
            int moduloVal2 = 20;
            res2 = klient1.Reszta(moduloVal1, moduloVal2);
            Console.WriteLine("Wynik modulo: " + res2);

            double val = 20.0;
            result = klient1.Sumuj(val);
            Console.WriteLine("1. Wynik sumowania: " + result);
            result = klient1.Sumuj(val);
            Console.WriteLine("2. Wynik sumowania: " + result);

            // Operacja Podziel
            //result = mojKlient.Podziel(value1, value2);
            //Console.WriteLine(result);

            // Zamkniecie klienta zamyka polaczenie i czysci zasoby
            klient1.Close();
        }
    }
}
