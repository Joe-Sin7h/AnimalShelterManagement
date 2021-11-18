
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
// import javafx.collections.FXCollections; 
// import javafx.collections.ObservableList; 
import javafx.scene.Group;

// import javafx.geometry.Insets; 
// import javafx.geometry.Pos; 

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.ChoiceBoxSkin;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

// import javafx.scene.control.CheckBox; 
// import javafx.scene.control.ChoiceBox; 
// import javafx.scene.control.DatePicker; 
// import javafx.scene.control.ListView; 
// import javafx.scene.control.RadioButton; 
// import javafx.scene.layout.GridPane; 
// import javafx.scene.control.TextField; 
// import javafx.scene.control.ToggleGroup;  
// import javafx.scene.control.ToggleButton; 
// import javafx.scene.input.MouseEvent;
import javafx.stage.Stage; 
         
public class App extends Application { 
   
   // Register widgets
   Button register;
   TextField name, age, weight;
   RadioButton gender;
   CheckBox injured, vaccinated;
   ChoiceBox<String> species;
   
   @Override 
   public void start(Stage stage) throws Exception{  
      GridPane root = new GridPane();
      root.setPadding(new Insets(10, 10, 10, 10));
      Scene scene = new Scene(root,500,500);
      Image img = new Image("happy.png");

      
      
      registerscreen(root);
      
      stage.getIcons().add(img);
      stage.setTitle("Aniaml Shelter Management");
      stage.setScene(scene);
      // stage.setFullScreen(true);
      // stage.setFullScreenExitHint("Welcome to my application");
      stage.show();

   }

   private void registerscreen(GridPane g){

      Label namelabel = new Label("Name : ");
      GridPane.setConstraints(namelabel, 0, 0);
      name = new TextField();
      GridPane.setConstraints(name, 1, 0);

      Label agelabel = new Label("Age : ");
      age = new TextField();
      GridPane.setConstraints(agelabel, 3, 0);
      GridPane.setConstraints(age, 4, 0);

      Label splabel = new Label("Species : ");
      species = new ChoiceBox<>();
      species.getItems().addAll("Dog","Cat");
      species.setValue("Dog");
      GridPane.setConstraints(splabel, 0, 1);
      GridPane.setConstraints(species, 1, 1);

      Label wlabel = new Label("Weight : ");
      weight = new TextField();
      GridPane.setConstraints(wlabel, 3, 1);
      GridPane.setConstraints(weight, 4, 1);

      
      g.getChildren().addAll(name, namelabel, agelabel, age,
      splabel, species, wlabel, weight);
      

   }
            
   public static void main(String args[]){ 
      launch(args); 
   } 
}