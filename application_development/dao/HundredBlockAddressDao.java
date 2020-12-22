package safeTravel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import safeTravel.model.HundredBlockAddress;


public class HundredBlockAddressDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static HundredBlockAddressDao instance = null;
	protected HundredBlockAddressDao() {
		connectionManager = new ConnectionManager();
	}
	public static HundredBlockAddressDao getInstance() {
		if(instance == null) {
			instance = new HundredBlockAddressDao();
		}
		return instance;
	}
	
	public HundredBlockAddress create(HundredBlockAddress hundredBlockAddress) throws SQLException {
		String insertHundredBlockAddress = "INSERT INTO 100BlockAddress(100BlockAddress) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertHundredBlockAddress);
			insertStmt.setString(1, hundredBlockAddress.getHundredBlockAddress());
			insertStmt.executeUpdate();
			
	
			return hundredBlockAddress;
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
	
	public HundredBlockAddress getHundredBlockAddress(String hundredBlockAddress) throws SQLException {
		String selectHundredBlockAddress = "SELECT 100BlockAddress FROM 100BlockAddress WHERE 100BlockAddress=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHundredBlockAddress);
			selectStmt.setString(1, hundredBlockAddress);

			results = selectStmt.executeQuery();

			if(results.next()) {
				String resultHundredBlockAddress = results.getString("100BlockAddress");
				HundredBlockAddress ret = new HundredBlockAddress(resultHundredBlockAddress);
		
				return ret;
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
		
	public HundredBlockAddress updateLastName(HundredBlockAddress hundredBlockAddress, String newHundredBlockAddress) throws SQLException {
		String updateHundredBlockAddress = "UPDATE 100BlockAddress SET 100BlockAddress=? WHERE 100BlockAddress=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateHundredBlockAddress);
			updateStmt.setString(1, newHundredBlockAddress);
			updateStmt.setString(2, newHundredBlockAddress);
			updateStmt.executeUpdate();
			
			hundredBlockAddress.setHundredBlockAddress(newHundredBlockAddress);
			return hundredBlockAddress;
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
	
	public HundredBlockAddress delete(HundredBlockAddress hundredBlockAddress) throws SQLException {
		String deleteHundredBlockAddress = "DELETE FROM 100BlockAddress WHERE 100BlockAddress=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteHundredBlockAddress);
			deleteStmt.setString(1, hundredBlockAddress.getHundredBlockAddress());
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
