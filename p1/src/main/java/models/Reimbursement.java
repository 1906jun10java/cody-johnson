package models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Reimbursement {
	private int id;
	private int employeeId;
	private int typeId;
	private int statusId;
	private BigDecimal amount;
	private int unixTs;
	private String description;

	public static final Map<Integer, String> types;
	public static final Map<Integer, String> statuses;
	static {
		types = new HashMap<>();
		types.put(1, "Seminar");
		types.put(2, "Preparation Classes");
		types.put(3, "Certification");
		types.put(4, "Technical Training");
		types.put(5, "Other");

		statuses = new HashMap<>();
		statuses.put(-1, "Rejected");
		statuses.put(0, "More Information Required");
		statuses.put(1, "Initialized");
		statuses.put(2, "Supervisor Approved");
		statuses.put(3, "Department Head Approved");
		statuses.put(4, "Accepted");
	}

	public Reimbursement() {}
	public Reimbursement(int employeeId, int typeId, int statusId,
	BigDecimal amount, int unixTs, String description) {
		this.employeeId = employeeId;
		this.typeId = typeId;
		this.statusId = statusId;
		this.amount = amount;
		this.unixTs = unixTs;
		this.description = description;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public int getUnixTs() {
		return unixTs;
	}
	public void setUnixTs(int unixTs) {
		this.unixTs = unixTs;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
