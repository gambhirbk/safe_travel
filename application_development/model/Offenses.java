package safeTravel.model;

import java.sql.Timestamp;
import java.util.Date;



/* 
 * the constructor regards reportDateTime as TimeStamp, offenseType as OffenseTypes, 
 * hundredBlockAddress as HundredBlockAddress; latitude, longitude as String
 */
public class Offenses {
	protected String offenseId;
	protected Timestamp reportDateTime;
	protected OffenseTypes offenseType;
	protected Neighborhoods neighborhood;
	protected HundredBlockAddress hundredBlockAddress;
	protected Locations location;
	

	public Offenses(String offenseId, Timestamp reportDateTime, OffenseTypes offenseType,
			Neighborhoods neighborhood, HundredBlockAddress hundredBlockAddress, Locations location) {
		this.offenseId = offenseId;
		this.reportDateTime = reportDateTime;
		this.offenseType = offenseType;
		this.neighborhood = neighborhood;
		this.hundredBlockAddress = hundredBlockAddress;
		this.location = location;
	}

	public Offenses(String offenseId) {
		this.offenseId = offenseId;
	}

	public Offenses(Timestamp reportDateTime, OffenseTypes offenseType,
			Neighborhoods neighborhood, HundredBlockAddress hundredBlockAddress, Locations location) {
		this.reportDateTime = reportDateTime;
		this.offenseType = offenseType;
		this.neighborhood = neighborhood;
		this.hundredBlockAddress = hundredBlockAddress;
		this.location = location;
	}
	

	public String getOffenseId() {
		return offenseId;
	}

	public void setOffenseId(String offenseId) {
		this.offenseId = offenseId;
	}

	public Date getReportDateTime() {
		return reportDateTime;
	}

	public void setReportDateTime(Timestamp reportDateTime) {
		this.reportDateTime = reportDateTime;
	}

	public OffenseTypes getOffenseType() {
		return offenseType;
	}

	public void setOffenseType(OffenseTypes offenseType) {
		this.offenseType = offenseType;
	}

	public Neighborhoods getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(Neighborhoods neighborhood) {
		this.neighborhood = neighborhood;
	}

	public HundredBlockAddress getHundredBlockAddress() {
		return hundredBlockAddress;
	}

	public void setHundredBlockAddress(HundredBlockAddress hundredBlockAddress) {
		this.hundredBlockAddress = hundredBlockAddress;
	}

	public Locations getLocation() {
		return location;
	}

	public void setLocation(Locations location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "Offenses [offenseId=" + offenseId + ", reportDateTime=" + reportDateTime + ", offenseType=" + offenseType.getOffenseCode() 
				+   ", neighborhood=" + neighborhood.getMCPP() + ", hundredBlockAddress=" + hundredBlockAddress.getHundredBlockAddress() + ", location=" + location.getLatitude() + "]";
	}

	

}