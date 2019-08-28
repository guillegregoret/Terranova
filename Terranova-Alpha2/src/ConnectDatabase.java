import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConnectDatabase {
   public static void main(String[] args) {
      Connection con = null;
      
      try {
         //Registering the HSQLDB JDBC driver
         Class.forName("org.hsqldb.jdbc.JDBCDriver");
         //Creating the connection with HSQLDB
         
         Read dir = new Read();
         String[] conn=dir.RFD();
         String url = conn[0];
         String user = conn[1];
         String password = conn[2];
         
         con = DriverManager.getConnection(url,user,password);
         //System.out.println("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
         if (con!= null){
            System.out.println("Connection created successfully");
            
         }else{
            System.out.println("Problem with creating connection");
         }
      
      }  catch (Exception e) {
         e.printStackTrace(System.out);
      }
   }
   public void Query(String query) {
	      Connection con = null;
	      Statement stmt = null;
	      int result=0;
	      
	      try {
	         Class.forName("org.hsqldb.jdbc.JDBCDriver");
	         Read dir = new Read();
	         String[] conn=dir.RFD();
	       //  String url = conn[0];
	       //  String user = conn[1];
	       //  String password = conn[2];
	         
	         con = DriverManager.getConnection(conn[0],conn[1],conn[2]);
	         stmt = con.createStatement();
	         
	         result = stmt.executeUpdate(query);
				
	      }  catch (Exception e) {
	         e.printStackTrace(System.out);
	      }
	   
   }
   public Statement sqlstatment() {
	   Connection con = null;
	      Statement stmt = null;

	      
	      try {
	         Class.forName("org.hsqldb.jdbc.JDBCDriver");
	         Read dir = new Read();
	         String[] conn=dir.RFD();
	         
	         con = DriverManager.getConnection(conn[0],conn[1],conn[2]);
	         stmt = con.createStatement();
	         
				
	      }  catch (Exception e) {
	         e.printStackTrace(System.out);
	      }
	      return stmt;
	   
   }
}