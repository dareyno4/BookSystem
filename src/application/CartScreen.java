package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class CartScreen {
	private ScreenController screenController;
	
	public CartScreen(ScreenController screenController) {
		this.screenController = screenController;
	}
	
	 public Scene getScene() {
	    	Text title = new Text("Cart");
	        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

	        Button backButton = new Button("Back to Dashboard"); //returns to buyer screen
	        backButton.setOnAction(e -> screenController.setScene(screenController.getBuyerScene()));
	        
	        Button logoutButton = new Button("Logout");
	        logoutButton.setOnAction(e -> screenController.setScene(screenController.getMainScene()));
	        
	        Button checkoutButton = new Button("Checkout");
	        checkoutButton.setOnAction(e -> screenController.setScene(screenController.getShippingScene()));
	        

	        AnchorPane layout = new AnchorPane();

	       
	        AnchorPane.setTopAnchor(title, 40.0); 
	        AnchorPane.setLeftAnchor(title, (double) 600);  
	        
	        AnchorPane.setTopAnchor(backButton, 10.0); 
	        AnchorPane.setLeftAnchor(backButton, 10.0);  
	        
	        AnchorPane.setTopAnchor(logoutButton, 10.0);
	        AnchorPane.setRightAnchor(logoutButton, 10.0);
	        
	        AnchorPane.setBottomAnchor(checkoutButton, 40.0);
	        AnchorPane.setLeftAnchor(checkoutButton, (double) 600);
	        
	        
	        layout.getChildren().addAll(title, logoutButton, backButton, checkoutButton);
	        return new Scene(layout, 1350, 675);
	    }
}
