package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

//a class to control historik cards and add them then to the primary stage
// at the right side of the the splitPane.

public class historikCardsController extends AnchorPane {



    private historikCardsController(){
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource
            ("historikCardsController.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
        fxmlLoader.load();
    } catch (IOException exception) {
        throw new RuntimeException(exception);
    }


}
}