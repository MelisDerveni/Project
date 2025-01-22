
 
	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

	public class CustomerPane {

	    


	    public static StackPane createCustomerPane(String username ,Stage stage,boolean admin,Scene loggedOut) {
	   
	    	 String sqlQuery="SELECT * FROM customers";
	 		TableView<ObservableList<String>> tableView = createTableView(sqlQuery);
	         
	         VBox vbox = new VBox();
	         
	         
	         
	         ChoiceBox<String> customerOptions = new ChoiceBox<>();
	         customerOptions.getItems().addAll("New Customer", "Modify Customer","Remove Customer");
	         customerOptions.setValue("Modify Customer"); 

	         
	         
	         ChoiceBox<String> customerChoiceBox = new ChoiceBox<>();
	         customerChoiceBox.setValue("Choose Customer");
	         populateCustomerNames(customerChoiceBox);
	  
	        
	         Label customerIDLB = new Label("Customer ID:");
	         Label customerNameLB = new Label("Customer Name:");
	         Label contactDetailsLB= new Label("Contact Details:");
	         
	         TextField customerIDTXField= new TextField();
	         TextField customerNameTXField= new TextField();
	         TextField contactDetailsTXField= new TextField();
	         
	         
	         
	         Button addBtn = new Button("Add");
	         Button modifyBtn = new Button("Modify");
	         Button removeBtn = new Button("Remove");
	         		
	         GridPane labelsGrid= new GridPane();
	         labelsGrid.setHgap(10);
	         labelsGrid.setVgap(10);
	         labelsGrid.add(customerIDLB, 0, 0);
	         labelsGrid.add(customerIDTXField, 1, 0);
	         labelsGrid.add(contactDetailsLB, 0, 1);
	         labelsGrid.add(contactDetailsTXField, 1, 1);
	         
	         labelsGrid.add(modifyBtn, 0, 3);
	         
	        
	         
	         
	         
	         
	         customerIDLB.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size:15px;");        
	         customerNameLB.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size:15px;");
	         contactDetailsLB.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size:15px;");

	         vbox.getChildren().clear();
             labelsGrid.getChildren().clear();
             labelsGrid.add(customerIDLB, 0, 0);
             labelsGrid.add(customerIDTXField, 1, 0);
             labelsGrid.add(contactDetailsLB, 0, 1);
             labelsGrid.add(contactDetailsTXField, 1, 1);
             labelsGrid.add(modifyBtn, 0, 2);
	   	     vbox.getChildren().addAll(customerOptions,customerChoiceBox,labelsGrid);
	  
	   	customerOptions.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	             if ("Modify Customer".equals(newValue)) {
	                 vbox.getChildren().clear();
	                 labelsGrid.getChildren().clear();
	                 labelsGrid.add(customerIDLB, 0, 0);
	                 labelsGrid.add(customerIDTXField, 1, 0);
	                 labelsGrid.add(contactDetailsLB, 0, 1);
	                 labelsGrid.add(contactDetailsTXField, 1, 1);
	                 labelsGrid.add(modifyBtn, 0, 2);
	           	  vbox.getChildren().addAll(customerOptions,customerChoiceBox,labelsGrid);
	             } else if ("New Customer".equals(newValue)) {
	                 
	           	  vbox.getChildren().clear();
	           	  labelsGrid.getChildren().clear();
	           	labelsGrid.add(customerIDLB, 0, 0);
	             labelsGrid.add(customerIDTXField, 1, 0);
	             labelsGrid.add(customerNameLB, 0, 1);
	             labelsGrid.add(customerNameTXField, 1, 1);
	             labelsGrid.add(contactDetailsLB, 0, 2);
	             labelsGrid.add(contactDetailsTXField, 1, 2);
	             labelsGrid.add(addBtn, 0, 3);
	           vbox.getChildren().addAll(customerOptions,labelsGrid);
	             }
	             
	             else if ("Remove Customer".equals(newValue))
	             {
	             	 vbox.getChildren().clear();
	             	 
	                  
	            	  vbox.getChildren().addAll(customerOptions,customerChoiceBox,removeBtn);
	             }
	             
	         });
	         
	         
	         
	         vbox.setSpacing(10);
	         
	        
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
		         	if(!customerIDTXField.getText().isEmpty()  && !contactDetailsTXField.getText().isEmpty() )
		         		{
		         		 String sql = "INSERT INTO customers (CustomerID, Name,ContactDetails) VALUES (?, ?, ?)";
		         	        

		         		 Connection connection= LogIn.getConnection();
		         	        try (
		         	            
		         	            PreparedStatement statement = connection.prepareStatement(sql);
		         	        ) {
		         	        
		         	           
		         	            
		         	        	statement.setInt(1,Integer.parseInt(customerIDTXField.getText()) );
		         	        	statement.setString(2, customerNameTXField.getText());
		         	            statement.setString(3, contactDetailsTXField.getText());
		         	           
		      
		         	            statement.executeUpdate();
		         	        } catch (NumberFormatException e1) {
		 						// TODO Auto-generated catch block
		 						e1.printStackTrace();
		 					} catch (SQLException e1) {
		 						// TODO Auto-generated catch block
		 						e1.printStackTrace();
		 					};
		 					grid.getChildren().remove(0, 0);
							grid.add(createTableView(sqlQuery), 0, 0);}
		 					
		         });
		         
		         modifyBtn.setOnAction(e->{
		         	if(!customerIDTXField.getText().isEmpty() && !contactDetailsTXField.getText().isEmpty() )
		         		if(customerChoiceBox.getValue()=="Choose Customer")
		             		;
		             	else{String selectedCustomer= customerChoiceBox.getValue();	
		         	{
		         		 String sql = "UPDATE customers SET CustomerID = ?, ContactDetails=? WHERE Name= ?";
		         	      

		         		 Connection connection= LogIn.getConnection();
		         	        try (
		         	         
		         	            PreparedStatement statement = connection.prepareStatement(sql);
		         	        ) {
		         	        
		         	            
		         	            
		         	        	statement.setInt(1,Integer.parseInt(customerIDTXField.getText()) );
		         	            statement.setString(2, contactDetailsTXField.getText());
		         	            
		         	           
		         	            statement.setString(3, selectedCustomer);
		         	           
		         	            statement.executeUpdate();
		         	        } catch (NumberFormatException e1) {
		 						// TODO Auto-generated catch block
		 						e1.printStackTrace();
		 					} catch (SQLException e1) {
		 						// TODO Auto-generated catch block
		 						e1.printStackTrace();
		 					};
		 					 
		 					}
		 					
		         	grid.getChildren().remove(0, 0);
					grid.add(createTableView(sqlQuery), 0, 0);}
		         });
		         
		         removeBtn.setOnAction(e->{
		         	
		         		if(customerChoiceBox.getValue()=="Choose Customer")
		             		;
		             	else{String selectedCustomer= customerChoiceBox.getValue();	
		         	
		         		 String sql = "DELETE FROM customers WHERE Name = ?";
		         	        

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
							grid.add(createTableView(sqlQuery), 0, 0);}
		 					
		         });
		         
	         
	         StackPane pane = new StackPane();
	         StackPane.setAlignment(vbox, Pos.BASELINE_CENTER);
	         pane.getChildren().add(grid);
	         //StackPane.setAlignment(grid, Pos.CENTER);
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
	           String sql = "SELECT Name FROM customers";
	           try (PreparedStatement statement = conn.prepareStatement(sql)) {
	               ResultSet resultSet = statement.executeQuery();
	               while (resultSet.next()) {
	                   String name = resultSet.getString("Name");
	                   customerNames.add(name);
	               }
	           }
	       } catch (SQLException e) {
	           e.printStackTrace();
	       }
	       return customerNames;
	   }


	 public static TableView<ObservableList<String>> createTableView(String sqlQuery) {
	 	 TableView<ObservableList<String>> tableView = new TableView<>();

	      try {
	          Connection connection = LogIn.getConnection();
	          Statement statement = connection.createStatement();
	          ResultSet resultSet = statement.executeQuery(sqlQuery);

	          
	          for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
	              final int columnIndex = i;
	              TableColumn<ObservableList<String>, String> column = new TableColumn<>(resultSet.getMetaData().getColumnName(i + 1));
	              column.setCellValueFactory(cellData -> {
	                  ObservableList<String> row = cellData.getValue();
	                  return new SimpleStringProperty(row.get(columnIndex));
	              });
	              column.setReorderable(false);
	              column.setResizable(false);
	              column.setPrefWidth(100);
	              column.setStyle("-fx-alignment: CENTER;");
	              tableView.getColumns().add(column);
	          }

	      
	          while (resultSet.next()) {
	              ObservableList<String> row = FXCollections.observableArrayList();
	              for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
	                  row.add(resultSet.getString(i));
	              }
	              tableView.getItems().add(row);
	          }

	          
	          connection.close();
	      } catch (SQLException e) {
	          e.printStackTrace();
	      }
	     // tableView.setMaxWidth(100);
	      
	      
	      double totalWidth = tableView.getColumns().stream()
	              .mapToDouble(column -> column.getWidth())
	              .sum();

	     
	      tableView.setMaxWidth(totalWidth );
	      tableView.setFixedCellSize(25);
	      tableView.setMaxHeight(275);
	     

	      
	     
	      tableView.autosize();
	      return tableView;}
	}

