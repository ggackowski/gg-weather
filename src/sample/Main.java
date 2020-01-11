package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/sample.fxml"));

        StackPane stackPane = loader.load();

       // Controller controller = loader.getController();
        System.out.println("main");
       // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //Controller controller =
        primaryStage.setTitle("GG Weather");
        primaryStage.setScene(new Scene(stackPane));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
