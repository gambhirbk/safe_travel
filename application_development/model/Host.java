package safeTravel.model;

import java.util.Date;

public class Host{
	private Integer hostId;  
	private String name;
	private Date since;
	private Float responseRate;  
	private Boolean isSuperHost;
	private String neighborhood;
	private Integer listingCount;
	
	/**
	 * @param hostId
	 * @param name
	 * @param since
	 * @param responseRate
	 * @param isSuperHost
	 * @param neighborhood
	 * @param listingCount
	 */
	public Host(Integer hostId, String name, Date since, Float responseRate, Boolean isSuperHost, String neighborhood,
			Integer listingCount) {
		this.hostId = hostId;
		this.name = name;
		this.since = since;
		this.responseRate = responseRate;
		this.isSuperHost = isSuperHost;
		this.neighborhood = neighborhood;
		this.listingCount = listingCount;
	}
	
	/**
	 * @return the hostId
	 */
	public Integer getHostId() {
		return hostId;
	}
	
	/**
	 * @param hostId the hostId to set
	 */
	public void setHostId(Integer hostId) {
		this.hostId = hostId;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the since
	 */
	public Date getSince() {
		return since;
	}
	
	/**
	 * @param since the since to set
	 */
	public void setSince(Date since) {
		this.since = since;
	}
	
	/**
	 * @return the responseRate
	 */
	public Float getResponseRate() {
		return responseRate;
	}
	
	/**
	 * @param responseRate the responseRate to set
	 */
	public void setResponseRate(Float responseRate) {
		this.responseRate = responseRate;
	}
	
	/**
	 * @return the isSuperHost
	 */
	public Boolean getIsSuperHost() {
		return isSuperHost;
	}
	
	/**
	 * @param isSuperHost the isSuperHost to set
	 */
	public void setIsSuperHost(Boolean isSuperHost) {
		this.isSuperHost = isSuperHost;
	}
	
	/**
	 * @return the neighborhood
	 */
	public String getNeighborhood() {
		return neighborhood;
	}
	
	/**
	 * @param neighborhood the neighborhood to set
	 */
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	
	/**
	 * @return the listingCount
	 */
	public Integer getListingCount() {
		return listingCount;
	}
	
	/**
	 * @param listingCount the listingCount to set
	 */
	public void setListingCount(Integer listingCount) {
		this.listingCount = listingCount;
	}
	
	@Override
	public String toString() {
		return "Host [hostId=" + hostId + ", name=" + name + ", since=" + since + ", responseRate=" + responseRate
				+ ", isSuperHost=" + isSuperHost + ", neighborhood=" + neighborhood + ", listingCount=" + listingCount
				+ "]";
	}
	
}