package safeTravel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import safeTravel.model.OffenseTypes;


public class OffenseTypesDao {
	protected ConnectionManager connectionManager;

	private static OffenseTypesDao instance = null;
	protected OffenseTypesDao() {
		connectionManager = new ConnectionManager();
	}
	public static OffenseTypesDao getInstance() {
		if(instance == null) {
			instance = new OffenseTypesDao();
		}
		return instance;
	}

	/**
	 * Save the OffenseType instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public OffenseTypes create(OffenseTypes OffenseType) throws SQLException {
		String insertOffenseType = "INSERT INTO OffenseType(OffenseCode, Offense, OffenseParentGroup) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertOffenseType);
			insertStmt.setString(1, OffenseType.getOffenseCode());
			insertStmt.setString(2, OffenseType.getOffense());
			insertStmt.setString(3, OffenseType.getOffenseParentGroup());
			insertStmt.executeUpdate();
			return OffenseType;
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
	 * Return all the offense types.
	 * This runs a SELECT statement.
	 */
	public List<OffenseTypes> getAllOffenseTypes() throws SQLException {
		String selectOffenseType = "SELECT * FROM OffenseType;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		List<OffenseTypes> offenseTypes = new ArrayList<>();
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectOffenseType);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String offenseCode = results.getString("OffenseCode");
				String offense = results.getString("Offense");
				String offenseParentGroup = results.getString("OffenseParentGroup");
				OffenseTypes og = new OffenseTypes(offenseCode, offense, offenseParentGroup);
				offenseTypes.add(og);
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
		}
		return offenseTypes;
	}
	
	/**
	 * Return OffenseType by Offense Code.
	 * This runs a SELECT statement.
	 */
	public OffenseTypes getOffenseTypesByOffenseCode(String offenseCode) throws SQLException {
		String selectOffenseType = "SELECT Offense, OffenseParentGroup FROM OffenseType WHERE OffenseCode=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectOffenseType);
			selectStmt.setString(1, offenseCode);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String offense = results.getString("Offense");
				String offenseParentGroup = results.getString("OffenseParentGroup");
				OffenseTypes og = new OffenseTypes(offenseCode, offense, offenseParentGroup);
				return og;
			}
			return null;
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
		}
	}

	/**
	 * Delete the OffenseType instance.
	 * This runs a DELETE statement.
	 */
	public OffenseTypes delete(OffenseTypes OffenseType) throws SQLException {
		String deleteOffenseType = "DELETE FROM OffenseType WHERE OffenseCode=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteOffenseType);
			deleteStmt.setString(1, OffenseType.getOffenseCode());
			deleteStmt.executeUpdate();
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
