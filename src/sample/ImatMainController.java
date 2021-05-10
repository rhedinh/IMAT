package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;


import java.net.URL;
import java.util.*;
import java.util.List;

public class ImatMainController implements Initializable {
    //database
    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    private List<ItemsCardsController> products = new ArrayList<>();
    @FXML
    ImageView logo;
    @FXML
    Button mejeri;
    @FXML
    Button chark;
    @FXML
    Button grönsaker;
    @FXML
    Button bröd;
    @FXML
    Button kryddor;
    @FXML
    Button godis;
    @FXML
    Button hygein;

    @FXML
    Button add;
    @FXML
    ImageView cardImage;
    @FXML
    TextArea description;
    @FXML
    ImageView plus;
    @FXML
    ImageView minus;

    @FXML
    Label home_label;
    @FXML FlowPane flowPane;
    @FXML
    AnchorPane infoScen;
    @FXML
    AnchorPane mainScen;


    @FXML
    ImageView exit;
    ItemsCardsController I = new ItemsCardsController
           (iMatDataHandler, this, 17);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     for (Product p: iMatDataHandler.getProducts()){
        flowPane.getChildren().add( new ItemsCardsController(iMatDataHandler, this, p.getProductId()) );
        // System.out.println(p.getCategory());
     }




    }

    public void getInfoScen(){
        infoScen.toFront();

    }
    public void getMainScen(Event event){
        mainScen.toFront();

    }
@FXML
    public void vegeFilter(){
        flowPane.getChildren().clear();
        for (Product p: iMatDataHandler.getProducts()){
            if(p.getCategory().name()=="FRUIT" || p.getCategory().name()=="VEGETABLE_FRUIT"
                    || p.getCategory().name()=="EXOTIC_FRUIT" || p.getCategory().name()=="VEGETABLE_FRUIT"){
                flowPane.getChildren().add(new ItemsCardsController(iMatDataHandler,this, p.getProductId()));
            }
        }

    }


}