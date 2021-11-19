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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.skin.ChoiceBoxSkin;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
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

   // Databse
   Mysql db;

   // Menu
   Menu file;
   MenuBar menubar;

   // Layouts
   GridPane grid;
   BorderPane borderPane;
   
   @Override 
   public void start(Stage stage) throws Exception{ 

      db = new Mysql(); 
      grid = new GridPane();
      borderPane = new BorderPane();

      grid.setPadding(new Insets(15, 15, 15, 15));
      grid.setVgap(15);
      // grid.setHgap(15);

      file = new Menu("FILE");
      MenuItem regist = new MenuItem("Registration");
      MenuItem info = new MenuItem("Animal Info");

      file.getItems().addAll(regist, info);

      menubar = new MenuBar();
      menubar.getMenus().add(file);

      borderPane.setTop(menubar);
      borderPane.setCenter(grid);
      //  
      Scene scene = new Scene(borderPane,500,500);
      Image img = new Image("happy.png");

      
      
      registerscreen(grid);
      
      stage.getIcons().add(img);
      stage.setTitle("Animal Shelter Management");
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

      Label ilabel = new Label("Injured? ");
      injured = new CheckBox();
      GridPane.setConstraints(ilabel, 0, 2);
      GridPane.setConstraints(injured, 1, 2);

      Label vlabel = new Label("Vaccinated? ");
      vaccinated = new CheckBox();
      GridPane.setConstraints(vlabel, 3, 2);
      GridPane.setConstraints(vaccinated, 4, 2);
      
      register = new Button("Register");
      register.setOnAction(e -> registerEvent());
      GridPane.setConstraints(register, 2, 4);


      g.getChildren().addAll(name, namelabel, agelabel, age,
      splabel, species, wlabel, weight,
      ilabel, injured, vlabel, vaccinated, register);
      
   }
   
   
   private void registerEvent(){
      
      String n,s,i,v;
      int a,w;
      i = "No";
      v = "No";
      n = name.getText();
      s = species.getValue();
      
      if (checkDetails(n,age.getText(),weight.getText())==1){
         a = Integer.parseInt(age.getText());
         w = Integer.parseInt(weight.getText());
         if(injured.isSelected()){
            i= "Yes";
         }
         if(vaccinated.isSelected()){
            v = "Yes";
         }

         Animal ani = new Animal(n,s,a,w,i,v);
         db.insertData(ani);

         Alert alert = new Alert(AlertType.INFORMATION);
         alert.setContentText("Details added!");
         alert.setTitle("SUCCESSFUL");
         alert.show();


      }
      else{
         Alert error = new Alert(AlertType.ERROR);
         // error.setContentText("");
         error.setTitle("ERROR");
         error.show();
         System.out.println("Failed");
      }

   }

   private int checkDetails(String n, String a, String w){
      
      int age_, weight_;
      if (n.length()<=2 ){
         return 0;
      }

      try{
         age_ = Integer.parseInt(a);
         weight_ = Integer.parseInt(w);
      }
      catch(Exception e){
         System.out.println(e);
         return 0;
      }

      return 1;

   }
   public static void main(String args[]){ 
      launch(args); 
   } 
}