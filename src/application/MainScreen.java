package application;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.geometry.Pos;

//MainScreen.java: defines main screen layout and functionality
//Shahnawaz Syed

public class MainScreen {
    private ScreenController screenController;

    public MainScreen(ScreenController screenController) {
        this.screenController = screenController;
    }

    public Scene getScene() {
        BorderPane mainLayout = new BorderPane();
        
        VBox header = createHeader();
        mainLayout.setTop(header);
        
        VBox mainContent = createMainContent();
        mainLayout.setCenter(mainContent);
        
        return new Scene(mainLayout, 1350, 675); //1350x675 screen size
    }
    
    private VBox createHeader() { //encapsulation
        Text title = new Text("Sun Devil Books");
        title.setStyle("-fx-font-size: 32px; -fx-font-weight: bold;"); //title formatting
        
        Text subtitle = new Text("Use this system to organize your books, whether they're math,\nscience, computer, or English!"); //subtitle
        subtitle.setStyle("-fx-font-size: 16px; -fx-text-alignment: center;"); //subtitle formatting
        
        VBox header = new VBox(10, title, subtitle);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new javafx.geometry.Insets(40, 0, 20, 0));
        return header;
    }
    
    private VBox createMainContent() { //encapsulation
    	//use option card class for abstraction & simplicity
        HBox options = new HBox(30,
            OptionCard.createCard(
                "Buyer",  //buyer option
                "Select this option if you're a buyer\nlooking for books to buy", 
                () -> screenController.setScene(screenController.getBuyerLoginScene())
            ),
            OptionCard.createCard(
                "Seller",  //seller option
                "Select this option if you're a seller\nand you want to sell your books", 
                () -> screenController.setScene(screenController.getSellerLoginScene())
            ),
            OptionCard.createCard(
                "Admin",  //admin option
                "Select this option if you're an\nadministrator", 
                () -> screenController.setScene(screenController.getAdminScene())
            )
        );
        //formatting
        options.setAlignment(Pos.CENTER);
        options.setPadding(new javafx.geometry.Insets(20, 0, 40, 0));
        
        VBox mainContent = new VBox(options);
        mainContent.setAlignment(Pos.CENTER);
        return mainContent;
    }
}