package application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PaymentScreen {
private ScreenController screenController;
	
	public PaymentScreen(ScreenController screenController) {
		this.screenController = screenController;
	}
	
	public Scene getScene() {
    	Text title = new Text("Payment Information");
        title.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");

        Button cancelButton = new Button("Back"); //returns to buyer screen
        cancelButton.setOnAction(e -> screenController.setScene(screenController.getShippingScene()));
        
        Button logoutButton = new Button("Logout");
        logoutButton.setOnAction(e -> screenController.setScene(screenController.getMainScene()));
        
        Button submitButton = new Button("Submit");
        

        AnchorPane layout = new AnchorPane();
        
        // entry fields and positions to take in credit card information
        Label name = new Label("Name");
        Label address = new Label("Billing Address");
        Label card = new Label("Card Number");
        Label exp = new Label("Expiration Date");
        Label cv = new Label("CVC");
        
        TextField fullName = new TextField();
        fullName.setPromptText("Full name");
        TextField billingAddress = new TextField();
        billingAddress.setPromptText("Billing Address");
        TextField cardNumber = new TextField();
        cardNumber.setPromptText("1234 1234 1234 1234");
        
        DatePicker expirationDate = new DatePicker();
        
        TextField cvc = new TextField();
        cvc.setPromptText("CVC");

        submitButton.setOnAction(e -> {
        	String n = name.getText();
        	String addy = address.getText();
        	String cnum = card.getText();	
        	String sec = cvc.getText();
        	
        	if(n.isEmpty() || addy.isEmpty() || sec.isEmpty()) {
        		alert(AlertType.ERROR, "Error", "Fields can't be empty");
                return;
        	}
        	if (!checkDate(expirationDate)) {
        		alert(AlertType.ERROR, "Error", "Invalid Date");
                return;
        	}
        	else {
        	//card is valid get confirmation screen
        	if (cardValidation(cnum)) {
        		Scene confirmationScene = screenController.getPaymentScene();
			    Stage stage = (Stage) submitButton.getScene().getWindow(); // Get the current stage
			    stage.setScene(confirmationScene); // Set the payment scene
        	}
        }
        	
        	
        });
        
        AnchorPane.setTopAnchor(title, 40.0); 
        AnchorPane.setLeftAnchor(title, (double) 550);  
        
        AnchorPane.setTopAnchor(name, 110.0);
        AnchorPane.setLeftAnchor(name, 400.0);
        
        AnchorPane.setTopAnchor(fullName, 130.00);
        AnchorPane.setLeftAnchor(fullName,  400.00);
        
        AnchorPane.setTopAnchor(address, 160.00);
        AnchorPane.setLeftAnchor(address, 400.00);
        
        AnchorPane.setTopAnchor(billingAddress, 180.00);
        AnchorPane.setLeftAnchor(billingAddress, 400.00);
        
        AnchorPane.setTopAnchor(card, 210.00);
        AnchorPane.setLeftAnchor(card, 400.00);
        
        AnchorPane.setTopAnchor(cardNumber, 230.00);
        AnchorPane.setLeftAnchor(cardNumber, 400.00);
        
        AnchorPane.setTopAnchor(exp, 260.00);
        AnchorPane.setLeftAnchor(exp, 400.00);
        
        AnchorPane.setTopAnchor(expirationDate, 280.00);
        AnchorPane.setLeftAnchor(expirationDate, 400.00);
        
        AnchorPane.setTopAnchor(cv, 310.00);
        AnchorPane.setLeftAnchor(cv, 400.00);
        
        AnchorPane.setTopAnchor(cvc, 330.00);
        AnchorPane.setLeftAnchor(cvc, 400.00);
        
        AnchorPane.setBottomAnchor(cancelButton, 40.0); 
        AnchorPane.setLeftAnchor(cancelButton, (double) 580);  
        
        AnchorPane.setTopAnchor(logoutButton, 10.0);
        AnchorPane.setRightAnchor(logoutButton, 10.0);
        
        AnchorPane.setBottomAnchor(submitButton, 40.0);
        AnchorPane.setLeftAnchor(submitButton, (double) 650);
        
        //side panel that displays items in cart and subtotal
        
        
        
        
        
        layout.getChildren().addAll(title, logoutButton, name, fullName, address, billingAddress, card, cardNumber, exp, expirationDate, cv, cvc, submitButton, cancelButton);
        return new Scene(layout, 1350, 675);
        
        
    }
	
	private void alert(AlertType type, String title, String content) { //relevant messages to the user
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
	
	//validates credit card information/payment
	private boolean cardValidation(String cnum) {
		
		int[] credit = new int[16];
    	for(int p = 0; p < credit.length; p++) {
    		credit[p] = Character.getNumericValue(cnum.charAt(p));
    	}
    	int temp;
    	int j = credit.length-1;
    	//reverses credit card num
    	for(int i = 0; i < credit.length/2; i++) {
    		temp = credit[j];
    		credit[j] = credit[i];
    		credit[i] = temp;
    		j = j - 1;
    		
    	}
    	
    	//doubles every second digit
    	for(int i = 0; i < credit.length; i++) {
    		if(i % 2 == 1) {
    			credit[i] = credit[i] * 2;
    		}
    	}
    	
    	//subtract 9 from doubled digits
    	for(int i = 0; i < credit.length; i++) {
    		if(i % 2 == 1) {
    			if(credit[i] > 9) {
    			credit[i] = credit[i] - 9;
    			}
    		}
    	}
    	
    	//add all digits together
    	int sum = 0;
    	for(int i = 0; i < credit.length; i++) {
    		sum = credit[i] + sum;
    	}
    	
    	//check if number is valid
    	if(sum % 10 == 0) {
		return true; 
    	}
    	else {
    		return false;
    	}
	}
	
	private boolean checkDate(DatePicker exp) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/YY");
		exp.setConverter(new StringConverter<>() {
			@Override
			public String toString(LocalDate date) {
				if (date != null) {
					return format.format(date);
				}
				return"";
			}
		
		@Override
		public LocalDate fromString(String string) {
			if (string != null && !string.isEmpty()) {
				try 
				{
					return LocalDate.parse("01/" + string, DateTimeFormatter.ofPattern("dd/MM/yy"));
				}
				catch (Exception e){
					return null;
				}
			}
			return null;
		}
	});
		exp.setDayCellFactory(picker -> new DateCell() {
			public void updateItem(LocalDate date, boolean empty) {
				super.updateItem(date, empty);
				if (date != null) {
					setText(date.format(DateTimeFormatter.ofPattern("MMM")));
				}
			}
		});
		
		//valid expiration date
		exp.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				LocalDate today = LocalDate.now();
				if (newValue.isBefore(today.withDayOfMonth(1))) {
					alert(AlertType.ERROR, "Error", "Invalid Expiration Date");
	                return;
				}
			}
		});
		return true;

}
	}
