import org.apache.xmlrpc.XmlRpcClient;

import java.util.Vector;

public class RPC {
    public static void main(String[] args){
        try {
            String partnerIP = "http://10.182.153.66:";
            final int PORT = 9008; // 10000 + nr komputera
            XmlRpcClient srv = new XmlRpcClient( "http://localhost:"+ PORT);

            Vector<Integer> params = new Vector<Integer>();
            params.addElement(new Integer(13));
            params.addElement(new Integer(21));

            Object result = srv.execute("MojSerwer.echo", params);
            int wynik = ((Integer) result).intValue();
            System.out.println("Wynik: " + wynik);



            AC cb = new AC();
            Vector<Integer> params2 = new Vector<Integer>();
            params2.addElement(new Integer(3000));
            srv.executeAsync("MojSerwer.execAsy", params2, cb);
            System.out.println("Wywolano asynchronicznie");

            String password = "Abc3424dwaSA";
            Vector<String> params3 = new Vector<String>();
            params3.addElement(password);

            Object isStrongPass = srv.execute("MojSerwer.isStrongPassword", params3);
            boolean isStrong = (Boolean) isStrongPass;
            if(isStrong) {
                System.out.println("Silne hasło");
            }
            else {
                System.out.println("Słabe hasło");
            }

            ACContainsChar containsChar = new ACContainsChar();
            Vector<Object> params4 = new Vector<Object>();
            String word = "blabla";
            String sign = "a";
            Integer responseTime = new Integer(3000);
            params4.addElement(word);
            params4.addElement(sign);
            params4.addElement(responseTime);
            srv.executeAsync("MojSerwer.containsChar", params4, containsChar);


        } catch (Exception exception) {
            System.err.println("Klient XML-RPC: " + exception);
        }
    }
}