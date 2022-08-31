package com.SalJobs.backend.model;

import javax.persistence.*;

@Entity
@Table(name = "Chats")
public class Chat {
	
	public Chat() {
		
	}

	public Chat(long id, long applicantId,long recruiterId, String applicantName, String recruiterName,String initialMessage) {
		super();
		this.id = id;
		this.recruiterId = recruiterId;
		this.applicantId = applicantId;
		this.applicantName = applicantName;
		this.recruiterName = recruiterName;
		this.initialMessage = initialMessage;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "applicantName")
	private String applicantName;

	@Column(name = "recruiterName")
	private String recruiterName;
	
	@Column(name = "applicantId")
	private Long applicantId;

	@Column(name = "recruiterId")
	private Long recruiterId;
	
	@Column(name = "initialMessage")
	private String initialMessage;

	public Long getRecruiterId() {
		return recruiterId;
	}

	public void setId(Long recruiterId) {
		this.recruiterId = recruiterId;
	}
	
	public long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(long applicantId) {
		this.applicantId = applicantId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	
	public String getInitialMessage() {
		return initialMessage;
	}

	public void setInitialMessage(String message) {
		this.initialMessage = message;
	}

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiter(String recruiterName) {
		this.recruiterName = recruiterName;
	}

}