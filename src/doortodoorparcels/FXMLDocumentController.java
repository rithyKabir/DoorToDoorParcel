/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doortodoorparcels;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 *
 * @author HELLO FROM RITHY
 */
public class FXMLDocumentController implements Initializable {

    public Connection connection;
    public static String userID;
    private Label label;
    @FXML
    private AnchorPane homePage;
    @FXML
    private TextField SignUpName;
    @FXML
    private TextField SignUpEmail;
    @FXML
    private PasswordField SignUpPassword;
    @FXML
    private TextField loginUserID;
    @FXML
    private PasswordField loginPassword;
    @FXML
    private TextField NUMBER;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onActionSignUp(ActionEvent event) {
        PreparedStatement pst;
        String name = SignUpName.getText();
        String num = NUMBER.getText();
        String pass = SignUpPassword.getText();
        String email = SignUpEmail.getText();
        String sql = "INSERT INTO USER1(USERNAME,USERPASS,USEREMAIL,PAYMENT_NUMBER) VALUES (?,?,?,?)";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=DOORTODOORPARCEL;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT USEREMAIL FROM USER1 WHERE USEREMAIL = '" + email + "'");

            boolean flag = false;
            if (resultSet.next()) {

                flag = true;
                System.out.println("Already account exists");

            } else {

                pst = connection.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2, num);
                pst.setString(3, email);
                pst.setInt(4, Integer.parseInt(num));
                pst.execute();

                resultSet = statement
                        .executeQuery("SELECT USERID FROM USER1 WHERE USEREMAIL = '" + email + "'");
                while (resultSet.next()) {
                    this.userID = resultSet.getString("USERID");
                }

                try {
                    Parent pane = FXMLLoader.load(getClass().getResource("homepage.fxml"));
                    Scene scene = new Scene(pane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    DoorToDoorParcels.stage.close();
                    stage.show();;
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }

    }

    @FXML
    private void onActionLoginAsAdmin(ActionEvent event) {
    }

    @FXML
    private void onActionLoginAsUser(ActionEvent event) {

        String ID1 = loginUserID.getText();

        String pass1 = loginPassword.getText();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=DOORTODOORPARCEL;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT UserID,USERPASS FROM USER1 where USERID = '" + ID1 + "'");
            int f1 = 0;
            String ID, pass;
            while (resultSet.next()) {

                ID = resultSet.getString("USERID");

                pass = resultSet.getString("USERPASS");
                System.out.println("Customer NAME:"
                        + resultSet.getString("USERPASS"));
                System.out.println(pass + " " + pass1);
                if (ID.equals(ID1) && pass.equals(pass1)) {
                    f1 = 1;
                    this.userID = ID;
                    System.out.println("done");
                    break;
                }
            }
            if (f1 == 1) {
                try {
                    Parent pane = FXMLLoader.load(getClass().getResource("homepage.fxml"));
                    Scene scene = new Scene(pane);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    DoorToDoorParcels.stage.close();
                    stage.show();;
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }

}
