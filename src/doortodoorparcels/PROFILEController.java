/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doortodoorparcels;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author HELLO FROM RITHY
 */
public class PROFILEController implements Initializable {

    public Connection connection;
    public PreparedStatement pst;
    @FXML
    private Text total;
    @FXML
    private Text DELIVERED;
    @FXML
    private Text PENDING;
    @FXML
    private Text DAMAGED;
    @FXML
    private Text RETURNED;
    @FXML
    private Text ID;
    @FXML
    private Text NAME;
    @FXML
    private Text EMAIL;
    @FXML
    private Text PHONE;
    @FXML
    private Text totalReceived;
    @FXML
    private Text totalPending;
    @FXML
    private Text ID1;
    @FXML
    private PasswordField PASSWORDUPDATE;
    @FXML
    private TextField UPDATEPHONE;
    @FXML
    private Text ID11;
    @FXML
    private TextField UPDATEUSERNAME;
    @FXML
    private Text ID111;
    @FXML
    private TextField UPDATEUSEREMAIL;
    @FXML
    private Text ID112;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=DOORTODOORPARCEL;selectMethod=cursor", "sa", "123456");
            
            // total parcel registered
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT COUNT(SENT_FROM) AS COUNT FROM PARCELINFO WHERE SENT_FROM =" + FXMLDocumentController.userID);

            resultSet.next();
            total.setText(resultSet.getString("COUNT"));
            
            //total parcel delivered
             resultSet = statement
                    .executeQuery("SELECT COUNT(SENT_FROM) AS COUNT FROM PARCELINFO WHERE DELIVERY_STATUS = 'SUCCESSFUL' AND SENT_FROM ="+FXMLDocumentController.userID);
            
            resultSet.next();
            DELIVERED.setText(resultSet.getString("COUNT"));
            
            // TOTAL PENDING PARCELS
            resultSet = statement
                    .executeQuery("SELECT COUNT(SENT_FROM) AS COUNT FROM PARCELINFO WHERE DELIVERY_STATUS = 'PROCESSING' AND SENT_FROM ="+FXMLDocumentController.userID);
            
            resultSet.next();
            PENDING.setText(resultSet.getString("COUNT"));
            
            //TOTAL RETURNED PARCEL
            resultSet = statement
                    .executeQuery("SELECT COUNT(SENT_FROM) AS COUNT FROM PARCELINFO WHERE DELIVERY_STATUS = 'RETURNED' AND SENT_FROM ="+FXMLDocumentController.userID);
            
            resultSet.next();
            RETURNED.setText(resultSet.getString("COUNT"));
            
            //TOTAL DAMAGED PARCEL
            resultSet = statement
                    .executeQuery("SELECT COUNT(SENT_FROM) AS COUNT FROM PARCELINFO WHERE DELIVERY_STATUS = 'DAMAGED' AND SENT_FROM ="+FXMLDocumentController.userID);
            
            resultSet.next();
            DAMAGED.setText(resultSet.getString("COUNT"));
            
            resultSet = statement
                    .executeQuery("SELECT USERNAME, USEREMAIL,PAYMENT_NUMBER FROM USER1 WHERE USERID ="+FXMLDocumentController.userID);
            
            resultSet.next();
            NAME.setText(resultSet.getString("USERNAME"));
            EMAIL.setText(resultSet.getString("USEREMAIL"));
            PHONE.setText(resultSet.getString("PAYMENT_NUMBER"));
            ID.setText(FXMLDocumentController.userID);
               
           
           
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }

    @FXML
    private void onActionPassword(ActionEvent event) {
         String sql= "UPDATE USER1 set USERPASS = ? where USERID="+FXMLDocumentController.userID+"";
         String pass = PASSWORDUPDATE.getText();
         try {
                pst=connection.prepareStatement(sql);
                pst.setString(1,pass);
                pst.execute();
               
            } catch (Exception e) {
              e.printStackTrace();
            }
    }

    @FXML
    private void onActionPhone(ActionEvent event) {
        String sql= "UPDATE USER1 set USERPHONE = ? where USERID="+FXMLDocumentController.userID+"";
         String phone = UPDATEPHONE.getText();
         try {
                pst=connection.prepareStatement(sql);
                pst.setString(1,phone);
                pst.execute();
                
            } catch (Exception e) {
              e.printStackTrace();
            }
    }

    @FXML
    private void onActionName(ActionEvent event) {
         String sql= "UPDATE USER1 set USERNAME = ? where USERID="+FXMLDocumentController.userID+"";
         String name = UPDATEUSERNAME.getText();
         try {
                pst=connection.prepareStatement(sql);
                pst.setString(1,name);
                pst.execute();
                
            } catch (Exception e) {
              e.printStackTrace();
            }
    }

    @FXML
    private void onActionEmail(ActionEvent event) {
         String sql= "UPDATE USER1 set USEREMAIL = ? where USERID="+FXMLDocumentController.userID+"";
         String email = UPDATEUSEREMAIL.getText();
         try {
                pst=connection.prepareStatement(sql);
                pst.setString(1,email);
                pst.execute();
                
            } catch (Exception e) {
              e.printStackTrace();
            }
    }

}
