package safeTravel.model;

public class PoliceSectors {
	protected String beat;
	protected Precinct precinct;
	
	public enum Precinct{
		N, S, W, E, SW, UNKNOWN
	}
	
	public PoliceSectors(String beat, Precinct precinct ) {
		this.beat = beat;
		this.precinct = precinct;
	}
	
	public PoliceSectors(String beat) {
		this.beat = beat;
	}
	
	public String getBeat() {
		return beat;
	}

	public void setBeat(String beat) {
		this.beat = beat;
	}

	public Precinct getPrecinct() {
		return precinct;
	}

	public void setPrecinct(Precinct precinct) {
		this.precinct = precinct;
	}
	
	@Override
	public String toString() {
		return "Police Sector [beat=" + beat + ", precinct=" + precinct + "]";
	}
	
}
