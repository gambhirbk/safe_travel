package safeTravel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import safeTravel.model.OffenseGroups;


public class OffenseGroupsDao {
	protected ConnectionManager connectionManager;

	private static OffenseGroupsDao instance = null;
	protected OffenseGroupsDao() {
		connectionManager = new ConnectionManager();
	}
	public static OffenseGroupsDao getInstance() {
		if(instance == null) {
			instance = new OffenseGroupsDao();
		}
		return instance;
	}

	/**
	 * Save the OffenseGroup instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */
	public OffenseGroups create(OffenseGroups offenseGroup) throws SQLException {
		String insertoffenseGroup = "INSERT INTO OffenseGroup(OffenseParentGroup,CrimeAgainstCategory) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertoffenseGroup);
			insertStmt.setString(1, offenseGroup.getOffenseParentGroup());
			insertStmt.setString(2, offenseGroup.getCrimeAgainstCategory().toString());
			insertStmt.executeUpdate();
			return offenseGroup;
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
	 * Return all the offense groups.
	 * This runs a SELECT statement.
	 */
	public List<OffenseGroups> getAllOffenseGroups() throws SQLException {
		String selectOffenseGroup = "SELECT * FROM OffenseGroup;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		List<OffenseGroups> offenseGroups = new ArrayList<>();
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectOffenseGroup);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String offenseGroup = results.getString("OffenseParentGroup");
				String category = results.getString("CrimeAgainstCategory");
				OffenseGroups og = new OffenseGroups(offenseGroup, OffenseGroups.CrimeAgainstCategory.valueOf(category));
				offenseGroups.add(og);
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
		return offenseGroups;
	}

	/**
	 * Update the CrimeAgainstCategory of the OffenseGroup instance.
	 * This runs a UPDATE statement.
	 */
	public OffenseGroups updateCrimeAgainstCategory(OffenseGroups offenseGroup, OffenseGroups.CrimeAgainstCategory newCrimeAgainstCategory) throws SQLException {
		String updateoffenseGroup = "UPDATE OffenseGroup SET CrimeAgainstCategory=? WHERE  OffenseParentGroup=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateoffenseGroup);
			updateStmt.setString(1, newCrimeAgainstCategory.toString());
			updateStmt.setString(2, offenseGroup.getOffenseParentGroup());
			updateStmt.executeUpdate();
			offenseGroup.setCrimeAgainstCategory(newCrimeAgainstCategory);
			return offenseGroup;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	/**
	 * Delete the OffenseGroup instance.
	 * This runs a DELETE statement.
	 */
	public OffenseGroups delete(OffenseGroups offenseGroup) throws SQLException {
		String deleteoffenseGroup = "DELETE FROM OffenseGroup WHERE OffenseParentGroup=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteoffenseGroup);
			deleteStmt.setString(1, offenseGroup.getOffenseParentGroup());
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
	
	/**
	 * Get the matching OffenseGroup records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching OffenseGroup.
	 */
	public List<OffenseGroups> getOffenseGroupByCrimeAgainstCategory(OffenseGroups.CrimeAgainstCategory category) throws SQLException {
		List<OffenseGroups> OffenseGroup = new ArrayList<OffenseGroups>();
		String selectOffenseGroup =
			"SELECT OffenseParentGroup FROM OffenseGroup WHERE CrimeAgainstCategory=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectOffenseGroup);
			selectStmt.setString(1, category.toString());
			results = selectStmt.executeQuery();
			while(results.next()) {
				String offenseParentGroup = results.getString("OffenseParentGroup");
				OffenseGroups offenseGroup = new OffenseGroups(offenseParentGroup, category);
				OffenseGroup.add(offenseGroup);
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
		return OffenseGroup;
	}
	
	/**
	 * Get the matching OffenseGroup records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching OffenseGroup.
	 */
	public OffenseGroups getOffenseGroupByOffenseParentGroup(String offenseParentGroup) throws SQLException {
		String selectOffenseGroup =
			"SELECT CrimeAgainstCategory FROM OffenseGroup WHERE OffenseParentGroup=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectOffenseGroup);
			selectStmt.setString(1, offenseParentGroup);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String crimeAgainstCategory = results.getString("CrimeAgainstCategory");
				return new OffenseGroups(offenseParentGroup, OffenseGroups.CrimeAgainstCategory.valueOf(crimeAgainstCategory));
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
			if(results != null) {
				results.close();
			}
		}
	}
}
