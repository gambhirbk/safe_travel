package safeTravel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import safeTravel.model.WithInDistances;


/**
 * @author Xinyu Dai
 *
 * Nov 12, 2020 - 9:00:02 PM
 */
public class WithInDistancesDao {
	protected ConnectionManager connectionManager;
	
	// Singleton pattern: instantiation is limited to one object.
	private static WithInDistancesDao instance = null;
	protected WithInDistancesDao() {
		connectionManager = new ConnectionManager();
	}
	public static WithInDistancesDao getInstance() {
		if(instance == null) {
			instance = new WithInDistancesDao();
		}
		return instance;
	}
	
	/**
	 * Save the WithInDistances instance by storing it in your MySQL instance.
	 * This runs a INSERT statement.
	 */

	
	public WithInDistances create(WithInDistances withInDistance) throws SQLException {
		String insertWithInDistance = "INSERT INTO WithInDistance(ListingId, OffenseId) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertWithInDistance,
					Statement.RETURN_GENERATED_KEYS);

			insertStmt.setInt(1, withInDistance.getListingId());
			insertStmt.setString(2, withInDistance.getOffenseId());

			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int recordId = -1;
			if(resultKey.next()) {
				recordId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			withInDistance.setRecordId(recordId);
			return withInDistance;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}

	/**
	 * Get the WithInDistances record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single WithInDistances instance.
	 */
	public WithInDistances getWithInDistanceById(int recordId) throws SQLException {
		String selectWithInDistance = "SELECT RecordId, ListingId, OffenseId FROM WithInDistance WHERE RecordId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWithInDistance);
			selectStmt.setInt(1, recordId);
			
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultRecordId = results.getInt("RecordId");
				int listingId = results.getInt("ListingId");
				String offenseId = results.getString("OffenseId");
				WithInDistances withInDistance = new WithInDistances(resultRecordId, listingId, offenseId);
				return withInDistance;
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
	 * Get the matching WithInDistances records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching WithInDistances.
	 */
	public List<WithInDistances> getWithInDistanceByListingId(int listingId) throws SQLException {
		List<WithInDistances> withInDistances = new ArrayList<WithInDistances>();
		String selectWithInDistances =
			"SELECT RecordId,ListingId,OffenseId " +
			"FROM WithInDistance " +
			"WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWithInDistances);
			selectStmt.setInt(1, listingId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int recordId = results.getInt("RecordId");
				int resultListingId = results.getInt("ListingId");
				String offenseId = results.getString("OffenseId");
				WithInDistances withInDistance = new WithInDistances(recordId, resultListingId, offenseId);
				withInDistances.add(withInDistance);
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
		return withInDistances;
	}
	
	/**
	 * Get the matching WithInDistances records by fetching from your MySQL instance.
	 * This runs a SELECT statement and returns a list of matching WithInDistances.
	 */
	public List<WithInDistances> getWithInDistanceByOffenseId(String offenseId) throws SQLException {
		List<WithInDistances> withInDistances = new ArrayList<WithInDistances>();
		String selectWithInDistances =
			"SELECT RecordId,ListingId,OffenseId " +
			"FROM WithInDistance " +
			"WHERE OffenseId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectWithInDistances);
			selectStmt.setString(1, offenseId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int recordId = results.getInt("RecordId");
				int listingId = results.getInt("ListingId");
				String resultOffenseId = results.getString("OffenseId");
				WithInDistances withInDistance = new WithInDistances(recordId, listingId, resultOffenseId);
				withInDistances.add(withInDistance);
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
		return withInDistances;
	}
	
	/**
	 * Delete the WithInDistances instance.
	 * This runs a DELETE statement.
	 */
	public WithInDistances delete(WithInDistances withInDistance) throws SQLException {
		String deleteWithInDistance = "DELETE FROM WithInDistance WHERE RecordId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteWithInDistance);
			deleteStmt.setInt(1, withInDistance.getRecordId());
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
