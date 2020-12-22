package safeTravel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import safeTravel.model.*;


public class PoliceSectorsDao {
	protected ConnectionManager connectionManager;
	
	private static PoliceSectorsDao instance = null;
	protected PoliceSectorsDao() {
		connectionManager = new ConnectionManager();
	}
	public static PoliceSectorsDao getInstance() {
		if(instance == null) {
			instance = new PoliceSectorsDao();
		}
		return instance;
	}
	
	public PoliceSectors create(PoliceSectors policeSector) throws SQLException {
		String insertPoliceSector = "INSERT INTO PoliceSector(Beat, Precinct) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPoliceSector);

			insertStmt.setString(1, policeSector.getBeat());
			insertStmt.setString(2, policeSector.getPrecinct().name());
			
			insertStmt.executeUpdate();
			
			return policeSector;
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
	
	public PoliceSectors getPoliceSectorsByBeat(String beat) throws SQLException {
		String selectPoliceSector = "SELECT Beat, Precinct FROM PoliceSector WHERE Beat=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPoliceSector);
			selectStmt.setString(1, beat);

			results = selectStmt.executeQuery();

			if(results.next()) {
				String resultBeat = results.getString("Beat");
				PoliceSectors.Precinct precinct = PoliceSectors.Precinct.valueOf
						(results.getString("Precinct"));
				PoliceSectors policeSector = new PoliceSectors(resultBeat, precinct);
				return policeSector;
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
	
	public List<PoliceSectors> getPoliceSectorsByPrecinct(PoliceSectors.Precinct precinct) throws SQLException {
		List<PoliceSectors> policeSectors = new ArrayList<PoliceSectors>();
		String selectPoliceSectors =
			"SELECT Beat, Precinct "
			+ "FROM PoliceSector WHERE Precinct=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPoliceSectors);
			selectStmt.setString(1, precinct.toString());
			results = selectStmt.executeQuery();
			while(results.next()) {
				String beat = results.getString("Beat");
				PoliceSectors.Precinct resultPrecinct = PoliceSectors.Precinct.valueOf
						(results.getString("Precinct"));
				PoliceSectors policeSector = new PoliceSectors(beat, resultPrecinct);
				policeSectors.add(policeSector);
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
		return policeSectors;
	}
	
	public PoliceSectors delete(PoliceSectors policeSector) throws SQLException {
		String deletePoliceSector = "DELETE FROM PoliceSector WHERE Beat=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePoliceSector);
			deleteStmt.setString(1, policeSector.getBeat());
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