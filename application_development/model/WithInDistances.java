package safeTravel.model;

/**
 * @author Xinyu Dai
 *
 * Nov 12, 2020 - 8:29:16 PM
 */
public class WithInDistances {
	protected int recordId;
	protected int listingId;
	protected String offenseId;
	
	public WithInDistances(int recordId, int listingId, String offenseId) {
		this.recordId = recordId;
		this.listingId = listingId;
		this.offenseId = offenseId;
	}

	public WithInDistances(int listingId, String offenseId) {
		this.listingId = listingId;
		this.offenseId = offenseId;
	}

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public int getListingId() {
		return listingId;
	}

	public void setListingId(int listingId) {
		this.listingId = listingId;
	}

	public String getOffenseId() {
		return offenseId;
	}

	public void setOffenseId(String offenseId) {
		this.offenseId = offenseId;
	}

	@Override
	public String toString() {
		return "WithInDistances [recordId=" + recordId + ", listingId=" + listingId + ", offenseId=" + offenseId + "]";
	}

}
