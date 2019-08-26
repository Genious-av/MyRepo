package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RentACarApp {
 public static void main(String[] args) throws SQLException {
	Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/rentacar?useSSL=false&serverTimezone=UTC",
						"root",
						"12345");
	Statement statement=connection.createStatement();
	
	String sql="SELECT*FROM car";
	ResultSet resultset=statement.executeQuery(sql);
	while(resultset.next()) {
		System.out.println(resultset.getString(1)+';'+resultset.getString(2));
	}		
}
}
