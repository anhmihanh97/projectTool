package vn.com.cmcglobal.du5.model;

public enum TicketType {
	DEFECT("defect"), UPDATE_TC("update_tc"), ENVIRONMENT("environment"), QA("qa");
	// DEFECT("defect");

	private String type;

	public String getType() {
		return type;
	}

	private TicketType(String type) {
		this.type = type;
	}

}
