package application;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

//AdminScreen.java: Defines layout and functionality of login/splash screen for Admin users
//Author: Shahnawaz Syed


public class AdminScreen {
    private ScreenController screenController;
    
    private Login login;

    public AdminScreen(ScreenController screenController) {
        this.screenController = screenController;
        this.login = new Login("adminCredentials.csv", null); //abstraction
    }

    public Scene getScene() {
    	Text title = new Text("Admin Screen");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        Button backButton = new Button("Back to Main"); //returns to main screen
        backButton.setOnAction(e -> screenController.setScene(screenController.getMainScene()));

        AnchorPane layout = new AnchorPane();

       
        AnchorPane.setTopAnchor(title, 40.0); 
        AnchorPane.setLeftAnchor(title, (double) 600);  
        
        AnchorPane.setTopAnchor(backButton, 10.0); 
        AnchorPane.setLeftAnchor(backButton, 10.0);  
        
        AnchorPane loginPane = login.LoginPane();
        
        layout.getChildren().addAll(title, loginPane, backButton);
        return new Scene(layout, 1350, 675);
    }
}
