package com.SalJobs.backend.model;

import javax.persistence.*;

/*
 * Class for the listing. Add the constructor, getters and setter. The @ stuff
 * corresponds to SQL notation, i.e. @ID == primary key, @Column == attribute. 
 * For other stuff like foreign keys, search up JPA annotations, jpa foreign key etc.
 */

@Entity
@Table(name = "Listings")
public class Listing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "company")
	private String company;

	@Column(name = "title")
	private String title;

	@Column(name = "description",length=2000)
	private String description;

	@Column(name = "location")
	private String location;
	
	@Column(name = "recruiterId")
	private String recruiterId;
	
	@Column(name = "recruiterName")
	private String recruiterName;
	
	@Column(name = "applicantCount")
	private long applicantCount;
	
	public Listing() {

	}

	public Listing(long id, String company, String title, String description, String location,String recruiterId,String recruiterName
			,long applicantCount) {
		this.id = id;
		this.company = company;
		this.title = title;
		this.description = description;
		this.location = location;
		this.recruiterId = recruiterId;
		this.recruiterName = recruiterName;
		this.applicantCount = applicantCount;
	}
	
	

	public String getRecruiterId() {
		return recruiterId;
	}

	public void setRecruiterId(String recruiterId) {
		this.recruiterId = recruiterId;
	}

	public String getRecruiterName() {
		return recruiterName;
	}

	public void setRecruiterName(String recruiterName) {
		this.recruiterName = recruiterName;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getId() {
		return id;
	}

	public String getCompany() {
		return company;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getLocation() {
		return location;
	}

	public long getApplicantCount() {
		return applicantCount;
	}

	public void setApplicantCount(long applicantCount) {
		this.applicantCount = applicantCount;
	}

}
