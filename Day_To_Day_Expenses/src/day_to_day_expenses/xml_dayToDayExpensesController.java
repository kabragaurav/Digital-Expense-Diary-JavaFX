package day_to_day_expenses;

import com.sun.media.jfxmedia.control.VideoDataBuffer;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author gaura
 */
public class xml_dayToDayExpensesController extends Throwable implements Initializable {
    
    static Stage stage_clear_db = null;
    
    
    

    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private TextField additemname;
    @FXML
    private TextField additemprice;
    @FXML
    private TextField updateitemname;
    @FXML
    private TextField updateitemprice;
    @FXML
    private TextField deleteitem;
    @FXML
    private DatePicker fromdate;
    @FXML
    private DatePicker todate;
    @FXML
    private ChoiceBox category;
    @FXML
    private DatePicker fromdateview;
    @FXML
    private DatePicker todateview;
    @FXML
    private static Button clear_db;
    @FXML
    private static Button summary;
   
    
    
    
    
    
    @FXML
    public void logout(ActionEvent event) throws Exception{
            Day_To_Day_Expenses.stage_static.close();
            Parent root = FXMLLoader.load(getClass().getResource("xml_Login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            Day_To_Day_Expenses.stage_static = stage;
            Day_To_Day_Expenses.stage_static.getIcons().add(new Image("file:login.png"));
            Day_To_Day_Expenses.stage_static.setScene(scene);
            Day_To_Day_Expenses.stage_static.setTitle("Digital Expense Diary Login");
            Day_To_Day_Expenses.stage_static.setResizable(false);
            Day_To_Day_Expenses.stage_static.show();
    }
    
    
    @FXML
    public void clear_DB(ActionEvent event) throws Exception{
        Stage stage = new Stage();
        stage_clear_db = stage;
        Parent root = FXMLLoader.load(getClass().getResource("Confirmer_Delete_DB.fxml"));
            Scene scene = new Scene(root);
            //Stage stage = new Stage();
            stage.getIcons().add(new Image("file:warning.jpg"));
            stage.setScene(scene);
            stage.setTitle("DELETE EVERYTHING?");
            stage.setResizable(false);
            stage.show();
    }
    
    @FXML
    public void letSummarize(ActionEvent event) throws Exception{
        try {
            String url = "jdbc:mysql://localhost:3307/expense_diary";
            String uname = "root";
            String passwd = "";
            String query = "SELECT SUM(price) as sum_price,AVG(price) as avg_price FROM record ";
            System.out.println(query);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, uname, passwd);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);  
            double total = 0;
            double average =0;
            while(rs.next()){
                total += rs.getDouble("sum_price");
                average+=rs.getDouble("avg_price");
            }
            //ViewPopup vp = new ViewPopup();
            String temp = "TOTAL : "+total+"\n AVERAGE (Price per Item) : "+average;
            //vp.display("GRAND SUM",temp);
            FactoryClass fc = new FactoryClass();
            FunctionalityProviderInterface fpi = fc.getInstance("popup");
            fpi.display("GRAND SUM",temp);
            st.close();
            conn.close();
        } catch (Exception ex) {
                //ViewPopup vp = new ViewPopup();
                //vp.display("Alert","Command failed to load record. Try again.");
                FactoryClass fc = new FactoryClass();
                FunctionalityProviderInterface fpi = fc.getInstance("popup");
                fpi.display("Alert","Command failed to load record. Try again.");
        }
        
    }
    

    @FXML
    public void AddAction(ActionEvent event) throws Exception {
        try {
            Date date = new Date();
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            String currentTime = "\"" + sdf.format(date) + "\"";
            //System.out.println(currentTime);
            //System.out.println(date);
            String name = "\"" + additemname.getText() + "\"";  // else name is treated as a column in query
            String amt = additemprice.getText();
            Double price = Double.parseDouble(amt);
            String category_type = "\'" + (String) category.getValue() + "\'";

            String url = "jdbc:mysql://localhost:3307/expense_diary";
            String uname = "root";
            String passwd = "";
            //String query = "INSERT INTO record(serial_no,item_name,price,today,category) VALUES(DEFAULT+'"+name+"',"+price+",DEFAULT,DEFAULT)";
            String query = "INSERT  INTO record(item_name,price,today,category_type) VALUES(" + name + "," + price + "," + currentTime + "," + category_type + ")";
            //System.out.println(query);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, uname, passwd);
            Statement st = conn.createStatement();
            int rows_affected = st.executeUpdate(query);                //DML : insertion is dml stmt (data manipulation lang)
            if(rows_affected<=0)
            {
                //System.out.println("Command failed to insert");
                //ViewPopup vp = new ViewPopup();
                //vp.display("Alert","Command failed to insert. Try again.");
                FactoryClass fc = new FactoryClass();
                FunctionalityProviderInterface fpi = fc.getInstance("popup");
                fpi.display("Alert","Command failed to insert. Try again.");
            }
            else
            {
                //ViewPopup vp = new ViewPopup();
                //vp.display("Success",name+"successfully inserted...");
                FactoryClass fc = new FactoryClass();
                FunctionalityProviderInterface fpi = fc.getInstance("popup");
                fpi.display("Success",name+"successfully inserted...");
            }
            st.close();
            conn.close();
        } catch (Exception ex) {
            //ViewPopup vp = new ViewPopup();
            //vp.display("Alert","Command failed to insert. Try again.");
            FactoryClass fc = new FactoryClass();
            FunctionalityProviderInterface fpi = fc.getInstance("popup");
            fpi.display("Alert","Command failed to insert. Try again.");
        }
    }
    
    public void UpdateAction(ActionEvent event) throws ParseException{
        try {
            String name = "\"" + updateitemname.getText() + "\"";  // else name is treated as a column in query
            String amt = updateitemprice.getText();
            Double price = Double.parseDouble(amt);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            System.out.println(dateFormat.format(date));

            String url = "jdbc:mysql://localhost:3307/expense_diary";
            String uname = "root";
            String passwd = "";
            String query = "UPDATE record SET price = " + price + " WHERE item_name = " + name+"AND today = "+"\""+dateFormat.format(date)+"\"";
            //System.out.println(query);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, uname, passwd);
            Statement st = conn.createStatement();
            int rows_affected = st.executeUpdate(query);                //DML : insertion is dml stmt (data manipulation lang)
            if(rows_affected<=0)
            {
                //ViewPopup vp = new ViewPopup();
                //vp.display("Alert","Command failed to update. Try again.");
                FactoryClass fc = new FactoryClass();
                FunctionalityProviderInterface fpi = fc.getInstance("popup");
                fpi.display("Alert","Command failed to update. Try again.");
            }
            else
            {
                //ViewPopup vp = new ViewPopup();
                //vp.display("Success",name+"successfully updated...");
                FactoryClass fc = new FactoryClass();
                FunctionalityProviderInterface fpi = fc.getInstance("popup");
                fpi.display("Success",name+"successfully updated...");
            }
            st.close();
            conn.close();
        } catch (Exception ex) {
            //ViewPopup vp = new ViewPopup();
                //vp.display("Alert","Command failed to update. Try again.");
            FactoryClass fc = new FactoryClass();
            FunctionalityProviderInterface fpi = fc.getInstance("popup");
            fpi.display("Alert","Command failed to update. Try again.");
        }
    }

    public void DeleteAction(ActionEvent event) throws ParseException {
        try {
            String name = "\"" + deleteitem.getText() + "\"";  // else name is treated as a column in query

            String url = "jdbc:mysql://localhost:3307/expense_diary";
            String uname = "root";
            String passwd = "";
            //String query = "INSERT INTO record(serial_no,item_name,price,today,category) VALUES(DEFAULT+'"+name+"',"+price+",DEFAULT,DEFAULT)";
            String query = "DELETE FROM record WHERE item_name=" + name;
            //System.out.println(query);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, uname, passwd);
            Statement st = conn.createStatement();
            int rows_affected = st.executeUpdate(query);                //DML : insertion is dml stmt (data manipulation lang)
            if(rows_affected<=0)
            {
                //ViewPopup vp = new ViewPopup();
                //vp.display("Alert","Command failed to delete. Try again.");
                FactoryClass fc = new FactoryClass();
                FunctionalityProviderInterface fpi = fc.getInstance("popup");
                fpi.display("Alert","Command failed to delete. Try again.");
            }
            else
            {
                //ViewPopup vp = new ViewPopup();
                //vp.display("Success",name+"successfully deleted...");
                FactoryClass fc = new FactoryClass();
                FunctionalityProviderInterface fpi = fc.getInstance("popup");
                fpi.display("Success",name+"successfully deleted...");
            }
            st.close();
            conn.close();
        } catch (Exception ex) {
            //ViewPopup vp = new ViewPopup();
                //vp.display("Alert","Command failed to delete. Try again.");
            FactoryClass fc = new FactoryClass();
            FunctionalityProviderInterface fpi = fc.getInstance("popup");
            fpi.display("Alert","Command failed to delete. Try again.");
        }
    }

    public void ViewAction(ActionEvent event) throws ParseException {
        try {
            //System.out.println("working");
            LocalDate fromdate =fromdateview.getValue();
            //System.out.println(value);
            LocalDate todate = todateview.getValue();
            //System.out.println(fromdate);
            if(fromdate == null || todate == null)
                   throw new Exception();
            String url = "jdbc:mysql://localhost:3307/expense_diary";
            String uname = "root";
            String passwd = "";
            String query = "SELECT * FROM record WHERE today BETWEEN "+"\""+fromdate+"\""+" AND "+"\""+todate+"\"";
            //System.out.println(query);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, uname, passwd);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);  
            String temp = "";//DDL : selection is ddl stmt (data def lang)
            while(rs.next()){                                            //no method hasNext() in here
			//System.out.print(rs.getString("item_name")); 
			//System.out.print("  : "+rs.getString("price"));  
                        //System.out.println("    :   "+rs.getString("today"));
                        temp = temp+rs.getString("item_name")+"  : "+rs.getString("price")+"    :   "+rs.getString("today")+"\n";
		}
            //ViewPopup vp = new ViewPopup();
            //vp.display("Record",temp);
            FactoryClass fc = new FactoryClass();
            FunctionalityProviderInterface fpi = fc.getInstance("popup");
            fpi.display("Record",temp);
            st.close();
            conn.close();
        } catch (Exception ex) {
                ViewPopup vp = new ViewPopup();
                vp.display("Alert","Command failed to load record. Try again.");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        category.getItems().addAll("Personal Care", "Health", "Kids", "Education", "Entertainment", "Food");
    }

}
