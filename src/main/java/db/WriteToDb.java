package db;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 * @Note:链接数据库时需要"添加构建路径",添加jar包：为jdbc驱动----"mysql-connector-java-5.1.31-bin.jar"
 * 		注意数据库名字为testdb(写在url里),表名字为student(写在sql里的selete * from student里)
 *
 */
public class WriteToDb {
	public static void main(String[] args) {
		String driver = "com.mysql.cj.jdbc.Driver"; //驱动Driver
		String url = "jdbc:mysql://localhost:3306/ticketbrush?serverTimezone=UTC"; //url,数据库名字为testdb
		String user = "root"; //username------注意用户名一定是root!!! 用户名只能默认是root
		String password = "123456"; //pwd-----密码可以为mysql,反正自己设置的
		
		try {
			Class.forName(driver); //加载驱动
			Connection conn = DriverManager.getConnection(url, user, password); //建立连接
			if(!conn.isClosed()){
				System.out.println("Succeed connecting to the database!");
			}
			Statement statement = conn.createStatement(); //执行SQL的statement
			String sql = "select * from agency_ip"; //sql语句,表名称为student
			ResultSet result = statement.executeQuery(sql); //结果集result
			System.out.println("执行结果:");		
			while(result.next()){	
				System.out.print(result.getString("ip") + "\t");
			}
			result.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println("cannot find the driver!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException!");
		}	
		
	}
 
}