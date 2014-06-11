package com.amity.hms.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(name="TOUTPASS")
public class OutpassBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="outpass_id")
	private Integer outpassId;
	
	@Column(name="enrollment_number", nullable=false)
	private String enrollNo;
	
	@Column(name="reason", nullable=false)
	private String reason;
	
	@Column(name="from_date", nullable=false)
	private String fromDate;
	
	@Column(name="to_date", nullable=false)
	private String toDate;
	
	@Column(name="place", nullable=false)
	private String place;
	
	@Column(name="outpass_status", nullable=false)
	private String outpassStatus;// NEW, (PA)parent-approved, (WA)warden-approved
	
	public Integer getOutpassId() {
		return outpassId;
	}
	public void setOutpassId(Integer outpassId) {
		this.outpassId = outpassId;
	}
	public String getEnrollNo() {
		return enrollNo;
	}
	public void setEnrollNo(String enrollNo) {
		this.enrollNo = enrollNo;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getOutpassStatus() {
		return outpassStatus;
	}
	public void setOutpassStatus(String outpassStatus) {
		this.outpassStatus = outpassStatus;
	}
}
