package com.swashtech.model;

public class ReportModel {

	private String orgId;
	private String oprId;
	private String name;
	private Integer mobile;
	private String tokenNo;
	private String mode;
	private Boolean enter;
	private Boolean exit;
	private String entryDateTime;
	private String exitDateTime;
	private Long createdOn;

	public ReportModel(String orgId, String oprId, String name, Integer mobile, String tokenNo, String mode,
			Boolean enter, Boolean exit, String entryDateTime, String exitDateTime, Long createdOn) {
		super();
		this.orgId = orgId;
		this.oprId = oprId;
		this.name = name;
		this.mobile = mobile;
		this.tokenNo = tokenNo;
		this.mode = mode;
		this.enter = enter;
		this.exit = exit;
		this.entryDateTime = entryDateTime;
		this.exitDateTime = exitDateTime;
		this.createdOn = createdOn;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOprId() {
		return oprId;
	}

	public void setOprId(String oprId) {
		this.oprId = oprId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMobile() {
		return mobile;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}

	public String getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Boolean getEnter() {
		return enter;
	}

	public void setEnter(Boolean enter) {
		this.enter = enter;
	}

	public Boolean getExit() {
		return exit;
	}

	public void setExit(Boolean exit) {
		this.exit = exit;
	}

	public String getEntryDateTime() {
		return entryDateTime;
	}

	public void setEntryDateTime(String entryDateTime) {
		this.entryDateTime = entryDateTime;
	}

	public String getExitDateTime() {
		return exitDateTime;
	}

	public void setExitDateTime(String exitDateTime) {
		this.exitDateTime = exitDateTime;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}
	
	

}
