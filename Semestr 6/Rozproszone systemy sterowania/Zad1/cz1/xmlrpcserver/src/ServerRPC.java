import org.apache.xmlrpc.WebServer;

public class ServerRPC {

    public Integer echo(int x, int y) {
        return new Integer(x + y);
    }

    public boolean isStrongPassword(String pass) {
        int minLength = 8;
        boolean containsNumber = false;
        boolean containsCapitalLetter = false;

        if(pass.length() >= 8) {
            for(int i = 0; i < pass.length(); i++) {
                char sign = pass.charAt(i);
                if(!containsNumber)
                    containsNumber = Character.isDigit(sign);
                if(!containsCapitalLetter)
                    containsCapitalLetter = Character.isUpperCase(sign);
            }

            if(containsNumber && containsCapitalLetter)
                return true;
        }

        return false;

    }

    public int containsChar(String word, String sign, int responseTime) {
        System.out.println("... wywołano asy - odliczam");
        int contains = -2;
        try {
            contains = word.indexOf(sign.charAt(0));
            Thread.sleep(responseTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        return contains;
    }


    public int execAsy(int x) {
        System.out.println("... wywołano asy - odliczam");

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
            System.out.println("Serwer wystartował pomyślnie.");
            System.out.println("Nasłuchuje na porcie: " + port);
            System.out.println("Aby zatrzymać serwer naciśnij ctrl+c");
        } catch(Exception exception) {
            System.err.println("Serwer XML-RPC: " + exception);
        }
    }
}
