import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage; 

import java.sql.*;
         
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
   Group displaygrid;
   HBox hbox;

   // Scenes
   Scene scene;

   // Table
   TableView table;


   
   @Override 
   public void start(Stage stage) throws Exception{ 

      db = new Mysql(); 
      grid = new GridPane();
      displaygrid = new Group();
      borderPane = new BorderPane();
      hbox = new HBox();

      grid.setPadding(new Insets(30, 30, 30, 30));
      grid.setVgap(25);
      
      // grid.setHgap(15);

      file = new Menu("FILE");
      MenuItem regist = new MenuItem("Registration");
      regist.setOnAction(e -> {
         borderPane.setCenter(grid);
         borderPane.setBottom(hbox);
      });
      MenuItem info = new MenuItem("Animal Info");
      info.setOnAction(e -> fillTable());

      file.getItems().addAll(regist, info);
      info.setStyle("-fx-text-fill: #262729;");
      regist.setStyle("-fx-text-fill: #262729;");

      menubar = new MenuBar();
      menubar.getMenus().add(file);
      menubar.setStyle("-fx-background-color: #262729;");

      borderPane.setTop(menubar);
      borderPane.setCenter(grid);
      borderPane.setBottom(hbox);

      scene = new Scene(borderPane,600,500);
      Image img = new Image("happy.png");

      registerscreen(grid);
      tabledisplay();
      displaygrid.getChildren().addAll(table);
      scene.getStylesheets().add(getClass().getResource("scene.css").toExternalForm());
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
      GridPane.setConstraints(vlabel, 4, 2);
      GridPane.setConstraints(vaccinated, 5, 2);
       
      Label t = new Label("   ");
      Label t2 = new Label("   ");
      GridPane.setConstraints(t, 2, 3);
      GridPane.setConstraints(t2, 3, 4);
      register = new Button("Register");
      register.setOnAction(e -> registerEvent());
      // GridPane.setConstraints(register, 2, 4);

      g.getChildren().addAll(name, namelabel, agelabel, age,
      splabel, species, wlabel, weight,
      ilabel, injured, vlabel, vaccinated, t);

      hbox.getChildren().add(register);
      hbox.setAlignment(Pos.TOP_CENTER);
      // hbox.setPrefWidth(200);
      hbox.setMinHeight(200);
      
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

   private void tabledisplay(){

      
      table = new TableView();
      table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
      TableColumn<Animal, String> IdCol = new TableColumn("Id");
      IdCol.setCellValueFactory(new PropertyValueFactory<>("sno"));
      

      TableColumn<Animal, String> AgeCol = new TableColumn("Age");
      AgeCol.setCellValueFactory(new PropertyValueFactory<>("age"));

      
      TableColumn<Animal, String> NameCol = new TableColumn("Name");
      NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
      // NameCol.setMinWidth(100);

      TableColumn<Animal, String> SpeciesCol = new TableColumn("Species");
      SpeciesCol.setCellValueFactory(new PropertyValueFactory<>("species"));

      TableColumn<Animal, String> WeightCol = new TableColumn("Weight");
      WeightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));

      TableColumn<Animal, String> InjuredCol = new TableColumn("Injured");
      InjuredCol.setCellValueFactory(new PropertyValueFactory<>("injured"));

      TableColumn<Animal, String> Vaccinated = new TableColumn("Vaccinated");
      Vaccinated.setCellValueFactory(new PropertyValueFactory<>("Vaccinated"));
      
      table.getColumns().addAll(IdCol, NameCol, SpeciesCol,
      AgeCol, WeightCol, InjuredCol, Vaccinated);
      
      // table.getItems().add(new Animal("JOE", "Dog", 12, 15, "Yes", "No"));

   }

   private void fillTable(){

      borderPane.setCenter(displaygrid);
      borderPane.setBottom(null);
      ResultSet rs = db.fetchData();
      table.getItems().clear();
      try{
         while(rs.next()){
            table.getItems().add(new Animal( rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6),
            rs.getString(7)));
         }

      }
      catch(Exception e){
         System.out.println(e);
      }
      
   }

   public static void main(String args[]){ 
      launch(args); 
   } 
}