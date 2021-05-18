package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingCart;

import java.awt.*;
import java.io.IOException;

public class ShoppingCartItem extends AnchorPane {
    IMatDataHandler iMatDataHandler;
    ImatMainController imatMainController;
    private int id;

    @FXML
    ImageView image;
    @FXML Label name;
    @FXML
    Label price;
    @FXML
    Label from;
    @FXML
    Label weight;
    @FXML
    Label amount;

    public ShoppingCartItem(IMatDataHandler iMatDataHandler,
                                   ImatMainController imatMainController,
                            int id){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource
                ("ShoppingCartItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.iMatDataHandler =iMatDataHandler;
        this.imatMainController=imatMainController;
        this.id = id;

        image.setImage(iMatDataHandler.getFXImage(iMatDataHandler.getProduct(id)));
        name.setText(iMatDataHandler.getProduct(id).getName());
        price.setText(iMatDataHandler.getProduct(id).getPrice() + "kr");
        //from.setText(iMatDataHandler.getProduct(id).get);
    }

    public void removeFromCart(){
        iMatDataHandler.getShoppingCart().addProduct(iMatDataHandler.getProduct(id), -1);
    }
}
