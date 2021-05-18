package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.*;


import java.net.URL;
import java.util.*;
import java.util.List;

public class ImatMainController implements Initializable {
    //database
    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    private List<Order> orderList = iMatDataHandler.getOrders();
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
    Label antalLabel;
    @FXML
    ImageView plus;
    @FXML
    ImageView minus;
    @FXML
    ProgressBar progres;
    @FXML
    Label detInfoPris;

    @FXML
    TextField searchFilter;
    @FXML
    Button historik;


    @FXML
    Label page_label;
    @FXML
    FlowPane flowPane;
    @FXML
    AnchorPane infoScen;
    @FXML
    AnchorPane mainScen;


    @FXML
    ImageView exit;


    int antalInt = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Product p : iMatDataHandler.getProducts()) {
            flowPane.getChildren().add(new ItemsCardsController(iMatDataHandler, this, p.getProductId()));
        }
       // flowPane.setPrefWrapLength(2);
       flowPane.setHgap(7);
        flowPane.setVgap(7);
        antalLabel.setText(antalInt+"");


    }

    public void getInfoScen() {
        infoScen.toFront();

    }

    public void getMainScen(Event event) {
        mainScen.toFront();

    }


    @FXML
    public void vegeFilter() {
        flowPane.getChildren().clear();
        for (Product p : iMatDataHandler.getProducts(ProductCategory.POD))
            flowPane.getChildren().add(new ItemsCardsController(iMatDataHandler, this, p.getProductId()));
        for (Product p : iMatDataHandler.getProducts(ProductCategory.VEGETABLE_FRUIT))
            flowPane.getChildren().add(new ItemsCardsController(iMatDataHandler, this, p.getProductId()));
        for (Product p : iMatDataHandler.getProducts(ProductCategory.BERRY))
            flowPane.getChildren().add(new ItemsCardsController(iMatDataHandler, this, p.getProductId()));
        for (Product p : iMatDataHandler.getProducts(ProductCategory.CITRUS_FRUIT))
            flowPane.getChildren().add(new ItemsCardsController(iMatDataHandler, this, p.getProductId()));
        for (Product p : iMatDataHandler.getProducts(ProductCategory.FRUIT))
            flowPane.getChildren().add(new ItemsCardsController(iMatDataHandler, this, p.getProductId()));
        for (Product p : iMatDataHandler.getProducts(ProductCategory.MELONS))
            flowPane.getChildren().add(new ItemsCardsController(iMatDataHandler, this, p.getProductId()));
        for (Product p : iMatDataHandler.getProducts(ProductCategory.VEGETABLE_FRUIT))
            flowPane.getChildren().add(new ItemsCardsController(iMatDataHandler, this, p.getProductId()));
        for (Product p : iMatDataHandler.getProducts(ProductCategory.ROOT_VEGETABLE))
            flowPane.getChildren().add(new ItemsCardsController(iMatDataHandler, this, p.getProductId()));
        page_label.setText("Grönsaker & Frukt");
    }


    @FXML
    public void breadFilter() {
        flowPane.getChildren().clear();
        for (Product p : iMatDataHandler.getProducts(ProductCategory.BREAD))
            flowPane.getChildren().add(new ItemsCardsController(iMatDataHandler, this, p.getProductId()));
        page_label.setText("Bröd");


    }


    @FXML
    public void meatFilter() {
        flowPane.getChildren().clear();
        for (Product p : iMatDataHandler.getProducts(ProductCategory.MEAT))
            flowPane.getChildren().add(new ItemsCardsController(iMatDataHandler, this, p.getProductId()));
        page_label.setText("Chark");


    }

    @FXML
    public void sweetFilter() {
        flowPane.getChildren().clear();
        for (Product p : iMatDataHandler.getProducts(ProductCategory.SWEET))
            flowPane.getChildren().add(new ItemsCardsController(iMatDataHandler, this, p.getProductId()));
        page_label.setText("Godis");

    }

    @FXML
    public void milkProductFilter() {
        flowPane.getChildren().clear();
        for (Product p : iMatDataHandler.getProducts(ProductCategory.DAIRIES))
            flowPane.getChildren().add(new ItemsCardsController(iMatDataHandler, this, p.getProductId()));
        page_label.setText("Mejeri");
    }

    @FXML
    public void kryddorFilter() {
        flowPane.getChildren().clear();
        for (Product p : iMatDataHandler.getProducts(ProductCategory.HERB))
            flowPane.getChildren().add(new ItemsCardsController(iMatDataHandler, this, p.getProductId()));
        page_label.setText("Kryddor");
    }

    @FXML
    public void dryckFilter() {
        flowPane.getChildren().clear();
        for (Product p : iMatDataHandler.getProducts(ProductCategory.HOT_DRINKS))
            flowPane.getChildren().add(new ItemsCardsController(iMatDataHandler, this, p.getProductId()));
        for (Product p : iMatDataHandler.getProducts(ProductCategory.HOT_DRINKS))
            flowPane.getChildren().add(new ItemsCardsController(iMatDataHandler, this, p.getProductId()));
        page_label.setText("Dryck");
    }


    @FXML
    public void setSearchFilter() {
        page_label.setText("söker...");
        flowPane.getChildren().clear();
        for (Product p : iMatDataHandler.findProducts(searchFilter.getText())){
            flowPane.getChildren().add(new ItemsCardsController(iMatDataHandler, this, p.getProductId()));}
        if(flowPane.getChildren().isEmpty()){
            page_label.setText("Hittade inga varor.. ");
        }

    }

    @FXML
    public void setHistorikCards() {
        flowPane.getChildren().clear();
        page_label.setText("Historik");
        for (Order o : orderList) {
            flowPane.getChildren().add(new historikCardsController(
                    iMatDataHandler, this, o));

        }
    }

    @FXML
    public void setCartCards() {
        flowPane.getChildren().clear();
        page_label.setText("Varukorg");
        for (ShoppingItem s : iMatDataHandler.getShoppingCart().getItems()) {
            flowPane.getChildren().add(new ShoppingCartItem(
                    iMatDataHandler, this, s.getProduct().getProductId()));

        }
    }

    @FXML
    public void setFavoritCards() {
        flowPane.getChildren().clear();
        page_label.setText("Favorit");
        ItemsCardsController itemsCardsController;
        for (Product p : iMatDataHandler.favorites()) {
            itemsCardsController = new ItemsCardsController(iMatDataHandler, this, p.getProductId());
            itemsCardsController.favorit.setImage(new Image("pic/star (1).png"));
            flowPane.getChildren().add(itemsCardsController);

        }
    }


    @FXML
    private void plusAntal(){
        antalInt++;
    antalLabel.setText(antalInt+"");
    }

    @FXML
    private void minusAntal(){
        if(antalInt>0) {
            antalInt--;
            antalLabel.setText(antalInt + "");
        }
    }



}