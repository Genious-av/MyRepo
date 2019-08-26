package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;

public class JDBCApp {

	public static void main(String[] args) throws SQLException {
		try(
		Connection connection =// make connection to DB
				// useSSL=false -  connection use secure connection between use
				//&serverTimezone=UTC - set time zone, used in MYSQL8, without this statement doesn`t work
				DriverManager.getConnection("jdbc:mysql://localhost/telran2019?useSSL=false&serverTimezone=UTC",
						"root",
						"12345");){
			//jdbc:mysql://localhost/ server adress
		
		Statement statement=connection.createStatement(); // Class as writer - the object used for executing a static SQL statementand returning the results it produces. 
		
		
		
		
		String sql="SELECT*FROM person";
		ResultSet resultset= statement.executeQuery(sql);
		while(resultset.next()) {
			System.out.println(new Person(resultset.getInt(1),resultset.getString(2),resultset.getInt(3),resultset.getDouble(4),resultset.getBoolean(5)));
		}
		
		Person alla= new Person(10, "Alla", 21, 155,false);
		
		//INSERT INTO person VALUES (10, 'lisa', 28, 173, 1);
		
		sql="INSERT INTO person VALUES("+
										//	lisa.getId()+","+
										+0 +","+
										"'"+alla.getName()+"'"+","+
										alla.getAge()+","+
										alla.getHeight()+","+
										(alla.isMarried() ? 1: 0)+
										")";
			System.out.println(statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS));
			
			ResultSet rs=statement.getGeneratedKeys();
			rs.next();
			System.out.println(rs.getInt(1));
			}
		
		
	}
}
