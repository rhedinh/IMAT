package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;


import java.io.IOException;

//a class to control items cards and add them then to the primary stage
// at the right side of the the splitPane.


public class ItemsCardsController extends AnchorPane {



    @FXML
    Label productName;
    @FXML
    Label productPrice;
    @FXML
    Label antal;
    @FXML
    Button merInfo;
    @FXML
    ImageView plus;
    @FXML
    ImageView minus;
    @FXML
    ImageView productImage;

    int antalInt=0;


    private ImatMainController parentController;
    private IMatDataHandler iMatDataHandler;
    private int id;



    public ItemsCardsController(IMatDataHandler iMatDataHandler,
                                ImatMainController imatMainController, int id) {

       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("item_card.fxml"));
       fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.iMatDataHandler = iMatDataHandler;
        this.id=id;


        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }


       parentController=imatMainController;
       productName.setText(iMatDataHandler.getProduct(id).getName());
       productPrice.setText(iMatDataHandler.getProduct(id).getPrice() + "");
       antal.setText(antalInt+"");

       productImage.setImage(iMatDataHandler.getFXImage(iMatDataHandler.getProduct(id)));

    }

    private Image getImage(String path){
         Image image = new Image(path);
         return image;

    }
    @FXML
    private void infoHandler(Event event){
        parentController.getInfoScen();
        parentController.cardImage.setImage(iMatDataHandler.getFXImage(iMatDataHandler.getProduct(id)));

    }

}
