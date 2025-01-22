import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SalesPane {

	public static StackPane createSalesPane(String username ,Stage stage,boolean admin,Scene loggedOut) {
TableView<ObservableList<String>> tableView = new TableView<>();
TableView<ObservableList<String>> tableViewDetails = new TableView<>();
		

		try {
             Connection connection = LogIn.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM transactions");
           

             for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
                 int columnIndex = i;
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
		
		try {
		    Connection connection = LogIn.getConnection();
		    PreparedStatement statement = connection.prepareStatement("SELECT * FROM transactiondetails WHERE 1=0");
		    ResultSetMetaData metaData = statement.getMetaData();

		    for (int i = 1; i <= metaData.getColumnCount(); i++) {
		        int columnIndex = i;
		        TableColumn<ObservableList<String>, String> column = new TableColumn<>(metaData.getColumnName(i));
		        column.setCellValueFactory(cellData -> {
		            ObservableList<String> row = cellData.getValue();
		            return new SimpleStringProperty(row.get(columnIndex - 1)); // Return a SimpleStringProperty
		        });
		        
		    
		        
		        column.setReorderable(false);
                column.setResizable(false);
                column.setPrefWidth(100);
                column.setStyle("-fx-alignment: CENTER;");
		        tableViewDetails.getColumns().add(column);
		    }

		    statement.close();
		    connection.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		
		
		tableView.setOnMouseClicked(event -> {
		    if (event.getClickCount() == 1) {
		    	
		        tableViewDetails.getItems().clear();
		       
		    	ObservableList<String> clickedRow = tableView.getSelectionModel().getSelectedItem();
		        if (clickedRow != null) {
		            int columnIndex = 0; 
		            String cellValue = clickedRow.get(columnIndex);

		    		try {
		                 Connection connection = LogIn.getConnection();
		                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM transactiondetails WHERE TransactionID = ?");
		                 statement.setInt(1,Integer.parseInt(cellValue));
		                 ResultSet resultSet = statement.executeQuery();
		         
		               

		                 while (resultSet.next()) {
		                     ObservableList<String> row = FXCollections.observableArrayList();
		                     for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
		                         row.add(resultSet.getString(i));
		                     }
		                     tableViewDetails.getItems().add(row);
		                 }
		                 
		            

		                 connection.close();
		             } catch (SQLException e) {
		                 e.printStackTrace();
		             }
		            
		        }
		    }
		});
        
         tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
         
         double totalWidth = tableView.getColumns().stream()
                 .mapToDouble(column -> column.getWidth())
                 .sum();

       
         tableView.setMaxWidth(totalWidth );
         tableView.setFixedCellSize(25);
         tableView.setMaxHeight(275);
        

         
         tableView.autosize();
         
         
         VBox vbox = new VBox();
         vbox.getChildren().addAll(tableViewDetails);
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

        
         
         StackPane pane = new StackPane();
         StackPane.setAlignment(vbox, Pos.BASELINE_CENTER);
         pane.getChildren().add(grid);
      
         pane.setAlignment(Pos.CENTER);
         return pane;
    }
	}

