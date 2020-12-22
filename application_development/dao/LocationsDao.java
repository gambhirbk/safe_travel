package safeTravel.dao;

import safeTravel.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Xinyu Dai
 *
 * Nov 12, 2020 - 9:40:47 PM
 */
public class LocationsDao {
	protected ConnectionManager connectionManager;
	
	// Singleton pattern: instantiation is limited to one object.
	private static LocationsDao instance = null;
	protected LocationsDao() {
		connectionManager = new ConnectionManager();
	}
	public static LocationsDao getInstance() {
		if(instance == null) {
			instance = new LocationsDao();
		}
		return instance;
	}
	
	/**
	 * Save the Locations instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public Locations create(Locations location) throws SQLException {
		String insertLocation = "INSERT INTO Location(Latitude, Longitude) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertLocation);
			
			insertStmt.setString(1, location.getLatitude());
			insertStmt.setString(2, location.getLongitude());
			insertStmt.executeUpdate();
			
			return location;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
	}
	
	/**
	 * Get the matching Locations records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching Locations.
	 */
	
	
	public Locations getLocationByLatitude(String latitude) throws SQLException {
		String selectLocation =
			"SELECT Latitude,Longitude " +
			"FROM Location " +
			"WHERE Latitude=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLocation);
			selectStmt.setString(1, latitude);
			results = selectStmt.executeQuery();
			
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultLatitude = results.getString("Latitude");
				String longitude = results.getString("Longitude");
				Locations location = new Locations(resultLatitude, longitude);

				return location;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}
	
	
	/**
	 * Delete the Locations instance.
	 * This runs a DELETE statement.
	 */
	public Locations delete(Locations location) throws SQLException {
		String deleteLocation = "DELETE FROM Location WHERE Latitude=? AND Longitude=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLocation);
			deleteStmt.setString(1,location.getLatitude());
			deleteStmt.setString(2, location.getLongitude());
			deleteStmt.executeUpdate();
			
			// Return null so the caller can no longer operate on the Users instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
