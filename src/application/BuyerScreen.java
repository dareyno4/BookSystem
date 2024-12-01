package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.image.*;

public class BuyerScreen {
    private ScreenController screenController;

    public BuyerScreen(ScreenController screenController) {
        this.screenController = screenController;
    }

    public Scene getScene() {
        Text title = new Text("Buyer Dashboard");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");
        Button logoutButton = new Button("Logout");
        
        Button viewCart = new Button("Cart");
        viewCart.setPrefSize(Region.USE_COMPUTED_SIZE, 15.0);
        Image img = new Image("shopping-cart.png");
        ImageView imageview = new ImageView(img);
        imageview.setFitHeight(15.0);
        imageview.setPreserveRatio(true);
        viewCart.setGraphic(imageview);
        viewCart.setContentDisplay(ContentDisplay.TOP);
        viewCart.setOnAction(e -> screenController.setScene(screenController.getCartScene()));
     
        logoutButton.setOnAction(e -> screenController.setScene(screenController.getMainScene()));

        AnchorPane layout = new AnchorPane();
        AnchorPane.setTopAnchor(title, 40.0);
        AnchorPane.setLeftAnchor(title, 600.0);
        AnchorPane.setTopAnchor(viewCart, 20.0);
        AnchorPane.setRightAnchor(viewCart, 80.0);
        AnchorPane.setTopAnchor(logoutButton, 10.0);
        AnchorPane.setRightAnchor(logoutButton, 10.0);


        layout.getChildren().addAll(title, viewCart, logoutButton);
        return new Scene(layout, 1350, 675);
    }
}
