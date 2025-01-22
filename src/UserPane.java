import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserPane {



    public static StackPane createUserPane(String username ,Stage stage,boolean admin,Scene loggedOut) {
   
    	 String sqlQuery="SELECT * FROM employees";
	 		TableView<ObservableList<String>> tableView = CustomerPane.createTableView(sqlQuery);
	         
	         VBox vbox = new VBox();
	         
	         
	         
	         ChoiceBox<String> userOptions = new ChoiceBox<>();
	         userOptions.getItems().addAll("New User", "Modify User","Remove User");
	         userOptions.setValue("Modify User"); 

	         
	         
	         ChoiceBox<String> userChoiceBox = new ChoiceBox<>();
	         userChoiceBox.setValue("Choose User");
	         populateCustomerNames(userChoiceBox);
	         
	        
	         Label usernameLB = new Label("Username:");
	         Label passwordLB = new Label("Password:");
	         Label roleLB= new Label("Role:");
	         
	         TextField usernameTXField= new TextField();
	         TextField passwordTXField= new TextField();
	         TextField roleTXField= new TextField();
	         
	         
	         
	         Button addBtn = new Button("Add");
	         Button modifyBtn = new Button("Modify");
	         Button removeBtn = new Button("Remove");
	         		
	         GridPane labelsGrid= new GridPane();
	         labelsGrid.setHgap(10);
	         labelsGrid.setVgap(10);
	         labelsGrid.add(passwordLB, 0, 0);
	         labelsGrid.add(passwordTXField, 1, 0);
	         labelsGrid.add(roleLB, 0, 1);
	         labelsGrid.add(roleTXField, 1, 1);
	         
	         labelsGrid.add(modifyBtn, 0, 2);
	         
	        
	         
	         
	         
	         
	         usernameLB.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size:15px;");        
	         passwordLB.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size:15px;");
	         roleLB.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size:15px;");

	         vbox.getChildren().clear();
          labelsGrid.getChildren().clear();
          labelsGrid.add(passwordLB, 0, 0);
          labelsGrid.add(passwordTXField, 1, 0);
          labelsGrid.add(roleLB, 0, 1);
          labelsGrid.add(roleTXField, 1, 1);
          labelsGrid.add(modifyBtn, 0, 2);
	   	     vbox.getChildren().addAll(userOptions,userChoiceBox,labelsGrid);
	  
	   	  userOptions.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	             if ("Modify User".equals(newValue)) {
	                 vbox.getChildren().clear();
	                 labelsGrid.getChildren().clear();
	                 labelsGrid.add(passwordLB, 0, 0);
	                 labelsGrid.add(passwordTXField, 1, 0);
	                 labelsGrid.add(roleLB, 0, 1);
	                 labelsGrid.add(roleTXField, 1, 1);
	                 labelsGrid.add(modifyBtn, 0, 2);
	           	  vbox.getChildren().addAll(userOptions,userChoiceBox,labelsGrid);
	             } else if ("New User".equals(newValue)) {
	                 
	           	  vbox.getChildren().clear();
	           	  labelsGrid.getChildren().clear();
	           	labelsGrid.add(usernameLB, 0, 0);
	             labelsGrid.add(usernameTXField, 1, 0);
	             labelsGrid.add(passwordLB, 0, 1);
	             labelsGrid.add(passwordTXField, 1, 1);
	             labelsGrid.add(roleLB, 0, 2);
	             labelsGrid.add(roleTXField, 1, 2);
	             labelsGrid.add(addBtn, 0, 3);
	           vbox.getChildren().addAll(userOptions,labelsGrid);
	             }
	             
	             else if ("Remove User".equals(newValue))
	             {
	             	 vbox.getChildren().clear();
	             	 
	                  
	            	  vbox.getChildren().addAll(userOptions,userChoiceBox,removeBtn);
	             }
	             
	         });
	         
	         
	         
	         vbox.setSpacing(10);
	         
	        // for (TableColumn<?, ?> column : tableView.getColumns()) {
	       //	    column.setStyle("-fx-background-color: #FF7F70; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 15px; -fx-font-family: 'SansSerif';");
	       //	}
	         Button backBtn= new Button("Back");
	     
	         backBtn.setOnAction(e->AdminScene.createScene(username, stage, admin,loggedOut));
	         

	         GridPane grid = new GridPane();
	         grid.add(tableView, 0, 0);
	         grid.add(vbox, 1, 0);
	         grid.add(backBtn, 1, 1);
	         grid.setHgap(10);
	         grid.setVgap(10);

	         grid.setPadding(new Insets(20));
	         
	         grid.setAlignment(Pos.CENTER);
	         
	         grid.setMaxHeight(275);

	         addBtn.setOnAction(e->{
		         	if(!usernameTXField.getText().isEmpty()  && !roleTXField.getText().isEmpty()&& !passwordTXField.getText().isEmpty())
		         		{
		         		 String sql = "INSERT INTO employees (Username, Password,Role) VALUES (?, ?, ?)";
		         	        
		         		 Connection connection= LogIn.getConnection();
		         	        try (
		         	           
		         	            PreparedStatement statement = connection.prepareStatement(sql);
		         	        ) {
		         	        
		         	           
		         	            
		         	        	statement.setString(1,usernameTXField.getText() );
		         	        	statement.setString(2, passwordTXField.getText());
		         	            statement.setString(3, roleTXField.getText());
		         	           
		         	          
		         	            statement.executeUpdate();
		         	        } catch (NumberFormatException e1) {
		 						// TODO Auto-generated catch block
		 						e1.printStackTrace();
		 					} catch (SQLException e1) {
		 						// TODO Auto-generated catch block
		 						e1.printStackTrace();
		 					};
		 					grid.getChildren().remove(0, 0);
							grid.add(CustomerPane.createTableView(sqlQuery), 0, 0);}
		 					
		         });
		         
		         modifyBtn.setOnAction(e->{
		         	
		         		if("Choose User".equals(userChoiceBox.getValue()))
		             		;
		         		else if("Klendi".equals(userChoiceBox.getValue()))
		         		{
		         			Alert alert = new Alert(AlertType.INFORMATION);
		         			alert.setHeaderText("Klendi is the Primary Admin!");
		            	    alert.setContentText("You can't modify the man himself!");
		            	    alert.showAndWait();
		         		}
		             	else if (userChoiceBox.getValue()!="Klendi")
		             	{String selectedCustomer= userChoiceBox.getValue();	
		         	{
		         		 String sql = "UPDATE employees SET Password = ?, Role=? WHERE Username= ?";
		         	        

		         		 Connection connection= LogIn.getConnection();
		         	        try (
		         	            PreparedStatement statement = connection.prepareStatement(sql);
		         	        ) {
		         	        
		         	            
		         	        	statement.setString(1,passwordTXField.getText());
		         	            statement.setString(2, roleTXField.getText());
		         	            
		         	            statement.setString(3, selectedCustomer);
		         	            statement.executeUpdate();
		         	       
		 					} catch (SQLException e1) {
		 						// TODO Auto-generated catch block
		 						e1.printStackTrace();
		 					};
		 					 
		 					}
		 					
		         	grid.getChildren().remove(0, 0);
					grid.add(CustomerPane.createTableView(sqlQuery), 0, 0);}
		         });
		         
		         removeBtn.setOnAction(e->{
		         	
		         		if("Choose User".equals(userChoiceBox.getValue()))
		             		;
		         		else if("Klendi".equals(userChoiceBox.getValue()))
		         		{
		         			Alert alert = new Alert(AlertType.INFORMATION);
		         			alert.setHeaderText("Klendi is the Primary Admin!");
		            	    alert.setContentText("You can't remove the man himself!");
		            	    alert.showAndWait();
		         		}
		             	else{String selectedCustomer= userChoiceBox.getValue();	
		         	
		         		 String sql = "DELETE FROM employees WHERE Username = ?";

		         		 Connection connection= LogIn.getConnection();
		         	        try (
		         	            PreparedStatement statement = connection.prepareStatement(sql);
		         	        ) {
		         	        
		         	            
		         	        	
		         	            statement.setString(1, selectedCustomer);
		         	            statement.executeUpdate();
		         	        } catch (NumberFormatException e1) {
		 						// TODO Auto-generated catch block
		 						e1.printStackTrace();
		 					} catch (SQLException e1) {
		 						// TODO Auto-generated catch block
		 						e1.printStackTrace();
		 					};
		 					grid.getChildren().remove(0, 0);
							grid.add(CustomerPane.createTableView(sqlQuery), 0, 0);}
		 					
		         });
		         
	         
	         StackPane pane = new StackPane();
	         StackPane.setAlignment(vbox, Pos.BASELINE_CENTER);
	         pane.getChildren().add(grid);
	         pane.setAlignment(Pos.CENTER);
	         return pane;
	         
	         
	         
	        
	         }
	   private static void populateCustomerNames(ChoiceBox<String> choiceBox) {
	       ArrayList<String> customerNames = fetchCustomerNamesFromDatabase();
	       choiceBox.setItems(FXCollections.observableArrayList(customerNames));
	   }

	   private static ArrayList<String> fetchCustomerNamesFromDatabase() {
	       ArrayList<String> customerNames = new ArrayList<>();
	       try (Connection conn = LogIn.getConnection()) {
	           String sql = "SELECT Username FROM employees";
	           try (PreparedStatement statement = conn.prepareStatement(sql)) {
	               ResultSet resultSet = statement.executeQuery();
	               while (resultSet.next()) {
	                   String name = resultSet.getString("Username");
	                   customerNames.add(name);
	               }
	           }
	       } catch (SQLException e) {
	           e.printStackTrace();
	       }
	       return customerNames;
	   }
}
