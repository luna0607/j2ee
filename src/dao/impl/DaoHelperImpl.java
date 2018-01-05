package dao.impl;

import dao.DaoHelper;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;


public class DaoHelperImpl implements DaoHelper
{
	private static DaoHelperImpl baseDao=new DaoHelperImpl();
	private Connection connection = null;

	private DaoHelperImpl()
	{
		
	}
	
	public static DaoHelperImpl getBaseDaoInstance()
	{
		return baseDao;
	}
	
	public Connection getConnection()
	{

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://54.64.213.108:6666/j2ee?user=j2ee&password=j2ee123");
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return connection;
	}
	
	/*
	 * Connection
	 */
	public void closeConnection(Connection con)
	{
		if(con!=null)
		{
			try 
			{
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * PreparedStatement
	 */
	public void closePreparedStatement(PreparedStatement stmt)
	{
		if(stmt!=null)
		{
			try
			{
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*
	 * ResultSet
	 */	
	public void closeResult(ResultSet result)
	{
		if(result!=null)
		{
			try
			{
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
