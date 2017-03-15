package farma_la_carmela.Model;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public  class DataConection{

    private static Connection con;
    private static DataConection INSTANCE = null;
    static String connectionUrl = "jdbc:mysql://localhost:3306/farmacia";
    private String user;
    private static String pass;
    
    public DataConection(){
    }
	

	public static boolean performConnection(String user,String pass) {
		
		  try{
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	            con = DriverManager.getConnection(connectionUrl, user,pass);
	            JOptionPane.showMessageDialog(null, "Conexion Establecida");
                    return true;
	            	        
	        }catch(Exception e){
	            e.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error in Conexion");
                    return false;
	        }
		
	}
	

	private synchronized static void createInstance(String user,String pass) {
	        if (INSTANCE == null) { 
	            INSTANCE = new DataConection();
	            performConnection(user,pass);
	        }
	    }
	 

	public static DataConection getInstance(String user,String pass) {
	        if (INSTANCE == null) createInstance(user,pass);
	        return INSTANCE;
	    }
	

	public void closeConnection() {
		try {
			con.close();
		}catch (Exception e) {
			System.out.println("Error al terminar conexion");
		}
	}
	
	public static Connection  getCon(){
		return con;
	}
	

    
    public static void ejecutarprocedure(String sql) {
        try {

            Statement sentencia = con.prepareCall(sql);
            sentencia.execute(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();

        }


    }
    
    public static ResultSet ejecutarProcedureSelect(String sql) {
        ResultSet resultado;
        try {
            Statement sentencia = con.createStatement();
            sentencia.execute(sql);
            resultado = sentencia.getResultSet();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return resultado;
    }
	

}
