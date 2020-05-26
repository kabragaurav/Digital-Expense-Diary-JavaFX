package day_to_day_expenses;

import java.text.ParseException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author gaura
 */
public class ViewPopup  implements FunctionalityProviderInterface {

    public void display(String title,String temp) throws ParseException{
        Stage popupwindow = new Stage();
        Button button1 = new Button("Close");
        if(title.equals("Alert")){
                button1.setStyle("-fx-background-color: pink");
                popupwindow.getIcons().add(new Image("file:warning.jpg"));
        }
        else{
            button1.setStyle("-fx-background-color: lightgreen");
            popupwindow.getIcons().add(new Image("file:nike.jpg"));
        }

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle(title);
        
        Label label1 = new Label(temp);

        
        
        
        button1.setOnAction(e -> popupwindow.close());

        VBox layout = new VBox(10);

        layout.getChildren().addAll(label1, button1);

        layout.setAlignment(Pos.CENTER);

        Scene scene1 = new Scene(layout, 300, 250);

        popupwindow.setScene(scene1);

        popupwindow.showAndWait();
        /*
        Scene scene = new Scene(new Group());
        Stage stage = new Stage();;
        stage.setTitle("Record");
        stage.setWidth(600);
        stage.setHeight(600);
 
        final Label label = new Label("Details Are Here!");
        label.setFont(new Font("Arial", 20));
        TableView<Record> table = new TableView<Record>();
        table.setEditable(true);
 
        TableColumn firstNameCol = new TableColumn("Item");
        TableColumn lastNameCol = new TableColumn("Amount");
        TableColumn emailCol = new TableColumn("Date");
        firstNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.4));
        lastNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        emailCol.prefWidthProperty().bind(table.widthProperty().multiply(0.4));
        
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
        Date date=new SimpleDateFormat("yyyy-MM-dd").parse("2019-09-11");  
        table.getItems().add(new Record("edew",17,date));
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
*/

    }

}
