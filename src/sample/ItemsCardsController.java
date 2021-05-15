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
    ImageView productImage;
    @FXML
    Label favoritLabel;
    @FXML
    ImageView ecoImage;
    @FXML
    Button merInfo;

    @FXML
    public ImageView favorit;
    @FXML
    public Label ecoLabel;



    private ImatMainController parentController;
    private IMatDataHandler iMatDataHandler;
    private int id;
    Image imageFilled = new Image("pic/star (1).png");
    Image image= new Image("pic/star.png");


    public ItemsCardsController(IMatDataHandler iMatDataHandler,
                                ImatMainController imatMainController, int id) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("item_card.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.iMatDataHandler = iMatDataHandler;
        this.id = id;


        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        if (iMatDataHandler.isFavorite(iMatDataHandler.getProduct(id))){
            favorit.setImage(imageFilled);
            favoritLabel.setText("Favorit");
        }
        else if (!iMatDataHandler.isFavorite(iMatDataHandler.getProduct(id))){
            favorit.setImage(image);
            favoritLabel.setText("Ej favorit");
        }


        if(iMatDataHandler.getProduct(id).isEcological()){
            ecoImage.setImage(new Image("pic/eco.png"));

        }


        parentController = imatMainController;
        productName.setText(iMatDataHandler.getProduct(id).getName());
        productPrice.setText(iMatDataHandler.getProduct(id).getPrice() + " " +
                iMatDataHandler.getProduct(id).getUnit());

        productImage.setImage(iMatDataHandler.getFXImage(iMatDataHandler.getProduct(id)));

    }

    private Image getImage(String path) {
        Image image = new Image(path);
        return image;

    }


    @FXML
    public void infoHandler(Event event) {
        System.out.println("hello");
        parentController.getInfoScen();
        parentController.cardImage.setImage(iMatDataHandler.getFXImage(iMatDataHandler.getProduct(id)));
        parentController.detInfoPris.setText(productPrice.getText()+iMatDataHandler.getProduct(id).getUnit());

    }

    @FXML
    public void setFavoritItem() {

        System.out.println("#");

        if (iMatDataHandler.isFavorite(iMatDataHandler.getProduct(id))){
            favorit.setImage(image);
            iMatDataHandler.removeFavorite(iMatDataHandler.getProduct(id));
            favoritLabel.setText("Ej favorit");

            System.out.println("1");

            parentController.flowPane.getChildren().remove(iMatDataHandler.getProduct(id));
        }
        else if (!iMatDataHandler.isFavorite(iMatDataHandler.getProduct(id))){
            favorit.setImage(imageFilled);
            iMatDataHandler.addFavorite(iMatDataHandler.getProduct(id));
            favoritLabel.setText("Favorit");

            System.out.println("2");

        }
        System.out.println(iMatDataHandler.isFavorite(iMatDataHandler.getProduct(id)))   ;

    }
}