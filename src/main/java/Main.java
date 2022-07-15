import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        primaryStage.setScene(root);
        primaryStage.show();
    }
}
