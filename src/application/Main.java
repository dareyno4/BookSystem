package application;

import javafx.application.Application;
import javafx.stage.Stage;


//Main.java: initializes program and screen controller
//Author: Shahnawaz Syed


public class Main extends Application {
    private ScreenController screenController;

    @Override
    public void start(Stage primaryStage) {
        screenController = new ScreenController(primaryStage); //scenecontroller
        primaryStage.setTitle("Sun Devil Books");
        primaryStage.setScene(screenController.getMainScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

