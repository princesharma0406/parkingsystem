import java.sql.*;

public class dao
{
	public static Connection connect()
	{
		Connection con=null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
                        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/parking_system","root","root");	
		}
		catch(Exception e)
		{
			System.out.println("Sorry! Connection Not Established\n\n" + e);
		}
		return con;
	}
}