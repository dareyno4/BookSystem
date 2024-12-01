package application;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;


public class ShippingScreen {
	private ScreenController screenController;
	String cwd = System.getProperty("user.dir");
	private java.nio.file.Path shippingPath = Paths.get(cwd, "src", "application", "shippingInfo.csv"); 
	
	public ShippingScreen (ScreenController screenController){
		this.screenController = screenController;
	}
	
	public Scene getScene() {
    	Text title = new Text("Shipping Information");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        Button cancelButton = new Button("Cancel"); //returns to buyer screen
        cancelButton.setOnAction(e -> screenController.setScene(screenController.getBuyerScene()));
        
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> screenController.setScene(screenController.getMainScene()));
        
        Button nextButton = new Button("Next");
        
        

        AnchorPane layout = new AnchorPane();
        
        // entry fields and positions to take in credit card information
        Label first = new Label("First");
        Label last = new Label("Last");
        Label email = new Label("Email Address");
        Label country = new Label("Country");
        Label address = new Label("Address");
        Label apt = new Label("Apartment/Suite (Optional)");
        Label zip = new Label("Zip Code");
        Label city = new Label("City");
        Label State = new Label("State");
        
        ObservableList<String> countries = 
        		FXCollections.observableArrayList(
        				"United States",
        				"Canada",
        				"Mexico"
        				);
        
        TextField firstName = new TextField();
        firstName.setPromptText("First");
        TextField lastName = new TextField();
        lastName.setPromptText("Last");
        TextField emailAddress = new TextField();
        emailAddress.setPromptText("Email Address");
        ComboBox<String> dropdown = new ComboBox<String>(countries);
        TextField shippingAddress = new TextField();
        shippingAddress.setPromptText("Address");
        TextField aptNumber = new TextField();
        aptNumber.setPromptText("Apt/Suite/Other");
        TextField zipCode = new TextField();
        zipCode.setPromptText("Zip Code");
        TextField cit = new TextField();
        cit.setPromptText("City");
        TextField st = new TextField();
        st.setPromptText("State");
        
       nextButton.setOnAction(e-> {
    	   String f = firstName.getText();
    	   String l = lastName.getText();
    	   String em = emailAddress.getText();
    	   String co = dropdown.getValue();
    	   String sh = shippingAddress.getText();
    	   String zi = zipCode.getText();
    	   String ci = cit.getText();
    	   String s = st.getText();
    	   
    	   if(f.isEmpty() || l.isEmpty() || em.isEmpty() || sh.isEmpty() || zi.isEmpty() || ci.isEmpty() || s.isEmpty()) {
    		   alert(AlertType.ERROR, "Error", "Fields can't be empty");
               return;
       }
    	   if(dropdown.getValue() == null) {
    		   alert(AlertType.ERROR, "Error", "Select Country");
               return;
    	   }
    	   else {
    		   
    		   try {
					if (logShipping(f, l, em, co, sh, zi, ci, s) == true) { //log shipping info
						    Scene paymentScene = screenController.getPaymentScene();
						    Stage stage = (Stage) nextButton.getScene().getWindow(); // Get the current stage
						    stage.setScene(paymentScene); // Set the payment scene
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	   }
       });

       
        AnchorPane.setTopAnchor(title, 40.0); 
        AnchorPane.setLeftAnchor(title, (double) 550);  
        
        AnchorPane.setTopAnchor(first, 110.0);
        AnchorPane.setLeftAnchor(first, 400.0);
        
        AnchorPane.setTopAnchor(firstName, 130.00);
        AnchorPane.setLeftAnchor(firstName,  400.00);
        
        AnchorPane.setTopAnchor(last, 160.00);
        AnchorPane.setLeftAnchor(last, 400.00);
        
        AnchorPane.setTopAnchor(lastName, 180.00);
        AnchorPane.setLeftAnchor(lastName, 400.00);
        
        AnchorPane.setTopAnchor(email, 210.00);
        AnchorPane.setLeftAnchor(email, 400.00);
        
        AnchorPane.setTopAnchor(emailAddress, 230.00);
        AnchorPane.setLeftAnchor(emailAddress, 400.00);
        
        AnchorPane.setTopAnchor(country, 260.00);
        AnchorPane.setLeftAnchor(country, 400.00);
        
        AnchorPane.setTopAnchor(dropdown, 280.00);
        AnchorPane.setLeftAnchor(dropdown, 400.00);
        
        AnchorPane.setTopAnchor(address, 310.00);
        AnchorPane.setLeftAnchor(address, 400.00);
        
        AnchorPane.setTopAnchor(shippingAddress, 330.00);
        AnchorPane.setLeftAnchor(shippingAddress, 400.00);
        
        AnchorPane.setTopAnchor(apt, 360.00);
        AnchorPane.setLeftAnchor(apt,  400.00);
        
        AnchorPane.setTopAnchor(aptNumber, 380.00);
        AnchorPane.setLeftAnchor(aptNumber, 400.00);
        
        AnchorPane.setTopAnchor(zip, 410.00);
        AnchorPane.setLeftAnchor(zip, 400.00);
        
        AnchorPane.setTopAnchor(zipCode, 430.00);
        AnchorPane.setLeftAnchor(zipCode, 400.00);
        
        AnchorPane.setTopAnchor(city, 460.00);
        AnchorPane.setLeftAnchor(city, 400.00);
        
        AnchorPane.setTopAnchor(cit, 480.00);
        AnchorPane.setLeftAnchor(cit, 400.00);
        
        AnchorPane.setTopAnchor(State, 510.00);
        AnchorPane.setLeftAnchor(State, 400.00);
        
        AnchorPane.setTopAnchor(st, 530.00);
        AnchorPane.setLeftAnchor(st, 400.00);
        
        AnchorPane.setBottomAnchor(cancelButton, 40.0); 
        AnchorPane.setLeftAnchor(cancelButton, (double) 580);  
        
        AnchorPane.setTopAnchor(logoutButton, 10.0);
        AnchorPane.setRightAnchor(logoutButton, 10.0);
        
        AnchorPane.setBottomAnchor(nextButton, 40.0);
        AnchorPane.setLeftAnchor(nextButton, (double) 650);
        
        
        
        layout.getChildren().addAll(title, logoutButton, first, firstName, last, lastName, address, email, emailAddress, country, dropdown, shippingAddress, apt, aptNumber, zip, zipCode, city, cit, State, st, nextButton, cancelButton);
        return new Scene(layout, 1350, 675);
	}
	
    private void alert(AlertType type, String title, String content) { //relevant messages to the user
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    private boolean logShipping(String first, String last, String email, String country, String address, String zip, String city, String state) throws IOException { //method to store shipping info for account
        Files.write( //write shipping info to .csv
            shippingPath,
            (first + "," + last + "," + email + "," + address + "," + country + "," + zip + "," + city + "," + state + System.lineSeparator()).getBytes(),
            StandardOpenOption.APPEND //makes sure we append to the .csv file instead of overwriting
        );
        return true;
    }
}
