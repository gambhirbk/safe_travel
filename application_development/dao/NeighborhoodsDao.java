package safeTravel.dao;

import safeTravel.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class NeighborhoodsDao {
	protected ConnectionManager connectionManager;

	private static NeighborhoodsDao instance = null;
	protected NeighborhoodsDao() {
		connectionManager = new ConnectionManager();
	}
	public static NeighborhoodsDao getInstance() {
		if(instance == null) {
			instance = new NeighborhoodsDao();
		}
		return instance;
	}
	
	public Neighborhoods create(Neighborhoods neighborhood) throws SQLException {
		String insertNeighborhood = "INSERT INTO Neighborhood(MCPP, Beat) VALUES(?,?)";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertNeighborhood,
					Statement.RETURN_GENERATED_KEYS);

			insertStmt.setString(1, neighborhood.getMCPP());
			insertStmt.setString(2, neighborhood.getBeat().getBeat());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int neighborhoodId = -1;
			if(resultKey.next()) {
				neighborhoodId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			neighborhood.setNeighborhoodId(neighborhoodId);
			return neighborhood;
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
	
	public Neighborhoods getNeighborhoodById(int neighborhoodId) throws SQLException {
		String selectRestaurant =
			"SELECT NeighborhoodId, MCPP, Beat " +
			"FROM Neighborhood " +
			"WHERE NeighborhoodId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurant);
			selectStmt.setInt(1, neighborhoodId);
			
			results = selectStmt.executeQuery();
			PoliceSectorsDao policesectorsDao = PoliceSectorsDao.getInstance();
			if(results.next()) {
				int resultNeighborhoodId = results.getInt("NeighborhoodId");
				String MCPP = results.getString("MCPP");
				PoliceSectors beat = policesectorsDao.getPoliceSectorsByBeat(results.getString("Beat"));
				Neighborhoods neighborhood = new Neighborhoods(resultNeighborhoodId, MCPP, beat);

				return neighborhood;
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
	
	public List<Neighborhoods> getNeighborhoodsByBeat(String beat) throws SQLException {
		List<Neighborhoods> neighborhoods = new ArrayList<Neighborhoods>();
		String selectNeighborhoods =
			"SELECT NeighborhoodId, MCPP, Beat "
			+ "FROM Neighborhood WHERE Beat=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectNeighborhoods);
			selectStmt.setString(1, beat);
			results = selectStmt.executeQuery();
			PoliceSectorsDao policesectorsDao = PoliceSectorsDao.getInstance();
			while(results.next()) {
				int neighborhoodId = results.getInt("NeighborhoodId");
				String MCPP = results.getString("MCPP");
				PoliceSectors resultBeat = policesectorsDao.getPoliceSectorsByBeat(results.getString("Beat"));
				Neighborhoods neighborhood = new Neighborhoods(neighborhoodId, MCPP,resultBeat);
				neighborhoods.add(neighborhood);
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
		return neighborhoods;
	}
	
	public Neighborhoods delete(Neighborhoods neighborhood) throws SQLException {
		String deleteNeighborhood = "DELETE FROM Neighborhood WHERE NeighborhoodId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteNeighborhood);
			deleteStmt.setInt(1, neighborhood.getNeighborhoodId());
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
