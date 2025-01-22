	
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;




	public class AdminScene {

		
		public static void createScene(String username ,Stage stage,boolean admin,Scene loggedOut) {
			
			
	         
	         Label role = new Label();
			 
			if(admin)
				role.setText("MANAGER: "+ username);
			else role.setText("CASHIER: "+ username);
		    StackPane root= new StackPane();
			GridPane grid= new GridPane();
			Image inventoryImage= new Image("https://icon-library.com/images/inventory-icon-png/inventory-icon-png-3.jpg");
			ImageView inventoryImgView = new ImageView(inventoryImage);
			Image cashRegisterImage= new Image("https://icon-library.com/images/cash-register-icon/cash-register-icon-5.jpg");
			ImageView  cashRegisterImgView = new ImageView( cashRegisterImage);
			Image userImage= new Image("https://icon-library.com/images/person-icon-png/person-icon-png-4.jpg");
			ImageView  userImgView = new ImageView( userImage);
			Image salesReportImage= new Image("https://icon-library.com/images/sales-icon-images/sales-icon-images-1.jpg");
			ImageView  salesReportImgView = new ImageView( salesReportImage);
			Image settingsImage= new Image("https://icon-library.com/images/setting-icon-png/setting-icon-png-0.jpg");
			ImageView  settingsImgView = new ImageView( settingsImage);
			Image logOutImage= new Image("https://icon-library.com/images/android-logout-icon/android-logout-icon-4.jpg");
			ImageView  logOutImgView = new ImageView( logOutImage);

			Button backBtn = new Button();
			
			
			StackPane userPane = UserPane.createUserPane(username,stage , admin,loggedOut);
			StackPane transactionPane = TransactionPane.createTransactionPane(username,stage , admin,loggedOut);
			StackPane inventoryPane = InventoryPane.createInventoryPane(username,stage , admin,loggedOut);
			StackPane salesPane = SalesPane.createSalesPane(username,stage , admin,loggedOut);
			StackPane customerPane = CustomerPane.createCustomerPane(username,stage , admin,loggedOut);
			
			grid.add(role,0,3);
			StackPane.setAlignment(role, Pos.CENTER);
			role.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size:25px;");
			double commonImageHeight = 140 ;

			
			double inventoryWidth = (commonImageHeight / inventoryImage.getHeight()) * inventoryImage.getWidth();
			double cashRegisterWidth = (commonImageHeight / cashRegisterImage.getHeight()) * cashRegisterImage.getWidth();
			double userWidth = (commonImageHeight / userImage.getHeight()) * userImage.getWidth();
			double salesReportWidth = (commonImageHeight / salesReportImage.getHeight()) * salesReportImage.getWidth();
			double settingsWidth = (commonImageHeight / settingsImage.getHeight()) * settingsImage.getWidth();
			double logOutWidth = (commonImageHeight / logOutImage.getHeight()) * logOutImage.getWidth();
			
			
			inventoryImgView.setFitWidth(inventoryWidth);
			inventoryImgView.setFitHeight(commonImageHeight);

			cashRegisterImgView.setFitWidth(cashRegisterWidth);
			cashRegisterImgView.setFitHeight(commonImageHeight);

			userImgView.setFitWidth(userWidth);
			userImgView.setFitHeight(commonImageHeight);
			
			salesReportImgView.setFitWidth(salesReportWidth);
			salesReportImgView.setFitHeight(commonImageHeight);
			
			settingsImgView.setFitWidth(settingsWidth);
			settingsImgView.setFitHeight(commonImageHeight);
			
			logOutImgView.setFitWidth(logOutWidth);
			logOutImgView.setFitHeight(commonImageHeight);
			
			if(admin) {
			HBox hbox11 = new HBox();
			hbox11.getChildren().addAll(createIconContainer(userImgView,root,userPane),createIconContainer(cashRegisterImgView,root,transactionPane),createIconContainer(inventoryImgView,root,inventoryPane));
	         
	         grid.add(hbox11, 0, 0);
	         hbox11.setSpacing(30);
	         hbox11.setAlignment(Pos.BASELINE_CENTER);
	         HBox settingsHBox = new HBox();
	         settingsHBox.getChildren().addAll(createIconContainer(salesReportImgView,root,salesPane),createIconContainer(settingsImgView,root,customerPane),createLogOutButton(logOutImgView,loggedOut,stage));
	         settingsHBox.setSpacing(30);
	         grid.setHgap(30);
	         grid.add(settingsHBox, 0, 1);
	         grid.setVgap(30);}
			else {
				HBox hbox11 = new HBox();
				hbox11.getChildren().addAll(createIconContainer(cashRegisterImgView,root,transactionPane),createIconContainer(inventoryImgView,root,inventoryPane));
		         
		         grid.add(hbox11, 0, 0);
		         hbox11.setSpacing(30);
		         hbox11.setAlignment(Pos.BASELINE_CENTER);
		         HBox settingsHBox = new HBox();
		         settingsHBox.getChildren().addAll(createIconContainer(salesReportImgView,root,salesPane),createLogOutButton(logOutImgView,loggedOut,stage));
		         settingsHBox.setSpacing(30);
		         grid.setHgap(30);
		         grid.add(settingsHBox, 0, 1);
		         grid.setVgap(30);}
			
	         
	         Stop[] stops = new Stop[] { 
	        		 new Stop(0, Color.rgb(42, 52, 66)), 
	                 new Stop(1, Color.rgb(47, 55, 61)) 
	             };
	             
	            
	             LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
	             
	             
	             BackgroundFill backgroundFill = new BackgroundFill(gradient, null, null);
	             Background background = new Background(backgroundFill);
	             root.setBackground(background);
	     
		  
			
	        root.getChildren().add(grid);
			grid.setAlignment(Pos.CENTER);
			StackPane rootcopy = root;
			
			
			Scene scene= new Scene(root,1000,600);
			stage.setScene(scene);
			backBtn.setOnAction(e->{
				scene.setRoot(rootcopy);
			});

		}
		
		public static StackPane createIconContainer(ImageView imageView,StackPane root,StackPane addToRoot) {
	        Rectangle rectangle = new Rectangle(170, 170); 
	        rectangle.setArcWidth(20); 
	        rectangle.setArcHeight(20);
	        rectangle.setFill(Color.WHITE); 
	        rectangle.setStroke(Color.BLACK); 
	        rectangle.setStrokeWidth(2); 

	        ScaleTransition rectangleTransition = new ScaleTransition(Duration.millis(70),rectangle);
	        ScaleTransition imageViewTransition = new ScaleTransition(Duration.millis(70),imageView);
	        
	        
	        rectangle.setOnMouseEntered(e->{
	        	rectangleTransition.stop();
	        	imageViewTransition.stop();
	        	rectangleTransition.setToX(1.1);
	        	rectangleTransition.setToY(1.1);
	        	imageViewTransition.setToX(1.1);
	        	imageViewTransition.setToY(1.1);
	        	rectangleTransition.play();
	        	imageViewTransition.play();
	        });
	        
	        rectangle.setOnMouseExited(e->{
	        	rectangleTransition.stop();
	        	imageViewTransition.stop();
	        	rectangleTransition.setToX(1.0);
	        	rectangleTransition.setToY(1.0);
	        	imageViewTransition.setToX(1.0);
	        	imageViewTransition.setToY(1.0);
	        	rectangleTransition.play();
	        	imageViewTransition.play();
	        });
	        
	        imageView.setOnMouseEntered(e->{
	        	rectangleTransition.stop();
	        	imageViewTransition.stop();
	        	rectangleTransition.setToX(1.1);
	        	rectangleTransition.setToY(1.1);
	        	imageViewTransition.setToX(1.1);
	        	imageViewTransition.setToY(1.1);
	        	rectangleTransition.play();
	        	imageViewTransition.play();
	        });
	        
	        imageView.setOnMouseExited(e->{
	        	rectangleTransition.stop();
	        	imageViewTransition.stop();
	        	rectangleTransition.setToX(1.0);
	        	rectangleTransition.setToY(1.0);
	        	imageViewTransition.setToX(1.0);
	        	imageViewTransition.setToY(1.0);
	        	rectangleTransition.play();
	        	imageViewTransition.play();
	        });
	        
	        rectangle.setOnMouseClicked(event -> {
	           root.getChildren().clear();
	            root.getChildren().add(addToRoot);
	           
	        });

	        imageView.setOnMouseClicked(event -> {
	        	root.getChildren().clear();
	            root.getChildren().add(addToRoot);
	            
	            });
	        

	        
	        StackPane iconContainer = new StackPane();
	        iconContainer.getChildren().addAll(rectangle, imageView);

	        return iconContainer;
	    }
	
	
	public static StackPane createLogOutButton(ImageView imageView,Scene loggedOut,Stage stage) {
        Rectangle rectangle = new Rectangle(170, 170);
        rectangle.setArcWidth(20);
        rectangle.setArcHeight(20);
        rectangle.setFill(Color.WHITE); 
        rectangle.setStroke(Color.BLACK); 
        rectangle.setStrokeWidth(2); 

        ScaleTransition rectangleTransition = new ScaleTransition(Duration.millis(70),rectangle);
        ScaleTransition imageViewTransition = new ScaleTransition(Duration.millis(70),imageView);
        
        
        rectangle.setOnMouseEntered(e->{
        	rectangleTransition.stop();
        	imageViewTransition.stop();
        	rectangleTransition.setToX(1.1);
        	rectangleTransition.setToY(1.1);
        	imageViewTransition.setToX(1.1);
        	imageViewTransition.setToY(1.1);
        	rectangleTransition.play();
        	imageViewTransition.play();
        });
        
        rectangle.setOnMouseExited(e->{
        	rectangleTransition.stop();
        	imageViewTransition.stop();
        	rectangleTransition.setToX(1.0);
        	rectangleTransition.setToY(1.0);
        	imageViewTransition.setToX(1.0);
        	imageViewTransition.setToY(1.0);
        	rectangleTransition.play();
        	imageViewTransition.play();
        });
        
        imageView.setOnMouseEntered(e->{
        	rectangleTransition.stop();
        	imageViewTransition.stop();
        	rectangleTransition.setToX(1.1);
        	rectangleTransition.setToY(1.1);
        	imageViewTransition.setToX(1.1);
        	imageViewTransition.setToY(1.1);
        	rectangleTransition.play();
        	imageViewTransition.play();
        });
        
        imageView.setOnMouseExited(e->{
        	rectangleTransition.stop();
        	imageViewTransition.stop();
        	rectangleTransition.setToX(1.0);
        	rectangleTransition.setToY(1.0);
        	imageViewTransition.setToX(1.0);
        	imageViewTransition.setToY(1.0);
        	rectangleTransition.play();
        	imageViewTransition.play();
        });
        
        rectangle.setOnMouseClicked(event -> {
          stage.setScene(loggedOut);
        });

        imageView.setOnMouseClicked(event -> {
        	stage.setScene(loggedOut);
            
            });
        

        
        StackPane iconContainer = new StackPane();
        iconContainer.getChildren().addAll(rectangle, imageView);

        return iconContainer;}}

