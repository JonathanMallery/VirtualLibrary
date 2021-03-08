package com.cognixia.jump.maven.libraryproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.cognixia.jump.maven.libraryproject.connection.ConnectionManager;
import com.cognixia.jump.maven.libraryproject.model.Patron;

public class PatronDao {
	
	public static final Connection conn = ConnectionManager.getConnection();
	private static final String SELECT_ALL_PATRONS ="SELECT * FROM patron";
	private static final String INSERT_INTO_PATRON="INSERT INTO patron(patron_id,first_name,last_name,username,password, account_frozen) values(null,?,?,?,?,?)";
	private static final String SELECT_PATRON_BY_ID="SELECT * FROM patron WHERE patron_id = ?";
	private static final String SELECT_PATRON_BY_USERNAME="SELECT * FROM Patron WHERE username = ?";
	
	//SELECT * Query
	public List<Patron> getAllPatrons(){
		List<Patron> allPatrons = new ArrayList<Patron>();
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_PATRONS);
				ResultSet rs = pstmt.executeQuery() )	{
			
			while(rs.next()) {
				int patronId = rs.getInt("patron_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String userName = rs.getString("username");
				String password = rs.getString("password");
				boolean account_frozen = rs.getBoolean("account_frozen");
				allPatrons.add(new Patron(patronId,firstName,lastName,userName,password,account_frozen));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allPatrons;
	}
	public boolean addPatron(Patron patron) {
		try(PreparedStatement pstmt = conn.prepareStatement(INSERT_INTO_PATRON);) {
			pstmt.setString(1, patron.getFirstName());
			pstmt.setString(2, patron.getLastName());
			pstmt.setString(3,  patron.getUserName());
			pstmt.setString(4, patron.getPassword());
			pstmt.setBoolean(5, patron.isAccountFrozen());
			if(pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public Patron getPatronById(int patronId) {
		Patron patron = null;
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_PATRON_BY_ID)) {
			pstmt.setInt(1, patronId);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int pId = rs.getInt("patron_id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString( "last_name");
			String userName = rs.getString("username");
			String passWord = rs.getString("password");
			boolean accountFrozen = rs.getBoolean("account_frozen");
			patron = new Patron(pId, firstName,lastName, userName, passWord, accountFrozen);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Couldn't find patron");
		}
		return patron;
	}
	public Patron getPatronByUsername(String username) {
		Patron patron = null;
		try(PreparedStatement pstmt = conn.prepareStatement(SELECT_PATRON_BY_USERNAME)) {
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			int patronId = rs.getInt("patron_id");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString( "last_name");
			String userName = rs.getString("username");
			String passWord = rs.getString("password");
			boolean accountFrozen = rs.getBoolean("account_frozen");
			
			patron = new Patron(patronId,firstName,lastName, userName, passWord, accountFrozen);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Couldn't find patron");
		}

		return patron;
	}
}
