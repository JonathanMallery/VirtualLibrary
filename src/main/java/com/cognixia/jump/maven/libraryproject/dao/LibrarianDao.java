package com.cognixia.jump.maven.libraryproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.maven.libraryproject.connection.ConnectionManager;
import com.cognixia.jump.maven.libraryproject.model.Librarian;

public class LibrarianDao {
	
	private Connection conn = ConnectionManager.getConnection();
	private final String TABLE = "librarian";
	private final String INSERT_QUERY = "insert into " + TABLE + " (librarian_id, username, password) values (null, ?, ?)";
	private final String GET_ALL_QUERY = "select * from " + TABLE;
	private final String GET_BY_ID_QUERY = "select * from " + TABLE + " where librarian_id = ?";
	private final String GET_BY_USERNAME_QUERY = "select * from " + TABLE + " where username = ?";
	
	public boolean addLibrarian(Librarian lib) {
		
		try (PreparedStatement stmt = conn.prepareStatement(INSERT_QUERY)) {
			
			stmt.setString(1, lib.getUsername());
			stmt.setString(2, lib.getPassword());

			int rowCount = stmt.executeUpdate();
			if (rowCount > 0) {
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public List<Librarian> getAll() {
		List<Librarian> libList = new ArrayList<>();
		
		try (PreparedStatement stmt = conn.prepareStatement(GET_ALL_QUERY)) {
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int librarianId = rs.getInt("librarian_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				Librarian lib = new Librarian(librarianId, username, password);
				libList.add(lib);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return libList;
	}
	
	public Librarian getById(int lId) {
		
		Librarian lib = null;
		
		try (PreparedStatement stmt = conn.prepareStatement(GET_BY_ID_QUERY)) {
			
			stmt.setInt(1, lId);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int librarianId = rs.getInt("librarian_id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			
			lib = new Librarian(librarianId, username, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lib;
	}
	
	public Librarian getByUsername(String usr) {
		Librarian lib = null;
		
		try (PreparedStatement stmt = conn.prepareStatement(GET_BY_USERNAME_QUERY)) {
			
			stmt.setString(1, usr);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int librarianId = rs.getInt("librarian_id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			
			lib = new Librarian(librarianId, username, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lib;
	}
	
}
