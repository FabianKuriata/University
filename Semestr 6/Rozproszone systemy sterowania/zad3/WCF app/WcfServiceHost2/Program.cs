using WCF_app;
using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.ServiceModel.Description;
using System.Text;
using System.Threading.Tasks;

namespace WcfServiceHost2
{
    class Program
    {
        static void Main(string[] args)
        {
            ServiceHost mojHost = new ServiceHost(typeof(mojKalkulator));

            try
            {
                ServiceEndpoint endpoint1 = mojHost.Description.Endpoints.Find(typeof(IKalkulator));

                ServiceEndpoint endpoint2 = mojHost.Description.Endpoints.Find(
                new Uri("http://localhost:10010/mojSerwis/endpoint2"));
                ServiceEndpoint endpoint3 = mojHost.Description.Endpoints.Find(
                new Uri("http://localhost:20010/mojSerwis/endpoint3"));
                Uri address4 = new Uri("net.tcp://localhost:5000/mojSerwisTCP");
                ServiceEndpoint endpoint4 = mojHost.AddServiceEndpoint(typeof(IKalkulator), new NetTcpBinding(), address4);
                //wyswietl endpointy
                Console.WriteLine("\n---> Endpointy:");

                Console.WriteLine("\nService endpoint {0}:", endpoint1.Name);
                Console.WriteLine("Binding: {0}", endpoint1.Binding.ToString());
                Console.WriteLine("ListenUri: {0}", endpoint1.ListenUri.ToString());

                mojHost.Open();
                Console.WriteLine("\n--> Serwis 1 jest uruchomiony.");

                // Dalszy kod
                ContractDescription cd = ContractDescription.GetContract(typeof(IKalkulator));
                Console.WriteLine("Informacje o kontrakcie:");
                Type contractType = cd.ContractType;
                Console.WriteLine("\tContract type: {0}", contractType.ToString());
                string name = cd.Name;
                Console.WriteLine("\tName: {0}", name);
                OperationDescriptionCollection odc = cd.Operations;
                Console.WriteLine("\tOperacje:");
                foreach(OperationDescription od in odc)
                {
                    Console.WriteLine("\t\t" + od.Name);
                }

                Console.WriteLine("\n--> Nacisnij <ENTER> aby zakonczyc");
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
