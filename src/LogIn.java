import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class LogIn {

	public static boolean validateLogIn(String username, String password) {
		Connection con = null;
		con = getConnection();
           
            String sqlQuery = "SELECT * FROM employees WHERE BINARY Username =? AND BINARY Password =?";
            PreparedStatement pst;
		
            try {
			
            	pst = con.prepareStatement(sqlQuery);
			pst.setString(1, username);
			pst.setString(2, password);
        
        ResultSet rs=pst.executeQuery();
        boolean validation=rs.next();
        	
        	con.close();
        	return validation;}
		
            catch(SQLException e) {
            	
			e.printStackTrace();
		}
			return false;
		}
	


public static boolean validateAdmin(String username,String password) {
	Connection con = null;
	con = getConnection();
       
        String sqlQuery = "SELECT * FROM employees WHERE BINARY username =? AND BINARY password =? AND Role ='admin'";
        PreparedStatement pst;
	
        try {
		
        	pst = con.prepareStatement(sqlQuery);
		pst.setString(1, username);
		pst.setString(2, password);
    
    ResultSet rs=pst.executeQuery();
    boolean validation=rs.next();
    	
    	con.close();
    	return validation;}
	
        catch(SQLException e) {
		e.printStackTrace();
	}
		return false;
	}

public static Connection getConnection () {
	 String jdbcUrl = "jdbc:mysql://sql11.freesqldatabase.com:3306/sql11680672";
     String username = "sql11680672";
     String password = "28tI2MbNdF";
     Connection connection = null;
     try {
    	
       
         connection = DriverManager.getConnection(jdbcUrl, username, password);
     } catch (SQLException e) {
         e.printStackTrace();
       
    }
     
     return connection;
     
}
}
