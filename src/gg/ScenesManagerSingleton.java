package gg;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;

public class ScenesManagerSingleton {

    HashMap<String, Scene> scenes;
    Stage stage;

    public ScenesManagerSingleton(Stage s) throws java.io.IOException {
        stage = s;
        scenes = new HashMap<>();
        Parent root;
        String[] scenesNames = {
                "MPickStation",
                "Start",
                "MUV",
                "MGraph",
                "MPlot"
        };

        for (String name : scenesNames) {
            System.out.println(getClass().getResource(name + "Screen.fxml"));
            root= FXMLLoader.load(getClass().getResource(name + "Screen.fxml"));
            Scene scene =new Scene(root);
            System.out.println(scene);
            scenes.put(name, scene);
        }
    }

    public void setScene(String sceneName) {
        if (scenes.containsKey(sceneName)) {
            System.out.println("bla");
            stage.setScene(scenes.get(sceneName));
            stage.show();
        }
        System.out.println("xd");
    }

    public static ScenesManagerSingleton getInstance(Stage s) throws java.io.IOException {
        return new ScenesManagerSingleton(s);
    }
}
