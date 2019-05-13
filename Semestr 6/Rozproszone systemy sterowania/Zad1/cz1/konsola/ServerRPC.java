// Fabian Kuriata 238016
import org.apache.xmlrpc.WebServer;

public class ServerRPC {

    public Integer echo(int x, int y) {
        return new Integer(x + y);
    }

    public int execAsy(int x) {
        System.out.println("... wywo³ano asy - odliczam");

        try {
            Thread.sleep(x);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
        System.out.println("... asy - koniec odliczania");
        return (123);
    }

    public static void main(String[] args) {
        try {
            System.out.println("Startuje serwer XML-RPC...");
            int port = 9008; // 10000 + nr komputera
            WebServer server = new WebServer(port);
            server.addHandler("MojSerwer", new ServerRPC());
            server.start();
            System.out.println("Serwer wystartowa³ pomyœlnie.");
            System.out.println("Nas³uchuje na porcie: " + port);
            System.out.println("Aby zatrzymaæ serwer naciœnij ctrl+c");
        } catch(Exception exception) {
            System.err.println("Serwer XML-RPC: " + exception);
        }
    }
}
