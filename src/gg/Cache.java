package gg;

import java.io.File;

/**
 * Klasa Cache służy do przechowywania historycznych zapytań do API
 */
public class Cache {
    String path;
    Cache() {
        path = new File("").getAbsolutePath() + "\\cache";
        new File(path).mkdirs();
    }


}
