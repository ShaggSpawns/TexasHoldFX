package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameRunner extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		Parent root = FXMLLoader.load(getClass().getResource("game_ui.fxml"));
		Scene scene = new Scene(root, 860, 610);
		primaryStage.setTitle("Texas Hold'em");
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(new Image("file:icon.png"));
		primaryStage.show();
	}
}