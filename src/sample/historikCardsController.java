package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//a class to control historik cards and add them then to the primary stage
// at the right side of the the splitPane.





public class historikCardsController extends AnchorPane {


    @FXML
    Label datum;
    @FXML
    Button detaljer;
    @FXML
    Label totPris;
    @FXML
    Label antalVaror;
    @FXML
    Label orderNummer;

IMatDataHandler iMatDataHandler;
ImatMainController imatMainController;
Order order;

    public historikCardsController(IMatDataHandler iMatDataHandler,
                                    ImatMainController imatMainController,
                                    Order order){
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource
            ("historikCardsController.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
        fxmlLoader.load();
    } catch (IOException exception) {
        throw new RuntimeException(exception);
    }
        this.iMatDataHandler =iMatDataHandler;
        this.imatMainController=imatMainController;
        this.order = order;



        setDatum();
        totPris.setText(setTotPris()+"");
        antalVaror.setText(setAntalVaror()+"");
        orderNummer.setText(order.getOrderNumber()+"");


}

private void setDatum(){
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    Date date = new Date(System.currentTimeMillis());
    datum.setText(formatter.format(date));
}
private double setTotPris(){
        double price=0;
        for (ShoppingItem s : order.getItems()){
            price = price + s.getTotal();
        }
    return price;
}

private int setAntalVaror(){
       int antalVaror=0;
    for (ShoppingItem s : order.getItems()){
        antalVaror++;
    }
    return antalVaror;
}


}