package gg;

import java.io.File;

public class Cache {
    String path;
    Cache() {
        path = new File("").getAbsolutePath() + "\\cache";
        new File(path).mkdirs();
    }


}
