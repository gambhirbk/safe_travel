package safeTravel.model;


public class Listing {
	protected int lisitingId;
	protected String name;
	protected String neighborhood;
	protected String latitude;
	protected String longitude;
	protected int reviewRating;
	protected String price;
	protected String description;
	protected int propertyType;
	protected RoomType roomType;
	protected int hostId;
	
	
	public enum RoomType {
		Entire_home_apt, Private_room, Hotel_room, Shared_room
	}


	public Listing(int lisitingId, String name, String neighborhood, String latitude, String longitude, int reviewRating,
			String price, String description, int propertyType, RoomType roomType, int hostId) {
		super();
		this.lisitingId = lisitingId;
		this.name = name;
		this.neighborhood = neighborhood;
		this.latitude = latitude;
		this.longitude = longitude;
		this.reviewRating = reviewRating;
		this.price = price;
		this.description = description;
		this.propertyType = propertyType;
		this.roomType = roomType;
		this.hostId = hostId;
	}


	public int getLisitingId() {
		return lisitingId;
	}


	public void setLisitingId(int lisitingId) {
		this.lisitingId = lisitingId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getNeighborhood() {
		return neighborhood;
	}


	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public int getReviewRating() {
		return reviewRating;
	}


	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getPropertyType() {
		return propertyType;
	}


	public void setPropertyType(int propertyType) {
		this.propertyType = propertyType;
	}


	public RoomType getRoomType() {
		return roomType;
	}


	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}


	public int getHostId() {
		return hostId;
	}


	public void setHostId(int hostId) {
		this.hostId = hostId;
	}
	
	@Override
	public String toString() {
		return "Listing [lisitingId=" + lisitingId + ", name=" + name + ", neighborhood=" + neighborhood 
				+   ", latitude=" + latitude + ", longitude=" + longitude + ", reviewRating=" + reviewRating + ", price=" + price
				+ ", description=" + description + ", propertyType=" + propertyType + ", roomType=" + roomType + ", hostId=" + hostId +" ]";
	}	
	
}
