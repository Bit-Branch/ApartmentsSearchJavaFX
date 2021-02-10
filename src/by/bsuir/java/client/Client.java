package by.bsuir.java.client;

import by.bsuir.java.client.controller.ClientController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {

    private final String CLIENT_APP_PATH = "/by/bsuir/java/client/view/Client.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CLIENT_APP_PATH));
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setOnHidden(e -> ((ClientController)fxmlLoader.getController()).shutdown());
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
