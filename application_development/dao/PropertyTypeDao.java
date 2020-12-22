package safeTravel.dao;

import safeTravel.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Data access object (DAO) class to interact with the underlying Users table in your MySQL
 * instance. This is used to store {@link Users} into your MySQL instance and retrieve 
 * {@link Users} from MySQL instance.
 */
public class PropertyTypeDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static PropertyTypeDao instance = null;
	protected PropertyTypeDao() {
		connectionManager = new ConnectionManager();
	}
	public static PropertyTypeDao getInstance() {
		if(instance == null) {
			instance = new PropertyTypeDao();
		}
		return instance;
	}

	/**
	 * Save the PropertyType instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public PropertyType create(PropertyType propertyType) throws SQLException {
		String insertPropertyType = "INSERT INTO PropertyType(PropertyId, Type) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPropertyType);
			insertStmt.setInt(1, propertyType.getPropertyId());
			insertStmt.setString(2, propertyType.getType());
			insertStmt.executeUpdate();
	
			return propertyType ;
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
	 * Get the Users record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Users instance.
	 */
	
	
	public PropertyType getPropertyTypeById(int propertyId) throws SQLException {
		String selectUser = "SELECT PropertyId, Type FROM PropertyType WHERE PropertyId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setInt(1, propertyId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultPropertyId = results.getInt("PropertyId");
				String type = results.getString("Type");
				PropertyType newProperty = new PropertyType(resultPropertyId, type);
				return newProperty;
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
	 * Delete the Users instance.
	 * This runs a DELETE statement.
	 */
	
	public PropertyType delete(PropertyType propertyType) throws SQLException {
		String deleteUser = "DELETE FROM PropertyType WHERE PropertyId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, propertyType.getPropertyId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
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
