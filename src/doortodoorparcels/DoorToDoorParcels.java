/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doortodoorparcels;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author HELLO FROM RITHY
 */
public class DoorToDoorParcels extends Application {
    
    public static Stage stage;
    public Connection connection;
    @Override
    public void start(Stage stage) throws Exception {
            this.stage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public void connectDB() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=DOORTODOORPARCEL;selectMethod=cursor", "sa", "123456");

            System.out.println("DATABASE NAME IS:" + connection.getMetaData().getDatabaseProductName());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM USER1");
            
            
            while (resultSet.next()) {
                
                System.out.println("Customer NAME:" + 
                        resultSet.getString("UserName"));
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
             System.out.println("ERROR");
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
        DoorToDoorParcels cnObj = new DoorToDoorParcels();
        cnObj.connectDB();
          launch(args);
    }
    
}
