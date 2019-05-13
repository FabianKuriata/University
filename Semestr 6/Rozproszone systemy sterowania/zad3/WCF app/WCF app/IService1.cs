using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace WCF_app
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract]
    public interface IKalkulator
    {
        [OperationContract]
        int Dodaj(int n1, int n2);
        [OperationContract]
        int Odejmij(int n1, int n2);
        [OperationContract]
        int Pomnoz(int n1, int n2);
        [OperationContract]
        double Podziel(double n1, double n2);
        [OperationContract]
        int Reszta(int n1, int n2);
        [OperationContract]
        double Sumuj(double n1);
        
    }

    // Use a data contract as illustrated in the sample below to add composite types to service operations.
    // You can add XSD files into the project. After building the project, you can directly use the data types defined there, with the namespace "RSIZadanie3Ćwiczenie1.ContractType".
    [DataContract]
    public class CompositeType
    {
        bool boolValue = true;
        string stringValue = "Hello ";

        [DataMember]
        public bool BoolValue
        {
            get { return boolValue; }
            set { boolValue = value; }
        }

        [DataMember]
        public string StringValue
        {
            get { return stringValue; }
            set { stringValue = value; }
        }
    }
}
