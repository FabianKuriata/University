using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.ServiceModel.Description;
using System.Text;
using System.Threading.Tasks;
using WCF_app;

namespace WcfServiceHost
{   
    class Program
    {
        public const int PORT = 10011;
        public const string BASENAME = "mojSerwis";
        static void Main(string[] args)
        {
            // Uri dla bazowego adresu serwisu
            Uri baseAddress = new Uri($"http://localhost:{PORT}/{BASENAME}");

            // Utworz instancje serwisu
            ServiceHost mojHost = 
                new ServiceHost(typeof(mojKalkulator), baseAddress);
        
            try
            {
                // Dodaj endpoint
                WSHttpBinding mojBanding = new WSHttpBinding();
                mojHost.AddServiceEndpoint(typeof(IKalkulator),
                                            mojBanding, "endpoint1");
                
                // Ustaw wymiane metadanych
                ServiceMetadataBehavior smb = new ServiceMetadataBehavior();
                smb.HttpGetEnabled = true;
                mojHost.Description.Behaviors.Add(smb);

                // Uruchom serwis
                mojHost.Open();
                Console.WriteLine("Serwis jest uruchomiony.");
                Console.WriteLine("Naciśnij <ENTER> aby zakonczyc");
                Console.WriteLine();
                Console.ReadLine();
                mojHost.Close();
            }
            catch(CommunicationException ce)
            {
                Console.WriteLine("Wystapil wyjatek: {0}", ce.Message);
                mojHost.Abort();
            }
        }   
    }
}
