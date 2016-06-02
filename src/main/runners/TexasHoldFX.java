package main.runners;

import game.Game;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TexasHoldFX extends Application {
    private static Stage stage;
    public static final int WIDTH = 860;
    public static final int HEIGHT = 610;

    public static Game game;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("../resources/main_menu_ui.fxml"));
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setTitle("Texas Hold'em");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:icon.png"));
        primaryStage.show();
    }

    public static Stage getStage() {
        return stage;
    }
}
