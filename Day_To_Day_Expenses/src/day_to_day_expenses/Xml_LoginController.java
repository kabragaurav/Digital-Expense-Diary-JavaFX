/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day_to_day_expenses;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gaura
 */
public class Xml_LoginController implements Initializable {
    
    
    
    
    
    @FXML
    private Button login_button;
    @FXML 
    private TextField login_username;
    @FXML
    private TextField login_password;

    
    
    
    @FXML
    public void onLogin(ActionEvent event) throws ParseException
    {
        String username = "\""+login_username.getText()+"\"";
        String password = "\""+login_password.getText()+"\"";
        //System.out.println(username+"###"+password);
        try {
            if(username == null || password == null)
                   throw new Exception();
            String url = "jdbc:mysql://localhost:3307/expense_diary";
            String uname = "root";
            String passwd = "";
            String query = "SELECT * FROM login  WHERE username ="+username+" AND password = "+password;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, uname, passwd);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            //System.out.println(rs.equals(null));
            //System.out.println(rs);
            if(rs.next()){
            //ViewPopup vp = new ViewPopup();
            //vp.display("Record","Login Successful!");
            Day_To_Day_Expenses.stage_static.close();
            Parent root = FXMLLoader.load(getClass().getResource("xml_dayToDayExpenses.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            Day_To_Day_Expenses.stage_static = stage;
            Day_To_Day_Expenses.stage_static.getIcons().add(new Image("file:logo.png"));
            Day_To_Day_Expenses.stage_static.setScene(scene);
           Day_To_Day_Expenses.stage_static.setTitle("Digital Expense Diary");
           Day_To_Day_Expenses.stage_static.setResizable(false);
            Day_To_Day_Expenses.stage_static.show();
            }
            else{
                //ViewPopup vp = new ViewPopup();
                //vp.display("Alert", "Invalid Credentials...");
                FactoryClass fc = new FactoryClass();
                FunctionalityProviderInterface fpi = fc.getInstance("popup");
                fpi.display("Alert", "Invalid Credentials...");
            }
            st.close();
            conn.close();
        } catch (Exception ex) {
                //ViewPopup vp = new ViewPopup();
                //vp.display("Alert","Login Details don't match with details provided at the time of installation!");
                FactoryClass fc = new FactoryClass();
                FunctionalityProviderInterface fpi = fc.getInstance("popup");
                fpi.display("Alert","Login Details don't match with details provided at the time of installation!");
        }
    }
    
    
    
    
    
    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
