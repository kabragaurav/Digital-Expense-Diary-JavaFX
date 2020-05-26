/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day_to_day_expenses;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author gaura
 */
public class Confirmer_Delete_DBController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public void OK_Clear_DB() throws Exception{
        try {
            String url = "jdbc:mysql://localhost:3307/expense_diary";
            String uname = "root";
            String passwd = "";
            String query = "DELETE FROM record";
            System.out.println(query);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, uname, passwd);
            Statement st = conn.createStatement();
            int rows_affected = st.executeUpdate(query);                //DML : insertion is dml stmt (data manipulation lang)
            if(rows_affected<=0)
            {
                //ViewPopup vp = new ViewPopup();
                //vp.display("Alert","Command failed to clear DB. Try again.");
                FactoryClass fc = new FactoryClass();
                FunctionalityProviderInterface fpi = fc.getInstance("popup");
                fpi.display("Alert","Command failed to clear DB. Try again.");
            }
            st.close();
            conn.close();
        } catch (Exception ex) {
            //ViewPopup vp = new ViewPopup();
                //vp.display("Alert","Command failed to clear DB. Try again.");
                FactoryClass fc = new FactoryClass();
                FunctionalityProviderInterface fpi = fc.getInstance("popup");
                fpi.display("Alert","Command failed to clear DB. Try again.");
        }
        finally{
            xml_dayToDayExpensesController.stage_clear_db.close();
        }
    }
    public void No_Dont_clear_DB() throws Exception{
            xml_dayToDayExpensesController.stage_clear_db.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
