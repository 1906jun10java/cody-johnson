package models;

import java.math.BigDecimal;

public class Reimbursement {
	private int id;
	private int employeeId;
	private int typeId;
	private String typeName;
	private int statusId;
	private String statusName;
	private BigDecimal amount;
	private long unixTs;
	private String description;
	private byte[] receiptImgFile;

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
	public long getUnixTs() {
		return unixTs;
	}
	public void setUnixTs(long unixTs) {
		this.unixTs = unixTs;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte[] getReceiptImgFile() {
		return receiptImgFile;
	}
	public void setReceiptImgFile(byte[] receiptImgUrl) {
		this.receiptImgFile = receiptImgUrl;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
