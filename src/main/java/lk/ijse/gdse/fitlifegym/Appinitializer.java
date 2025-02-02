package lk.ijse.gdse.fitlifegym;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Appinitializer extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Appinitializer.class.getResource("/view/LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Fit Life Gym");

        Image image = new Image(getClass().getResourceAsStream("/images/gym_icon_2.png"));
        stage.getIcons().add(image);

        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }


}
