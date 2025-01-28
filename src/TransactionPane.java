
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class TransactionPane {

  
	
         
    @SuppressWarnings("unchecked")
	public static StackPane createTransactionPane(String username ,Stage stage,boolean admin,Scene loggedOut) {
    	
    	 
    	TableView<Object[]> tableView = new TableView<>();

        // Create columns
        TableColumn<Object[], String> productColumn = new TableColumn<>("Product");
        productColumn.setCellValueFactory(cellData -> cellData.getValue()[0] == null ? null : new SimpleStringProperty(cellData.getValue()[0].toString()));
        productColumn.setMinWidth(150);

        TableColumn<Object[], Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue()[1] == null ? null : new SimpleObjectProperty<>(Integer.parseInt(cellData.getValue()[1].toString())));
        quantityColumn.setMinWidth(100);

        TableColumn<Object[], Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue()[2] == null ? null : new SimpleObjectProperty<>(Double.parseDouble(cellData.getValue()[2].toString())));
        priceColumn.setMinWidth(100);

        tableView.getColumns().addAll(productColumn, quantityColumn, priceColumn);
          
          VBox vbox = new VBox();
          Label newNameLB = new Label("Costumer Name:");
          Label quantityLB = new Label("Quantity:");
          Label contactLB= new Label("Contact Details:");
          Label totalLB= new Label("Total price: ");
          TextField contactTXField= new TextField();
          TextField quantityTXField= new TextField();
          TextField CustomerTXField= new TextField();
          newNameLB.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size:15px;");
          quantityLB.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size:15px;");
          contactLB.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size:15px;");
          totalLB.setStyle("-fx-text-fill: #ffffff; -fx-font-weight: bold; -fx-font-size:25px;");
          GridPane newCustomerDetails = new GridPane();
          
          Button addBtn = new Button("Add");
          
          HBox productHBox = new HBox();
          productHBox.setSpacing(10);
          productHBox.getChildren().addAll(quantityLB,quantityTXField);
          
          newCustomerDetails.add(newNameLB, 0, 0);
          newCustomerDetails.add(CustomerTXField, 1, 0);
          newCustomerDetails.add(contactLB,0, 1);
          newCustomerDetails.add(contactTXField,1, 1);
          newCustomerDetails.setHgap(10);
          newCustomerDetails.setVgap(10);
          
         
          
          ChoiceBox<String> customerChoiceBox = new ChoiceBox<>();
          customerChoiceBox.setValue("Select Customer");
          populateCustomerNames(customerChoiceBox, "customers");
         
         
          ChoiceBox<String> productChoiceBox = new ChoiceBox<>();
          productChoiceBox.setValue("Select Product");
          populateCustomerNames(productChoiceBox, "products");
          
          
          Button transactionBtn = new Button("Complete Transaction");
          
         
          
          vbox.getChildren().addAll(customerChoiceBox,productChoiceBox,productHBox,addBtn,transactionBtn);
          
          vbox.setSpacing(10);
        
          
          addBtn.setOnAction(e->{
        	if(productChoiceBox.getValue()=="Select Product") 
        		;
        	else{String selectedProduct= productChoiceBox.getValue();
        	int quantity=0;
        	double price=0.0;
        	try (Connection conn = LogIn.getConnection()) {
        		 String sql = "SELECT Price FROM products WHERE Name = ?";
        		    try (PreparedStatement statement = conn.prepareStatement(sql)) {
        		        statement.setString(1, selectedProduct); 
        		        try (ResultSet resultSet = statement.executeQuery()) {
        		            while (resultSet.next()) {
        		               price = resultSet.getDouble("Price");
        		               
        		              
        		            }
        		        }
        		    }
        		} catch (SQLException ex) {
        		    ex.printStackTrace();
        		}
        	try {
        	     quantity = Integer.parseInt(quantityTXField.getText());
        	  
        	} catch (NumberFormatException ex) {
        	    Alert alert = new Alert(AlertType.INFORMATION);
        	    alert.setHeaderText("Invalid Input");
        	    
        	    alert.showAndWait();
        	    quantityTXField.clear();
        	}
        
        	
			if (quantity>0)
        	{
        	if(quantity>getInventoryQuantity(selectedProduct))
        	{
        		 Alert alert = new Alert(AlertType.INFORMATION);
        	        alert.setTitle("Information Dialog");
        	        alert.setHeaderText("This is a header text");
        	        alert.setContentText("Not enough quantity in inventory.");

        	        
        	        alert.showAndWait();
        	}
        	
        	else {
        	tableView.getItems().add(new Object[]{selectedProduct, quantity, price});
        	double totalSum = tableView.getItems().stream()
        	        .mapToDouble(row -> {
        	            Integer productQuantity = (Integer) row[1];
        	            Double totalPrice = (Double) row[2];
        	            if (productQuantity != null && totalPrice != null) {
        	                return productQuantity * totalPrice;
        	            } else {
        	                return 0.0;
        	            }
        	        })
        	        .sum();
        	totalLB.setText("Total Price: " + totalSum + "L");
        	}
            
        	}}
        	
          });
          
          transactionBtn.setOnAction(e->{
        	 
        	  String customerName;
        	  Connection conn = LogIn.getConnection();
        	  int maxID= -1;
        	  try {
				maxID = getMaxTransactionId(conn)+1;
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	  if(customerChoiceBox.getValue()=="Select Customer" || (quantityTXField.getText().isBlank())|| (tableView.getItems().isEmpty()))
        	  {
        		  Alert alert = new Alert(AlertType.INFORMATION);
          	    alert.setHeaderText("COMPLETE ALL FIELDS CORRECTLY!");
          	    alert.setContentText("Don't be an idiot!");
          	    
          	    alert.showAndWait();
        	  }
        	  if(customerChoiceBox.getValue()!="Select Customer" && !(quantityTXField.getText().isBlank()) && !(tableView.getItems().isEmpty()) )
        		  {
        		  customerName=customerChoiceBox.getValue();
        		  double totalSum = tableView.getItems().stream()
          	        .mapToDouble(row -> {
          	            Integer productQuantity = (Integer) row[1];
          	            Double totalPrice = (Double) row[2];
          	            if (productQuantity != null && totalPrice != null) {
          	                return productQuantity * totalPrice;
          	            } else {
          	                return 0.0;
          	            }
          	        })
          	        .sum();
          	
        		completeTransaction(customerName,totalSum,tableView);
          		insertTransactionDetails(maxID,tableView);
          		
        	  ObservableList<?> items = tableView.getItems();
              items.clear();
              contactTXField.setText("");
              quantityTXField.setText("");
              CustomerTXField.setText("");
              totalLB.setText("Total price: ");
        	 }
          });
          
           Button backBtn= new Button("Back");
         backBtn.setOnAction(e->AdminScene.createScene(username, stage, admin,loggedOut));
 
          GridPane grid = new GridPane();
          grid.add(tableView, 0, 0);
          grid.add(vbox, 1, 0);
          grid.add(totalLB, 0, 1);
          grid.setHgap(10);
          grid.setPadding(new Insets(20));
          grid.add(backBtn, 2, 2);
          
          grid.setAlignment(Pos.CENTER);
          
          grid.setMaxHeight(275);

         
          
          StackPane pane = new StackPane();
          StackPane.setAlignment(vbox, Pos.BASELINE_CENTER);
          pane.getChildren().add(grid);
          
          pane.setAlignment(Pos.CENTER);
          return pane;}
          
    
    public static void completeTransaction(String customerName, double totalPrice, TableView<Object[]> tableView) {
        try (Connection connection = LogIn.getConnection()) {
            int maxTransactionId = getMaxTransactionId(connection) + 1;
            int customerID = getCustomerID(connection, customerName);

            
            insertTransaction(connection, maxTransactionId, customerID, totalPrice);

            
            for (Object[] row : tableView.getItems()) {
                String productName = row[0].toString();
                int quantitySold = Integer.parseInt(row[1].toString());
                updateInventoryQuantity(connection, productName, quantitySold);
            }
            
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void updateInventoryQuantity(Connection connection, String productName, int quantitySold) throws SQLException {
        int currentQuantity = getInventoryQuantity( productName);
        int newQuantity = currentQuantity - quantitySold;

        String updateSql = "UPDATE products SET InventoryQuantity = ? WHERE Name = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
            statement.setInt(1, newQuantity);
            statement.setString(2, productName);
            statement.executeUpdate();}}
    
    
    private static int getMaxTransactionId(Connection connection) throws SQLException {
        
        String sql = "SELECT MAX(TransactionID) AS MaxTransactionID FROM transactions";

        try (
           
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
        ) {
           
            if (resultSet.next()) {
                return resultSet.getInt("MaxTransactionID");
            }
        }

        
        return 0;
    }
    
    private static int getCustomerID(Connection conn,String customerName) {
    	int customerId = -1; 

        String sql = "SELECT CustomerID FROM customers WHERE Name = ?";
        
        try (
            PreparedStatement statement = conn.prepareStatement(sql)) {
            
            statement.setString(1, customerName); 
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    customerId = resultSet.getInt("CustomerID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }

        return customerId;
    }
    
    private static void insertTransaction(Connection connection, int transactionId,int customerID,double totalSum) throws SQLException {
       
        String sql = "INSERT INTO transactions (TransactionID, CustomerID,TransactionTime,TotalPrice) VALUES (?, ?, ?,?)";
        

        try (
           
            PreparedStatement statement = connection.prepareStatement(sql);
        ) {
        	LocalDateTime now = LocalDateTime.now();
            
            statement.setInt(1, transactionId);
            statement.setInt(2, customerID);
            statement.setObject(3, now);
            statement.setDouble(4, totalSum);
           

           
            statement.executeUpdate();
        }
        
       
    }
    private static void insertTransactionDetails(int maxTransactionID, TableView<Object[]> tableView) {
    	String insertSql = "INSERT INTO  transactiondetails (TransactionID, ItemName, Quantity,Price) VALUES (?, ?, ?,?)";
      
        
        try (Connection connection = LogIn.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertSql)) {

            for (Object[] row : tableView.getItems()) {
                
                String data1 = row[0].toString(); 
                int data2 = Integer.parseInt(row[1].toString());  
                double data3 = Double.parseDouble(row[2].toString());  

                
                statement.setInt(1, maxTransactionID);
                statement.setString(2, data1);
                statement.setInt(3, data2);
                statement.setDouble(4, data3);

               
                statement.executeUpdate();
                
            }
            
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    public static int getInventoryQuantity(String productName) {
    	int inventoryQuantity = -1;
    	String sql = "SELECT InventoryQuantity FROM products WHERE Name = ?";
    	try (Connection connection = LogIn.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

               statement.setString(1, productName); 

               try (ResultSet resultSet = statement.executeQuery()) {
                   if (resultSet.next()) {
                       inventoryQuantity = resultSet.getInt("InventoryQuantity");
                   }
               }
           } catch (SQLException e) {
               e.printStackTrace();
               
           }

           return inventoryQuantity;
       }
    
          
          private static void populateCustomerNames(ChoiceBox<String> choiceBox,String tableName) {
              ArrayList<String> customerNames = fetchCustomerNamesFromDatabase(tableName);
              choiceBox.setItems(FXCollections.observableArrayList(customerNames));
          }

          private static ArrayList<String> fetchCustomerNamesFromDatabase(String tableName) {
              ArrayList<String> customerNames = new ArrayList<>();
              try (Connection conn = LogIn.getConnection()) {
                  String sql = "SELECT Name FROM " + tableName;
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
         
         
          }
    
 
