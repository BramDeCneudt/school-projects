package db;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import domain.ShopTest;

public class DerbyDb implements Db {
	
	public static String createTableItemScript = 
			"CREATE TABLE items "
			+ "( itemid INT NOT NULL primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
			+ " itemtitle VARCHAR(100) NOT NULL, "
			+ " itemtype VARCHAR(1) NOT NULL, "
			+ " creationdate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ) " ;

	
	public static String driver = "org.apache.derby.jdbc.ClientDriver";
	public static String connectionURL = "jdbc:derby:JavaPracticumHerkansingDB;create=true";
	public static Connection connexion = null;

	
	public DerbyDb() {
		
		loadDb();
		createTables();
		
		
	}
	

public static void loadDriver() {
	try {
		Class.forName(driver);
	} 
	catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
}

	
	private void loadDb() {
		
		try {
			loadDriver();
			connexion = DriverManager.getConnection(connectionURL);
			System.out.println("successfully connected to derbyDB");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		
	}
	
	public static void createTables() {
		try {
			Statement sItemTable = connexion.createStatement();
			System.out.println(" . . . . creating table ItemTable");
			sItemTable.execute(createTableItemScript);
		}
		 catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public ShopTest haalOp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void slaOp(ShopTest shop) {
		// TODO Auto-generated method stub
		
	}

}
