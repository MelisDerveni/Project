
import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class GroceryApp extends Application {


	@Override
	public void start(Stage primaryStage) {
        primaryStage.setTitle("Grocery App");
        
     // ****************************  Scene 1 Elements  ****************************
        
        Button btn = new Button();
        btn.setText("LOG IN");
        btn.setStyle("-fx-background-color: #7AB5B6; -fx-text-fill: white; " +
                "-fx-background-radius: 15; -fx-pref-width: 150; -fx-pref-height: 40;-fx-font-family: 'SansSerif'; -fx-font-size: 20px;"+ 
                "-fx-font-weight: bold;");
        
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #617C7C; -fx-text-fill: white; " +
                "-fx-background-radius: 18; -fx-pref-width: 150; -fx-pref-height: 40;-fx-font-family: 'SansSerif'; -fx-font-size: 20px;"+ 
                "-fx-font-weight: bold;"));
        
        // Set default style on hover exit
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: #7AB5B6; -fx-text-fill: white; " +
                "-fx-background-radius: 15; -fx-pref-width: 150; -fx-pref-height: 40;-fx-font-family: 'SansSerif'; -fx-font-size: 20px;"+ 
                "-fx-font-weight: bold; "));
 

        // Set default style on click release
        btn.setOnMouseReleased(e -> btn.setStyle("-fx-background-color: #7AB5B6; -fx-text-fill: white; " +
                "-fx-background-radius: 15; -fx-pref-width: 150; -fx-pref-height: 40;-fx-font-family: 'SansSerif'; -fx-font-size: 20px;"+ 
                "-fx-font-weight: bold;"));

        
        
        ImageView imageView = new ImageView(new Image("https://thumbnails.production.thenounproject.com/7TD1kNcqVJSO6ynea_HVZbhXh1I=/fit-in/1000x1000/photos.production.thenounproject.com/photos/9E99E77E-341F-41FB-A59B-A84D6A7D72EB.jpg")); // Replace with the actual path
        // Maintain the image's aspect ratio
        imageView.setFitWidth(primaryStage.getWidth());
        imageView.setFitHeight(primaryStage.getHeight());
        imageView.fitWidthProperty().bind(primaryStage.widthProperty());
        imageView.fitHeightProperty().bind(primaryStage.heightProperty());


        StackPane background = new StackPane();
        Scene scene1=new Scene(background,430,645);        
        GridPane root = new GridPane();
        background.getChildren().addAll(imageView,root);
       
        root.setVgap(10);
        HBox hbox1= new HBox();
        HBox hbox2= new HBox();
        HBox hbox3= new HBox();
        hbox1.setAlignment(Pos.BASELINE_CENTER);
        hbox2.setAlignment(Pos.BASELINE_CENTER);
        hbox3.setAlignment(Pos.BASELINE_CENTER);
        hbox1.setSpacing(7);
        hbox2.setSpacing(7);
        GridPane.setMargin(hbox3, new Insets(20,0,0,0));
        TextField usernameTXField = new TextField();
        PasswordField passwordTXField = new PasswordField();
        usernameTXField.setPromptText("Enter username");
        usernameTXField.setStyle(" -fx-font-size: 20px; -fx-font-weight:bold ;-fx-font-family: 'SansSerif'; -fx-background-color: #f2f2f2; -fx-text-fill: #333333;-fx-border-radius: 7; -fx-border-color: #cccccc; -fx-padding: 5;");
        passwordTXField.setPromptText("Enter password");
        passwordTXField.setStyle("-fx-font-size: 20px;-fx-font-weight:bold; -fx-font-family: 'SansSerif'; -fx-background-color: #f2f2f2; -fx-text-fill: #333333; -fx-border-color: #cccccc; -fx-border-radius: 7; -fx-padding: 5;");
        Label usernameLabel=new Label("USERNAME:");
        usernameLabel.setStyle("-fx-font-size:20px;-fx-font-family: 'SansSerif';-fx-font-weight:bold;-fx-text-fill:#B7C6C6");
        Label passwordLabel=new Label("PASSWORD:");
        passwordLabel.setStyle("-fx-font-size:20px;-fx-font-family: 'SansSerif';-fx-font-weight:bold;-fx-text-fill:#B7C6C6");
        hbox1.getChildren().addAll(usernameLabel);
        hbox2.getChildren().addAll(passwordLabel);
        hbox3.getChildren().add(btn);
       
        
         root.add(hbox1,0,0);
         root.add(usernameTXField, 0, 1);
         root.add(hbox2, 0, 2);
         root.add(passwordTXField, 0, 3);
         
         root.add(hbox3, 0, 4);
         StackPane.setMargin(root,new Insets(100,0,0,0));
         StackPane.setAlignment(root, Pos.CENTER);
         root.setAlignment(Pos.TOP_CENTER);
         DoubleBinding xPosition = Bindings.createDoubleBinding(() ->
         (primaryStage.getWidth() - root.getWidth()) / 2,
         primaryStage.widthProperty(), root.widthProperty());

     root.translateXProperty().bind(xPosition);
     root.setLayoutY(100);

         
         
         // **************************** End of Scene 1 Elements  ****************************
         
         
         
      // ****************************  Scene 2 Elements  ****************************
         
        
         
        
         Button logOut= new Button("Log Out");
         
         logOut.setStyle("-fx-background-color: #7AB5B6; -fx-text-fill: white; " +
                 "-fx-background-radius: 15; -fx-pref-width: 150; -fx-pref-height: 40;-fx-font-family: 'SansSerif'; -fx-font-size: 20px;"+ 
                 "-fx-font-weight: bold;");
         
         logOut.setOnMouseEntered(e -> logOut.setStyle("-fx-background-color: #617C7C; -fx-text-fill: white; " +
                 "-fx-background-radius: 18; -fx-pref-width: 150; -fx-pref-height: 40;-fx-font-family: 'SansSerif'; -fx-font-size: 20px;"+ 
                 "-fx-font-weight: bold;"));
         
         // Set default style on hover exit
         logOut.setOnMouseExited(e -> logOut.setStyle("-fx-background-color: #7AB5B6; -fx-text-fill: white; " +
                 "-fx-background-radius: 15; -fx-pref-width: 150; -fx-pref-height: 40;-fx-font-family: 'SansSerif'; -fx-font-size: 20px;"+ 
                 "-fx-font-weight: bold; "));
         logOut.setOnMouseReleased(e -> logOut.setStyle("-fx-background-color: #7AB5B6; -fx-text-fill: white; " +
                 "-fx-background-radius: 15; -fx-pref-width: 150; -fx-pref-height: 40;-fx-font-family: 'SansSerif'; -fx-font-size: 20px;"+ 
                 "-fx-font-weight: bold;"));
         
         
       
         
         logOut.setOnAction(e->{
      	   primaryStage.setScene(scene1);
      	 primaryStage.setResizable(false);});
         
         
         
      //****************************  End of Scene 2 Elements  ****************************
        
      
        primaryStage.setScene(scene1);
        primaryStage.setResizable(false);
        primaryStage.show();
        
       
         
        btn.setOnAction(new EventHandler<ActionEvent>() 
        {
 
            @Override
            public void handle(ActionEvent event) {
              String username= usernameTXField.getText();
              String password=passwordTXField.getText(); 
              boolean admin=true;

           	
           if (username.equals("") || password.equals("") )
             {

            	 //JOptionPane.showMessageDialog(null,"Username or Password blank.");
             }
            
             else if(LogIn.validateLogIn(username,password))
                    {
            	 	if(LogIn.validateAdmin(username, password)) {
                   	 //JOptionPane.showMessageDialog(null,"Login Success!");
            	 		 admin= true;
                   	 AdminScene.createScene(username,primaryStage,admin,scene1);
                   	 usernameTXField.setText("");
                  	 passwordTXField.setText("");
                  	 primaryStage.setResizable(false);}
                  	 
                  	 else {
                  		 admin= false;
                      	AdminScene.createScene(username,primaryStage,admin,scene1);
                      	 usernameTXField.setText("");
                     	 passwordTXField.setText("");
                     	 primaryStage.setResizable(false);
                  	 }
                    }
                    
                    else
                    {
                   	 JOptionPane.showMessageDialog(null,"Login Failed");
                   	 usernameTXField.setText("");
                   	 passwordTXField.setText("");
                   	 
                    }
                     
                        }
               
                    
                   
                   });
               
             
            
					
  
           }          public static void main(String[] args) {
       		launch(args);}}                   