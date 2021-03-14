/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doortodoorparcels;
import java.util.Random;  
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author HELLO FROM RITHY
 */
public class MiddleStageController implements Initializable {
     public Connection connection;
     PreparedStatement pst;
    ObservableList listArea = FXCollections.observableArrayList();
    ObservableList listCategory = FXCollections.observableArrayList();
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private TextField address;
    @FXML
    private TextField city;
    @FXML
    private ChoiceBox<String> AreaChoice;
    @FXML
    private ChoiceBox<String> parcelCategory;
    @FXML
    private TextField totalBill;
    @FXML
    private TextField instructions;
    @FXML
    private AnchorPane register;
    @FXML
    private Button submit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadData();
    }    

    @FXML
    private void onActionSubmit(ActionEvent event) {
        String parcelName = name.getText();
        String pAddress = address.getText();
        String pPhone = phone.getText();
        String pCity = city.getText();
        String pSubArea = AreaChoice.getValue();
        String category = parcelCategory.getValue();
        String bill = totalBill.getText();
        String instruction = instructions.getText();
         Random rand = new Random(); 
  
        // Generate random integers in range 0 to 999 
        int rand1 = rand.nextInt(10000); 
        int rand2 = rand.nextInt(10000); 
        String code = "DOOR"+Integer.toString(rand1)+Integer.toString(rand2);
        String sql = "INSERT INTO PARCELINFO(PARCEL_NAME,NUMBER,PARCEL_ADDRESS,CITY,SUB_AREA, PAYMENT, INSTRUCTION, SENT_FROM, PARCEL_CATEGORY, CONFIRMATION_CODE) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=DOORTODOORPARCEL;selectMethod=cursor", "sa", "123456");
            
                pst = connection.prepareStatement(sql);
                pst.setString(1, parcelName);
                pst.setString(2, pPhone);
                pst.setString(3, pAddress);
                pst.setString(4, pCity);
                pst.setString(5, pSubArea);
                pst.setInt(6, Integer.parseInt(bill));
                pst.setString(7, instruction);
                pst.setInt(8,Integer.parseInt(FXMLDocumentController.userID));
                pst.setString(9, category);
                pst.setString(10, code);
                pst.execute();
            
            
        } catch (Exception e) {
            e.printStackTrace();
             System.out.println("ERROR");
        }
    }
    private void loadData()
    {
        listCategory.removeAll(listCategory);
        listArea.removeAll(listArea);
        
        String area1 = "OUTSIDE DHAKA";
        String area2 = "DHANMONDI";
        String area3 = "GULSHAN";
        String area4 = "BANANI";
        String area5 = "BASHUNDHARA R/A";
        String area6 = "UTTARA";
        String area7 = "MOTIJHEEL";
        String area8 = "TEJGAON";
        String area9 = "KHILGAON";
        String area10 = "BANASREE";
        String area11 = "LALBAG";
        String area12 = "SCIENCE LAB";
        String area13 = "SHAHBAG";
        
        listArea.addAll(area1,area2,area3,area4,area5,area6,area7,area8,area9,area10,area11,area12,area13);
        AreaChoice.getItems().addAll(listArea);
        
        String cat1 = "FOOD";
        String cat2 = "CLOTHS";
        String cat3 = "JEWELLERY";
        String cat4 = "SHOES";
        String cat5 = "MAKEUP ITEMS";
        String cat6 = "SKIN CARE ITEMS";
        String cat7 = "ELECTRONIC GADGATES";
        String cat8 = "CRAFTS";
        String cat9 = "MOBILE ACCESSORIES";
        String cat10 = "WATCH";
        String cat11 = "BAGS";
        
       

        listCategory.addAll(cat1,cat2,cat3,cat4,cat5,cat6,cat7,cat8,cat9,cat10,cat11);
        parcelCategory.getItems().addAll(listCategory);
    }
}
