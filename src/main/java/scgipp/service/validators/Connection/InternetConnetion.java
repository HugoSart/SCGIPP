package scgipp.service.validators.Connection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author: Marcus Junius Brutus
 * Site: https://stackoverflow.com/questions/1402005/how-to-check-if-internet-connection-is-present-in-java
 */

public class InternetConnetion {

    public static boolean netIsAvailable() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }
}
