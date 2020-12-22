package safeTravel.model;


public class OffenseGroups {
	String offenseParentGroup;
	CrimeAgainstCategory crimeAgainstCategory;
	
	public enum CrimeAgainstCategory {
		PROPERTY, SOCIETY, PERSON
	}

	public OffenseGroups(String offenseParentGroup, CrimeAgainstCategory crimeAgainstCategory) {
		this.offenseParentGroup = offenseParentGroup;
		this.crimeAgainstCategory = crimeAgainstCategory;
	}

	public String getOffenseParentGroup() {
		return offenseParentGroup;
	}

	public void setOffenseParentGroup(String offenseParentGroup) {
		this.offenseParentGroup = offenseParentGroup;
	}

	public CrimeAgainstCategory getCrimeAgainstCategory() {
		return crimeAgainstCategory;
	}

	public void setCrimeAgainstCategory(CrimeAgainstCategory crimeAgainstCategory) {
		this.crimeAgainstCategory = crimeAgainstCategory;
	}
	
	@Override
	public String toString() {
		return "OffenseGroup [offenseParentGroup=" + offenseParentGroup + ", crimeAgainstCategory=" + crimeAgainstCategory + "]";
	}
	
}
