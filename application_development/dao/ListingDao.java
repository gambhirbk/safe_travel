package safeTravel.dao;

import safeTravel.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ListingDao {
	protected ConnectionManager connectionManager;

	private static ListingDao instance = null;
	protected ListingDao() {
		connectionManager = new ConnectionManager();
	}
	public static ListingDao getInstance() {
		if(instance == null) {
			instance = new ListingDao();
		}
		return instance;
	}

	public Listing create(Listing listing) throws SQLException {
		String insertListing =
			"INSERT INTO Listing(ListingId, Name, Neighborhood, Latitude, Longitude, ReviewRating,"
			+ "Price, Description, PropertyType, RoomType, HostId) " +
			"VALUES(?,?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertListing);
			insertStmt.setInt(1, listing.getLisitingId());
			insertStmt.setString(2, listing.getName());
			insertStmt.setString(3, listing.getNeighborhood());
			insertStmt.setString(4, listing.getLatitude());
			insertStmt.setString(5, listing.getLongitude());
			insertStmt.setInt(6, listing.getReviewRating());
			insertStmt.setString(7, listing.getPrice());
			insertStmt.setString(8, listing.getDescription());
			insertStmt.setInt(9, listing.getPropertyType());
			insertStmt.setString(10, listing.getRoomType().name());
			insertStmt.setInt(11, listing.getHostId());
			insertStmt.executeUpdate();
			
			return listing;
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
	 * Get the Restaurant record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Restaurant instance.
	 * Note that we use RestaurantsDao to retrieve the referenced Restaurants instance.
	 */
	public Listing getListingById(int listingId) throws SQLException {
		String selectRestaurant =
			"SELECT ListingId, Name, Neighborhood, Latitude, Longitude, ReviewRating, Price, Description, PropertyType, RoomType, HostId " +
			"FROM Listing " +
			"WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRestaurant);
			selectStmt.setInt(1, listingId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				int resultLisitingId = results.getInt("ListingId");
				String name = results.getString("Name");
				String neighborhood = results.getString("Neighborhood");
				String latitude = results.getString("Latitude");
				String longitude = results.getString("Longitude");
				int reviewRating = results.getInt("ReviewRating");
				String price = results.getString("Price");
				String description = results.getString("Description");				
				int propertyType = results.getInt("PropertyType");
				Listing.RoomType roomType = Listing.RoomType.valueOf(results.getString("RoomType"));
				int hostId = results.getInt("HostId");
				
				Listing listing = new Listing(resultLisitingId, name, neighborhood, latitude, longitude, 
									reviewRating, price, description, propertyType, roomType, hostId);
						
				return listing;
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
	 * Update the Neighborhood of the Listing instance.
	 * This runs a UPDATE statement.
	 */
	public Listing updateNeighborhood(Listing listing, String newNeighborhood) throws SQLException {
		String updateListing = "UPDATE Listing SET Neighborhood=? WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateListing);
			updateStmt.setString(1, newNeighborhood);
			updateStmt.setInt(2, listing.getLisitingId());
			updateStmt.executeUpdate();
			
			listing.setNeighborhood(newNeighborhood);
			return listing;
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
	 * Delete the ListingDao instance.
	 * This runs a DELETE statement.
	 */
	public Listing delete(Listing listing) throws SQLException {
		String deleteListing = "DELETE FROM Listing WHERE ListingId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteListing);
			deleteStmt.setInt(1,listing.getLisitingId());
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
