package safeTravel.model;

/**
 * Property is a simple, plain old java objects (POJO).
 */
public class PropertyType {
	protected int propertyId;
	protected String type;

	public PropertyType(int propertyId, String type) {
		super();
		this.propertyId = propertyId;
		this.type = type;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "PropertyType [propertyId=" + propertyId + ", type=" + type + "]";
	}

}
	
