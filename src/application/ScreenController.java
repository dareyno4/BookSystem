package application;

import javafx.scene.Scene;
import javafx.stage.Stage;


//ScreenController.java: manages screen switching, specifically defining getters and setters for different scenes
//Author: Shahnawaz Syed

public class ScreenController {
    private Stage stage;
    private Scene mainScene, buyerScene, sellerScene, adminScene, buyerLoginScene, sellerLoginScene, paymentScene, cartScene, shippingScene;

    public ScreenController(Stage stage) {
        this.stage = stage;
        initScenes();
    }

    private void initScenes() {
        MainScreen mainScreen = new MainScreen(this);
        BuyerLogin buyerLogin = new BuyerLogin(this);
        SellerLogin sellerLogin = new SellerLogin(this);
        AdminScreen adminScreen = new AdminScreen(this);
        BuyerScreen buyerScreen = new BuyerScreen(this);
        SellerScreen sellerScreen = new SellerScreen(this);
        CartScreen cartScreen = new CartScreen(this);
        PaymentScreen paymentScreen = new PaymentScreen(this);
        ShippingScreen shippingScreen = new ShippingScreen(this);

        mainScene = mainScreen.getScene();
        buyerLoginScene = buyerLogin.getScene();
        sellerLoginScene = sellerLogin.getScene();
        adminScene = adminScreen.getScene();
        buyerScene = buyerScreen.getScene();
        sellerScene = sellerScreen.getScene();
        cartScene = cartScreen.getScene();
        paymentScene = paymentScreen.getScene();
        shippingScene = shippingScreen.getScene();
    }
    
    
    public Scene getBuyerScene() {
        return buyerScene;
    }

    public Scene getSellerScene() {
        return sellerScene;
    }
    
    
    //getters and setters for scene

    public void setScene(Scene scene) {
        stage.setScene(scene);
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public Scene getBuyerLoginScene() {
        return buyerLoginScene;
    }

    public Scene getSellerLoginScene() {
        return sellerLoginScene;
    }

    public Scene getAdminScene() {
        return adminScene;
    }
    
    public Scene getCartScene() {
    	return cartScene;
    }
    
    public Scene getPaymentScene() {
    	return paymentScene;
    }
    
    public Scene getShippingScene() {
    	return shippingScene;
    }
}
