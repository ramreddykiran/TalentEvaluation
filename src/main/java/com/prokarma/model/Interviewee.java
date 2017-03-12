package com.prokarma.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="INTERVIEWEE")
public class Interviewee {
	
	@Id
	@Column(name="id")
	private Integer id;
	private String name;
	@Column(name="mblnum")
	private Long mobileNumber;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "INTERVIEWEECERTIFICATE", joinColumns = { @JoinColumn(name = "INTERVIEWEEID") }, inverseJoinColumns = { @JoinColumn(name = "CERTIFICATEID") })
	private Set<Certificate> certificates;
	
	@Column(name="jprofileid")
	private Integer jobProfileId;
	
	public Integer getJobProfileId() {
		return jobProfileId;
	}
	public void setJobProfileId(Integer jobProfileId) {
		this.jobProfileId = jobProfileId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public Set<Certificate> getCertificates() {
		return certificates;
	}
	public void setCertificates(Set<Certificate> certificates) {
		this.certificates = certificates;
	}

}
