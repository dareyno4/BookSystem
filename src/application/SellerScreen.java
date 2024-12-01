package application;


import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class SellerScreen {
    private ScreenController screenController;

    public SellerScreen(ScreenController screenController) {
        this.screenController = screenController;
    }

    public Scene getScene() {
        Text title = new Text("Seller Dashboard");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> screenController.setScene(screenController.getMainScene()));

        AnchorPane layout = new AnchorPane();
        AnchorPane.setTopAnchor(title, 40.0);
        AnchorPane.setLeftAnchor(title, 600.0);
        AnchorPane.setTopAnchor(logoutButton, 10.0);
        AnchorPane.setRightAnchor(logoutButton, 10.0);


        layout.getChildren().addAll(title, logoutButton);
        return new Scene(layout, 1350, 675);
    }
}

