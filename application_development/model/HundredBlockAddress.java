package safeTravel.model;

public class HundredBlockAddress {
	
	private String hundredBlockAddress;
	
	/**
	 * @param hundredBlockAddress
	 */
	public HundredBlockAddress(String hundredBlockAddress) {
		this.hundredBlockAddress = hundredBlockAddress;
	}

	/**
	 * @return the hundredBlockAddress
	 */
	public String getHundredBlockAddress() {
		return hundredBlockAddress;
	}

	/**
	 * @param hundredBlockAddress the hundredBlockAddress to set
	 */
	public void setHundredBlockAddress(String hundredBlockAddress) {
		this.hundredBlockAddress = hundredBlockAddress;
	}

	@Override
	public String toString() {
		return "HundredBlockAddress [hundredBlockAddress=" + hundredBlockAddress + "]";
	}
}
