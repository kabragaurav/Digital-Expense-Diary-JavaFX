package day_to_day_expenses;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Day_To_Day_Expenses extends Application  {
    static Stage stage_static;
    @Override
    public void start(Stage stage) throws Exception {
        stage_static = stage;
        Parent root = FXMLLoader.load(getClass().getResource("xml_Login.fxml"));        
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("file:login.png"));
        stage.setScene(scene);
        stage.setTitle("Digital Expense Diary Login");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
