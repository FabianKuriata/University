// Fabian Kuriata 238016
import org.apache.xmlrpc.XmlRpcClient;

import java.util.Vector;

public class RPC {
    public static void main(String[] args){
        try {
            final int PORT = 9008; // 10000 + nr komputera
            XmlRpcClient srv = new XmlRpcClient("http://10.182.153.66:" + PORT);

            Vector<Integer> params = new Vector<Integer>();
            params.addElement(new Integer(17));
            params.addElement(new Integer(13));

            Object result = srv.execute("MojSerwer.echo", params);
            int wynik = ((Integer) result).intValue();
            System.out.println("Wynik: " + wynik);

            AC cb = new AC();
            Vector<Integer> params2 = new Vector<Integer>();
            params2.addElement(new Integer(3000));
            srv.executeAsync("MojSerwer.execAsy", params2, cb);
            System.out.println("Wywolano asynchronicznie");
	    

        } catch (Exception exception) {
            System.err.println("Klient XML-RPC: " + exception);
        }
    }
}