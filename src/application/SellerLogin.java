package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;




//SellerScreen.java: Defines layout and functionality of login/splash screen for Seller users
//Author: Shahnawaz Syed




public class SellerLogin {
    private ScreenController screenController;
    private Login login;

    public SellerLogin(ScreenController screenController) {
        this.screenController = screenController;
        this.login = new Login("sellerCredentials.csv", this::onLoginSuccess);
    }

    private void onLoginSuccess() {
        screenController.setScene(screenController.getSellerScene());
    }

    public Scene getScene() {
        Text title = new Text("Seller Screen");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
        Button backButton = new Button("Back to Main");
        backButton.setOnAction(e -> screenController.setScene(screenController.getMainScene()));

        AnchorPane layout = new AnchorPane();
        AnchorPane.setTopAnchor(title, 40.0);
        AnchorPane.setLeftAnchor(title, 600.0);
        AnchorPane.setTopAnchor(backButton, 10.0);
        AnchorPane.setLeftAnchor(backButton, 10.0);

        AnchorPane loginPane = login.LoginPane();
        layout.getChildren().addAll(title, loginPane, backButton);

        return new Scene(layout, 1350, 675);
    }
}


