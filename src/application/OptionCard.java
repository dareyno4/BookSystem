package application;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Region;
import javafx.geometry.Pos;


//OptionCard.java: Defines design and functionality of option cards for use in the program. Typically used for inter-screen navigation.
//Author: Shahnawaz Syed

public class OptionCard { //option card class allows for abstraction
    public static VBox createCard(String title, String description, Runnable onAction) {
        Region icon = new Region();
        
        Text cardTitle = new Text(title);
        cardTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        
        Text cardDescription = new Text(description);
        cardDescription.setStyle("-fx-font-size: 14px; -fx-text-alignment: center;");
        cardDescription.setWrappingWidth(200);
        
        Hyperlink learnMore = new Hyperlink("Learn more →");
        learnMore.setStyle("-fx-text-fill: black; -fx-underline: true;");
        learnMore.setOnAction(e -> onAction.run());
        
        VBox cardBox = new VBox(15, icon, cardTitle, cardDescription, learnMore);
        cardBox.setPadding(new javafx.geometry.Insets(30));
        cardBox.setAlignment(Pos.CENTER);
        cardBox.setStyle( //css formatting
            "-fx-border-color: black; " +
            "-fx-border-radius: 10; " +
            "-fx-background-radius: 10; " +
            "-fx-background-color: white; " +
            "-fx-cursor: hand;"
        );
        cardBox.setPrefSize(350, 300); //350x300
        
        cardBox.setOnMouseClicked(e -> onAction.run());
        
        return cardBox;
    }
}