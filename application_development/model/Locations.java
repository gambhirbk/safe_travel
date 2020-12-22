package safeTravel.model;

/**
 * @author Xinyu Dai
 *
 * Nov 12, 2020 - 8:31:21 PM
 */
public class Locations {
	protected String latitude;
	protected String longitude;
	
	public Locations(String latitude, String longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
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

	@Override
	public String toString() {
		return "Locations [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	
	
	
	
}
