package safeTravel.model;


public class OffenseTypes {
	String offenseCode;
	String offense;
	String offenseParentGroup;
	
	public String getOffenseCode() {
		return offenseCode;
	}
	public void setOffenseCode(String offenseCode) {
		this.offenseCode = offenseCode;
	}
	public String getOffense() {
		return offense;
	}
	public void setOffense(String offense) {
		this.offense = offense;
	}
	public String getOffenseParentGroup() {
		return offenseParentGroup;
	}
	public void setOffenseParentGroup(String offenseParentGroup) {
		this.offenseParentGroup = offenseParentGroup;
	}
	public OffenseTypes(String offenseCode, String offense, String offenseParentGroup) {
		super();
		this.offenseCode = offenseCode;
		this.offense = offense;
		this.offenseParentGroup = offenseParentGroup;
	}
	
	@Override
	public String toString() {
		return "OffenseType [offenseCode=" + offenseCode + ", offense=" + offense 
				+  ", offenseParentGroup=" + offenseParentGroup  + "]";
	}
	
}
