package safeTravel.model;


public class Neighborhoods {
	protected int neighborhoodId;
	protected String MCPP;
	protected PoliceSectors beat;
	
	public Neighborhoods(int neighborhoodId, String MCPP, PoliceSectors beat) {
		this.neighborhoodId = neighborhoodId;
		this.MCPP = MCPP;
		this.beat = beat;
	}
	
	public Neighborhoods(int neighborhoodId) {
		this.neighborhoodId = neighborhoodId;
	}
	
	public int getNeighborhoodId() {
		return neighborhoodId;
	}

	public void setNeighborhoodId(int neighborhoodId) {
		this.neighborhoodId = neighborhoodId;
	}

	public String getMCPP() {
		return MCPP;
	}

	public void setMCPP(String mCPP) {
		MCPP = mCPP;
	}

	public PoliceSectors getBeat() {
		return beat;
	}

	public void setBeat(PoliceSectors beat) {
		this.beat = beat;
	}
	
	@Override
	public String toString() {
		return "Neighborhood [neighborhoodId=" + neighborhoodId + ", MCPP=" + MCPP + ", beat=" + beat.getBeat() + "]";
	}

}
