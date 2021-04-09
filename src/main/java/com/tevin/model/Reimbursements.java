package com.tevin.model;

public class Reimbursements {
	private Integer reimb_id;
	private Integer emp_id;
	private double amount;
	private String reason;
	private String status;
	
	public Reimbursements() {
		super();
	}

	public Reimbursements(Integer reimb_id, Integer emp_id, double amount, String reason, String status) {
		super();
		this.reimb_id = reimb_id;
		this.emp_id = emp_id;
		this.amount = amount;
		this.reason = reason;
		this.status = status;
	}

	public Integer getReimb_id() {
		return reimb_id;
	}

	public void setReimb_id(Integer reimb_id) {
		this.reimb_id = reimb_id;
	}

	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Reimbursements [reimb_id=" + reimb_id + ", emp_id=" + emp_id + ", amount=" + amount + ", reason="
				+ reason + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((emp_id == null) ? 0 : emp_id.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((reimb_id == null) ? 0 : reimb_id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursements other = (Reimbursements) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (emp_id == null) {
			if (other.emp_id != null)
				return false;
		} else if (!emp_id.equals(other.emp_id))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (reimb_id == null) {
			if (other.reimb_id != null)
				return false;
		} else if (!reimb_id.equals(other.reimb_id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	
}
