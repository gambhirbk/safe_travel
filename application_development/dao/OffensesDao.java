package safeTravel.dao;

import safeTravel.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class OffensesDao {
	protected ConnectionManager connectionManager;

	private static OffensesDao instance = null;
	protected OffensesDao() {
		connectionManager = new ConnectionManager();
	}
	public static OffensesDao getInstance() {
		if(instance == null) {
			instance = new OffensesDao();
		}
		return instance;
	}

	// Create
	public Offenses create(Offenses offense) throws SQLException {
		String insertOffense =
			"INSERT INTO Offense(OffenseId,ReportDateTime,OffenseCode,NeighborhoodId,100BlockAddress,Latitude,Longitude) " +
			"VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertOffense);
			insertStmt.setString(1, offense.getOffenseId());
			insertStmt.setTimestamp(2, new Timestamp(offense.getReportDateTime().getTime()));
			// here get "String offense" in OffenseTypes class
			insertStmt.setString(3, offense.getOffenseType().getOffenseCode());
			// here get "Integer neighborhoodId " in Neighborhoods class
			insertStmt.setInt(4, offense.getNeighborhood().getNeighborhoodId());
			insertStmt.setString(5, offense.getHundredBlockAddress().getHundredBlockAddress());
			insertStmt.setString(6, offense.getLocation().getLatitude());
			insertStmt.setString(7, offense.getLocation().getLongitude());
			insertStmt.executeUpdate();
			return offense;
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
	
	// Delete
	public Offenses delete(Offenses offense) throws SQLException {
		String deleteOffense = "DELETE FROM Offense WHERE OffenseId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteOffense);
			deleteStmt.setString(1, offense.getOffenseId());
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

	
	// Update - newReportDateTime
	public Offenses updateReportDateTime(Offenses offense, Timestamp newReportDateTime) throws SQLException {
		String updateOffense = "UPDATE Offense SET ReportDateTime=? WHERE OffenseId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateOffense);
			updateStmt.setTimestamp(1, new Timestamp(newReportDateTime.getTime()));
			updateStmt.setString(2, offense.getOffenseId());
			updateStmt.executeUpdate();

			offense.setReportDateTime(newReportDateTime);
			return offense;
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
	
	// Update - newOffenseCode. Input is OffenseTypes instead of String offenseCode, not sure which one is better
	/*
	public Offenses updateOffenseCode(Offenses offense, OffenseTypes newOffenseCode) throws SQLException {
		String updateOffense = "UPDATE Offenses SET OffenseCode=?, WHERE OffenseId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateOffense);
			updateStmt.setString(1, offense.getOffenseType().getOffenseCode());
			updateStmt.setString(2, offense.getOffenseId());
			updateStmt.executeUpdate();

			offense.setOffenseType(newOffenseCode);
			return offense;
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
	*/
	
	// Update - newNeighborhoodId. Input is Neighborhoods instead of Integer neighborhoodId, not sure which one is better
	/*
	public Offenses updateNeighborhoodId(Offenses offense, Neighborhoods newNeighborhood) throws SQLException {
		String updateOffense = "UPDATE Offenses SET NeighborhoodId=?, WHERE OffenseId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateOffense);
			updateStmt.setInt(1, offense.getNeighborhood().getNeighborhoodId());
			updateStmt.setString(2, offense.getOffenseId());
			updateStmt.executeUpdate();

			offense.setNeighborhood(newNeighborhood);
			return offense;
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
	*/
	
	// Read - Get offense by offenseId
	public Offenses getOffenseById(String offenseId) throws SQLException {
		String selectOffense =
			"SELECT OffenseId,ReportDateTime,OffenseCode,NeighborhoodId,100BlockAddress,Latitude,Longitude " +
			"FROM Offense " +
			"WHERE OffenseId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectOffense);
			selectStmt.setString(1, offenseId);
			results = selectStmt.executeQuery();
			LocationsDao locationsDao = LocationsDao.getInstance();
			OffenseTypesDao offenseTypesDao = OffenseTypesDao.getInstance();
			NeighborhoodsDao neighborhoodsDao = NeighborhoodsDao.getInstance();
			HundredBlockAddressDao hundredBlockAddressDao = HundredBlockAddressDao.getInstance();
	
			if(results.next()) {
				String resultOffenseId = results.getString("OffenseId");
				// Ask TA to make sure. Not like "Date created =  new Date(results.getTimestamp("Created").getTime());"
				Timestamp reportDateTime = results.getTimestamp("ReportDateTime");
				String offenseCode = results.getString("OffenseCode");
				int neighborhoodId = results.getInt("NeighborhoodId");
				String hundredBlockAddress = results.getString("100BlockAddress");
				String latitude = results.getString("Latitude");
				
				Locations location = locationsDao.getLocationByLatitude(latitude);
				OffenseTypes offenseTypes = offenseTypesDao.getOffenseTypesByOffenseCode(offenseCode);
				Neighborhoods neighborhoods = neighborhoodsDao.getNeighborhoodById(neighborhoodId);
				HundredBlockAddress hundredBlockAddressObj = hundredBlockAddressDao.getHundredBlockAddress(hundredBlockAddress);
				
				Offenses offense = new Offenses(resultOffenseId, reportDateTime, offenseTypes, neighborhoods, hundredBlockAddressObj, location);
				return offense;
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
	
	// Read - Get offense by reportDateTime
		public List<Offenses> getOffenseByReportDateTime(Timestamp reportDateTime) throws SQLException {
			List<Offenses> offenses = new ArrayList<Offenses>();
			String selectOffense =
				"SELECT OffenseId,ReportDateTime,OffenseCode,NeighborhoodId,100BlockAddress,Latitude,Longitude " +
				"FROM Offense " +
				"WHERE ReportDateTime=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectOffense);
				selectStmt.setString(1, reportDateTime.toString());
				results = selectStmt.executeQuery();
				LocationsDao locationsDao = LocationsDao.getInstance();
				OffenseTypesDao offenseTypesDao = OffenseTypesDao.getInstance();
				NeighborhoodsDao neighborhoodsDao = NeighborhoodsDao.getInstance();
				HundredBlockAddressDao hundredBlockAddressDao = HundredBlockAddressDao.getInstance();
		
				while(results.next()) {
					String offenseId = results.getString("OffenseId");
					// Ask TA to make sure. Not like "Date created =  new Date(results.getTimestamp("Created").getTime());"
					Timestamp resultReportDateTime = results.getTimestamp("ReportDateTime");
					String offenseCode = results.getString("OffenseCode");
					int neighborhoodId = results.getInt("NeighborhoodId");
					String hundredBlockAddress = results.getString("100BlockAddress");
					String latitude = results.getString("Latitude");
					
					Locations location = locationsDao.getLocationByLatitude(latitude);
					OffenseTypes offenseTypes = offenseTypesDao.getOffenseTypesByOffenseCode(offenseCode);
					Neighborhoods neighborhoods = neighborhoodsDao.getNeighborhoodById(neighborhoodId);
					HundredBlockAddress hundredBlockAddressObj = hundredBlockAddressDao.getHundredBlockAddress(hundredBlockAddress);
					
					Offenses offense = new Offenses(offenseId, resultReportDateTime, offenseTypes, neighborhoods, 
							hundredBlockAddressObj, location);
					offenses.add(offense);
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
			return offenses;
		}
	
		// Read - Get offense by offenseCode(type related)
		public List<Offenses> getOffenseByOffenseType(OffenseTypes offenseCode) throws SQLException {
			List<Offenses> offenses = new ArrayList<Offenses>();
			String selectOffense =
				"SELECT OffenseId,ReportDateTime,OffenseCode,NeighborhoodId,100BlockAddress,Latitude,Longitude " +
				"FROM Offense " +
				"WHERE OffenseCode=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectOffense);
				selectStmt.setString(1, offenseCode.getOffenseCode());
				results = selectStmt.executeQuery();
				LocationsDao locationsDao = LocationsDao.getInstance();
				OffenseTypesDao offenseTypesDao = OffenseTypesDao.getInstance();
				NeighborhoodsDao neighborhoodsDao = NeighborhoodsDao.getInstance();
				HundredBlockAddressDao hundredBlockAddressDao = HundredBlockAddressDao.getInstance();
		
				while(results.next()) {
					String offenseId = results.getString("OffenseId");
					// Ask TA to make sure. Not like "Date created =  new Date(results.getTimestamp("Created").getTime());"
					Timestamp reportDateTime = results.getTimestamp("ReportDateTime");
					String resultoffenseCode = results.getString("OffenseCode");
					int neighborhoodId = results.getInt("NeighborhoodId");
					String hundredBlockAddress = results.getString("100BlockAddress");
					String latitude = results.getString("Latitude");
					
					Locations location = locationsDao.getLocationByLatitude(latitude);
					OffenseTypes offenseTypes = offenseTypesDao.getOffenseTypesByOffenseCode(resultoffenseCode);
					Neighborhoods neighborhoods = neighborhoodsDao.getNeighborhoodById(neighborhoodId);
					HundredBlockAddress hundredBlockAddressObj = hundredBlockAddressDao.getHundredBlockAddress(hundredBlockAddress);
					
					Offenses offense = new Offenses(offenseId, reportDateTime, offenseTypes, neighborhoods, 
							hundredBlockAddressObj, location);
					offenses.add(offense);
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
			return offenses;
		}
	
	
		// Read - Get offense by neighborhood
		public List<Offenses> getOffenseByNeighborhood(Neighborhoods neighborhood) throws SQLException {
			List<Offenses> offenses = new ArrayList<Offenses>();
			String selectOffense =
				"SELECT OffenseId,ReportDateTime,OffenseCode,NeighborhoodId,100BlockAddress,Latitude,Longitude " +
				"FROM Offense " +
				"WHERE NeighborhoodId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectOffense);
				selectStmt.setInt(1, neighborhood.getNeighborhoodId());
				results = selectStmt.executeQuery();
				LocationsDao locationsDao = LocationsDao.getInstance();
				OffenseTypesDao offenseTypesDao = OffenseTypesDao.getInstance();
				NeighborhoodsDao neighborhoodsDao = NeighborhoodsDao.getInstance();
				HundredBlockAddressDao hundredBlockAddressDao = HundredBlockAddressDao.getInstance();
		
				while(results.next()) {
					String offenseId = results.getString("OffenseId");
					// Ask TA to make sure. Not like "Date created =  new Date(results.getTimestamp("Created").getTime());"
					Timestamp reportDateTime = results.getTimestamp("ReportDateTime");
					String offenseCode = results.getString("OffenseCode");
					int resultNeighborhoodId = results.getInt("NeighborhoodId");
					String hundredBlockAddress = results.getString("100BlockAddress");
					String latitude = results.getString("Latitude");
					
					Locations location = locationsDao.getLocationByLatitude(latitude);
					OffenseTypes offenseTypes = offenseTypesDao.getOffenseTypesByOffenseCode(offenseCode);
					Neighborhoods neighborhoods = neighborhoodsDao.getNeighborhoodById(resultNeighborhoodId);
					HundredBlockAddress hundredBlockAddressObj = hundredBlockAddressDao.getHundredBlockAddress(hundredBlockAddress);
					
					Offenses offense = new Offenses(offenseId, reportDateTime, offenseTypes, neighborhoods, hundredBlockAddressObj, location);
					offenses.add(offense);
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
			return offenses;
		}
}
