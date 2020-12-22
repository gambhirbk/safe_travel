package safeTravel.dao;

import safeTravel.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HostDao {
	// Single pattern: instantiation is limited to one object.
	private static HostDao instance = null;
	protected ConnectionManager connectionManager;

	protected HostDao() {
	connectionManager = new ConnectionManager();
	}

	public static HostDao getInstance() {
		if (instance == null) {
			instance = new HostDao();
		}
		return instance;
	}

	public Host create(Host host) throws SQLException {
		String insertHost = "INSERT INTO Host(HostId, HostName, HostSince, HostResponseRate, HostIsSuperHost, HostNeighborhood, HostListingCount)"
				+ "VALUES(?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertHost);

			insertStmt.setInt(1, host.getHostId());
			insertStmt.setString(2, host.getName());
			insertStmt.setTimestamp(3, new Timestamp(host.getSince().getTime()));
			insertStmt.setFloat(4, host.getResponseRate());
			insertStmt.setBoolean(5, host.getIsSuperHost());
			insertStmt.setString(6, host.getNeighborhood());
			insertStmt.setInt(7, host.getListingCount());
			insertStmt.executeUpdate();
			return host;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
		}
	}

	public Host updateHostByHostName(Host host, String newHostName) throws SQLException {
		String updateHost = "UPDATE Host SET HostName=? WHERE HostId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateHost);
			updateStmt.setString(1, newHostName);
			updateStmt.setInt(2, host.getHostId());
			updateStmt.executeUpdate();

			host.setName(newHostName);
			return host;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	public Host delete(Host host) throws SQLException {
		String deleteHost = "DELETE FROM Host WHERE HostId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteHost);
			deleteStmt.setInt(1, host.getHostId());

			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	public Host getHostByHostId(int hostId) throws SQLException {
		String selectHost = "SELECT HostId,HostName,HostSince,HostResponseRate,HostIsSuperHost,HostNeighborhood,HostListingCount "
				+ "FROM Host " + "WHERE HostId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHost);
			selectStmt.setInt(1, hostId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				Integer resultHostId = results.getInt("HostId");
				String resultHostName = results.getString("HostName");
				Date resultHostSince = new Date(results.getTimestamp("HostSince").getTime());
				Float resultHostResponseRate = results.getFloat("HostResponseRate");
				Boolean resultHostIsSuperHost = results.getBoolean("HostIsSuperHost");
				String resultHostNeighborhood = results.getString("HostNeighborhood");
				Integer resultHostListingCount = results.getInt("HostListingCount");

				Host host = new Host(resultHostId, resultHostName, resultHostSince, resultHostResponseRate,
						resultHostIsSuperHost, resultHostNeighborhood, resultHostListingCount);
				return host;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;

	}

	public List<Host> getAllSuperHosts() throws SQLException {
		List<Host> hosts = new ArrayList<Host>();
		String selectHost = "SELECT HostId,HostName,HostSince,HostResponseRate,HostIsSuperHost,HostNeighborhood,HostListingCount "
				+ "FROM Host " + "WHERE HostIsSuperHost=TRUE;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectHost);
			results = selectStmt.executeQuery();
			while (results.next()) {
				Integer resultHostId = results.getInt("HostId");
				String resultHostName = results.getString("HostName");
				Date resultHostSince = new Date(results.getTimestamp("HostSince").getTime());
				Float resultHostResponseRate = results.getFloat("HostResponseRate");
				Boolean resultHostIsSuperHost = results.getBoolean("HostIsSuperHost");
				String resultHostNeighborhood = results.getString("HostNeighborhood");
				Integer resultHostListingCount = results.getInt("HostListingCount");

				Host host = new Host(resultHostId, resultHostName, resultHostSince, resultHostResponseRate,
						resultHostIsSuperHost, resultHostNeighborhood, resultHostListingCount);
				hosts.add(host);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return hosts;
	}

}