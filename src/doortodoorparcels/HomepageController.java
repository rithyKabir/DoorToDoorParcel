/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doortodoorparcels;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author HELLO FROM RITHY
 */
public class HomepageController implements Initializable {

    @FXML
    private BorderPane mainContent;
    @FXML
    private Button profile;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try {
            Parent pane = FXMLLoader.load(getClass().getResource("PROFILE.fxml"));
            mainContent.setCenter(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    @FXML
    private void onActionRegister(ActionEvent event) {
        
        try {
            Parent pane = FXMLLoader.load(getClass().getResource("MiddleStage.fxml"));
            mainContent.setCenter(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onActionParcelStatus(ActionEvent event) {
    }

    @FXML
    private void onActionReturnParcel(ActionEvent event) {
    }

    @FXML
    private void onActionDamagedParcel(ActionEvent event) {
    }

    @FXML
    private void onActionCustomerRating(ActionEvent event) {
    }

    @FXML
    private void onActionPromotions(ActionEvent event) {
    }

    @FXML
    private void onActionComplain(ActionEvent event) {
    }

    @FXML
    private void onActionTransactions(ActionEvent event) {
    }

    @FXML
    private void onActionLogout(ActionEvent event) {
    }

    @FXML
    private void onActionProfile(ActionEvent event) {
        try {
            Parent pane = FXMLLoader.load(getClass().getResource("PROFILE.fxml"));
            mainContent.setCenter(pane);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
