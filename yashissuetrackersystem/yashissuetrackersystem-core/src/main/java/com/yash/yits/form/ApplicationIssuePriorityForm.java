package com.yash.yits.form;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.yash.yits.domain.Application;
import com.yash.yits.domain.ApplicationSeverity;
import com.yash.yits.domain.ApplicationTeamMember;
import com.yash.yits.domain.Issue;

public class ApplicationIssuePriorityForm {

	private int id;

	private Timestamp createdDateTime;

	private int isActive;

	private Timestamp lastModifiedDateTime;

	private String type;

	private ApplicationForm application;

	private ApplicationTeamMemberForm lastModifiedBy;

	private ApplicationTeamMemberForm createdBy;

	private ApplicationSeverityForm applicationSeverity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Timestamp createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public Timestamp getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Timestamp lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ApplicationForm getApplication() {
		return application;
	}

	public void setApplication(ApplicationForm application) {
		this.application = application;
	}

	public ApplicationTeamMemberForm getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(ApplicationTeamMemberForm lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public ApplicationTeamMemberForm getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(ApplicationTeamMemberForm createdBy) {
		this.createdBy = createdBy;
	}

	public ApplicationSeverityForm getApplicationSeverity() {
		return applicationSeverity;
	}

	public void setApplicationSeverity(ApplicationSeverityForm applicationSeverity) {
		this.applicationSeverity = applicationSeverity;
	}
	
}
