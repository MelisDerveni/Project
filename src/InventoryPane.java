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

public class InventoryPane {
	public static StackPane createInventoryPane(String username ,Stage stage,boolean admin,Scene loggedOut) {
		 String sqlQuery="SELECT * FROM products";
	 		TableView<ObservableList<String>> tableView = createTableView(sqlQuery);
	         
	         VBox vbox = new VBox();
	         
	         
	         
	         ChoiceBox<String> productOptions = new ChoiceBox<>();
	         productOptions.getItems().addAll("New Product", "Modify Product","Remove Product");
	         productOptions.setValue("Modify Product"); 

	         
	         
	         ChoiceBox<String> productChoiceBox = new ChoiceBox<>();
	         productChoiceBox.setValue("Choose Product");
	         populateCustomerNames(productChoiceBox);
	        
	        
	         Label productIDLB = new Label("Product ID:");
	         Label productNameLB = new Label("Product Name:");
	         Label quantityLB= new Label("Quantity:");
	         Label priceLB = new Label("Price:");
	         Label categoryLB= new Label("Category: ");
	         TextField productIDTXField= new TextField();
	         TextField productNameTXField= new TextField();
	         TextField quantityTXField= new TextField();
	         TextField priceTXField= new TextField();
	         TextField categoryTXField= new TextField();
	         
	         
	         Button addBtn = new Button("Add");
	         Button modifyBtn = new Button("Modify");
	         Button removeBtn = new Button("Remove");
	         		
	         GridPane labelsGrid= new GridPane();
	         labelsGrid.setHgap(10);
	         labelsGrid.setVgap(10);
	         labelsGrid.add(productIDLB, 0, 0);
	         labelsGrid.add(productIDTXField, 1, 0);
	         labelsGrid.add(productNameLB, 0, 1);
	         labelsGrid.add(productNameTXField, 1, 1);
	         labelsGrid.add(priceLB, 0, 2);
	         labelsGrid.add(priceTXField, 1, 2);
	         labelsGrid.add(categoryLB, 0, 3);
	         labelsGrid.add(categoryTXField, 1, 3);
	         labelsGrid.add(quantityLB, 0, 4);
	         labelsGrid.add(quantityTXField, 1, 4);
	         labelsGrid.add(addBtn, 0, 5);
	         
	        
	         
	         addBtn.setOnAction(e->{
	         	if(!productIDTXField.getText().isEmpty() && !productNameTXField.getText().isEmpty() && !priceTXField.getText().isEmpty() &&!categoryTXField.getText().isEmpty() && !quantityTXField.getText().isEmpty())
	         		{
	         		 String sql = "INSERT INTO products (ProductID, Name,Price,Category,InventoryQuantity) VALUES (?, ?, ?, ?, ?)";
	         	        

	         		 Connection connection= LogIn.getConnection();
	         	        try (
	         	            
	         	            PreparedStatement statement = connection.prepareStatement(sql);
	         	        ) {
	         	        
	         	           
	         	            
	         	        	statement.setInt(1,Integer.parseInt(productIDTXField.getText()) );
	         	        	statement.setString(2, productNameTXField.getText());
	         	            statement.setDouble(3, Double.parseDouble(priceTXField.getText()));
	         	            statement.setString(4, categoryTXField.getText());
	         	            statement.setInt(5,Integer.parseInt(quantityTXField.getText()) );
	         	            

	         	            
	         	            statement.executeUpdate();
	         	        } catch (NumberFormatException e1) {
	 						// TODO Auto-generated catch block
	 						e1.printStackTrace();
	 					} catch (SQLException e1) {
	 						// TODO Auto-generated catch block
	 						e1.printStackTrace();
	 					};
	 					 ObservableList<String> row = FXCollections.observableArrayList();
	 					row.add(productIDTXField.getText());
	 					row.add(productNameTXField.getText());
	 					row.add(priceTXField.getText());
	 					row.add(categoryTXField.getText());
	 					row.add(quantityTXField.getText() );
	 					tableView.getItems().add(row);
	 					tableView.refresh();
	         		}
	         });
	         
	         modifyBtn.setOnAction(e->{
	         	if(!productIDTXField.getText().isEmpty() && !priceTXField.getText().isEmpty() &&!categoryTXField.getText().isEmpty() && !quantityTXField.getText().isEmpty())
	         		if(productChoiceBox.getValue()=="Choose Product")
	             		;
	             	else{String selectedProduct= productChoiceBox.getValue();	
	         	{
	         		 String sql = "UPDATE products SET ProductID = ?, Price=?, Category=?, InventoryQuantity=? WHERE Name= ?";
	         	        

	         		 Connection connection= LogIn.getConnection();
	         	        try (
	         	            
	         	            PreparedStatement statement = connection.prepareStatement(sql);
	         	        ) {
	         	        
	         	           
	         	            
	         	        	statement.setInt(1,Integer.parseInt(productIDTXField.getText()) );
	         	            statement.setDouble(2, Double.parseDouble(priceTXField.getText()));
	         	            statement.setString(3, categoryTXField.getText());
	         	            statement.setInt(4,Integer.parseInt(quantityTXField.getText()) );
	         	            
	         	            statement.setString(5, selectedProduct);
	         	            
	         	            statement.executeUpdate();
	         	        } catch (NumberFormatException e1) {
	 						// TODO Auto-generated catch block
	 						e1.printStackTrace();
	 					} catch (SQLException e1) {
	 						// TODO Auto-generated catch block
	 						e1.printStackTrace();
	 					};
	 					 
	 					tableView.refresh();
	         		}}
	         });
	         
	         removeBtn.setOnAction(e->{
	         	
	         		if(productChoiceBox.getValue()=="Choose Product")
	             		;
	             	else{String selectedProduct= productChoiceBox.getValue();	
	         	
	         		 String sql = "DELETE FROM products WHERE Name = ?";
	         	       

	         		 Connection connection= LogIn.getConnection();
	         	        try (
	         	            
	         	            PreparedStatement statement = connection.prepareStatement(sql);
	         	        ) {
	         	        
	         	            
	         	            
	         	        	
	         	            statement.setString(1, selectedProduct);
	         	            
	         	            statement.executeUpdate();
	         	        } catch (NumberFormatException e1) {
	 						// TODO Auto-generated catch block
	 						e1.printStackTrace();
	 					} catch (SQLException e1) {
	 						// TODO Auto-generated catch block
	 						e1.printStackTrace();
	 					};
	         	}
	         });
	         
	         
	         productIDLB.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size:15px;");        
	         productNameLB.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size:15px;");
	         quantityLB.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size:15px;");
	         categoryLB.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size:15px;");
	         priceLB.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size:15px;");

	         
	         labelsGrid.getChildren().clear();
	         labelsGrid.add(productIDLB, 0, 0);
	         labelsGrid.add(productIDTXField, 1, 0);
	         labelsGrid.add(priceLB, 0, 1);
	         labelsGrid.add(priceTXField, 1, 1);
	         labelsGrid.add(categoryLB, 0, 2);
	         labelsGrid.add(categoryTXField, 1, 2);
	         labelsGrid.add(quantityLB, 0, 3);
	         labelsGrid.add(quantityTXField, 1, 3);
	         labelsGrid.add(modifyBtn, 0, 4);
	   	  vbox.getChildren().addAll(productOptions,productChoiceBox,labelsGrid);
	  
	         productOptions.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	             if ("Modify Product".equals(newValue)) {
	                 vbox.getChildren().clear();
	                 labelsGrid.getChildren().clear();
	                 labelsGrid.add(productIDLB, 0, 0);
	                 labelsGrid.add(productIDTXField, 1, 0);
	                 labelsGrid.add(priceLB, 0, 1);
	                 labelsGrid.add(priceTXField, 1, 1);
	                 labelsGrid.add(categoryLB, 0, 2);
	                 labelsGrid.add(categoryTXField, 1, 2);
	                 labelsGrid.add(quantityLB, 0, 3);
	                 labelsGrid.add(quantityTXField, 1, 3);
	                 labelsGrid.add(modifyBtn, 0, 4);
	           	  vbox.getChildren().addAll(productOptions,productChoiceBox,labelsGrid);
	             } else if ("New Product".equals(newValue)) {
	                 
	           	  vbox.getChildren().clear();
	           	  labelsGrid.getChildren().clear();
	           	labelsGrid.add(productIDLB, 0, 0);
	             labelsGrid.add(productIDTXField, 1, 0);
	             labelsGrid.add(productNameLB, 0, 1);
	             labelsGrid.add(productNameTXField, 1, 1);
	             labelsGrid.add(priceLB, 0, 2);
	             labelsGrid.add(priceTXField, 1, 2);
	             labelsGrid.add(categoryLB, 0, 3);
	             labelsGrid.add(categoryTXField, 1, 3);
	             labelsGrid.add(quantityLB, 0, 4);
	             labelsGrid.add(quantityTXField, 1, 4);
	             labelsGrid.add(addBtn, 0, 5);
	           vbox.getChildren().addAll(productOptions,labelsGrid);
	             }
	             
	             else if ("Remove Product".equals(newValue))
	             {
	             	 vbox.getChildren().clear();
	             	 
	                  
	            	  vbox.getChildren().addAll(productOptions,productChoiceBox,removeBtn);
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
		         	if(!productIDTXField.getText().isEmpty() && !productNameTXField.getText().isEmpty() && !priceTXField.getText().isEmpty() &&!categoryTXField.getText().isEmpty() && !quantityTXField.getText().isEmpty())
		         		{
		         		 String sql = "INSERT INTO products (ProductID, Name,Price,Category,InventoryQuantity) VALUES (?, ?, ?, ?, ?)";

		         		 Connection connection= LogIn.getConnection();
		         	        try (
		         	            PreparedStatement statement = connection.prepareStatement(sql);
		         	        ) {
		         	        
		         	            
		         	        	statement.setInt(1,Integer.parseInt(productIDTXField.getText()) );
		         	        	statement.setString(2, productNameTXField.getText());
		         	            statement.setDouble(3, Double.parseDouble(priceTXField.getText()));
		         	            statement.setString(4, categoryTXField.getText());
		         	            statement.setInt(5,Integer.parseInt(quantityTXField.getText()) );

		         	            statement.executeUpdate();
		         	        } catch (NumberFormatException e1) {
		 						// TODO Auto-generated catch block
		 						e1.printStackTrace();
		 					} catch (SQLException e1) {
		 						// TODO Auto-generated catch block
		 						e1.printStackTrace();
		 					};
		 					grid.getChildren().remove(0, 0);
		 					grid.add(createTableView(sqlQuery), 0, 0);
		 					
		         		} });
		         
		         modifyBtn.setOnAction(e->{
		         	if(!productIDTXField.getText().isEmpty() && !priceTXField.getText().isEmpty() &&!categoryTXField.getText().isEmpty() && !quantityTXField.getText().isEmpty())
		         		if(productChoiceBox.getValue()=="Choose Product")
		             		;
		             	else{String selectedProduct= productChoiceBox.getValue();	
		         	{
		         		 String sql = "UPDATE products SET ProductID = ?, Price=?, Category=?, InventoryQuantity=? WHERE Name= ?";

		         		 Connection connection= LogIn.getConnection();
		         	        try (
		         	            PreparedStatement statement = connection.prepareStatement(sql);
		         	        ) {
		         	        
		         	            
		         	        	statement.setInt(1,Integer.parseInt(productIDTXField.getText()) );
		         	            statement.setDouble(2, Double.parseDouble(priceTXField.getText()));
		         	            statement.setString(3, categoryTXField.getText());
		         	            statement.setInt(4,Integer.parseInt(quantityTXField.getText()) );
		         	            statement.setString(5, selectedProduct);
		         	            
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
		         	
		         		if(productChoiceBox.getValue()=="Choose Product")
		             		;
		             	else{String selectedProduct= productChoiceBox.getValue();	
		         	
		         		 String sql = "DELETE FROM products WHERE Name = ?";

		         		 Connection connection= LogIn.getConnection();
		         	        try (
		         	            PreparedStatement statement = connection.prepareStatement(sql);
		         	        ) {
		         	        
		         	            
		         	        	
		         	            statement.setString(1, selectedProduct);
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
	           String sql = "SELECT Name FROM products";
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
	                  return new SimpleStringProperty(row.get(columnIndex)); // Return a SimpleStringProperty
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
	      tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
	      
	      double totalWidth = tableView.getColumns().stream()
	              .mapToDouble(column -> column.getWidth())
	              .sum();

	      tableView.setMaxWidth(totalWidth );
	      tableView.setFixedCellSize(25);
	      tableView.setMaxHeight(275);
	     

	      tableView.refresh();;
	      tableView.autosize();
	      return tableView;}
}

