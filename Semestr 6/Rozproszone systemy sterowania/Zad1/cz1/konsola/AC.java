// Fabian Kuriata 238016
import org.apache.xmlrpc.AsyncCallback;

import java.net.URL;

public class AC implements AsyncCallback {

    @Override
    public void handleResult(Object result, URL url, String metoda) {
        System.out.println("Wykonano pomyslnie: " + result) ;
    }

    @Override
    public void handleError(Exception e, URL url, String metoda) {
        System.out.println("Błąd");
    }
}
