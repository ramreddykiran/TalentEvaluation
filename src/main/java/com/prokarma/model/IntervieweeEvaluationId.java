package com.prokarma.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Embeddable
//@XmlRootElement(name="emp-eval-id")
@XmlAccessorType(XmlAccessType.FIELD)
public class IntervieweeEvaluationId implements Serializable{
	
	@Column(name="EVALID")
	@XmlElement(name="evaluator-id")
	private Integer evaluatorId;
	
	@XmlElement(name="interviewee-id")
	private Integer intervieweeId;
	
	@XmlElement(name="interview-round")
	private Integer interviewRound;
	
	public Integer getEvaluatorId() {
		return evaluatorId;
	}
	public void setEvaluatorId(Integer evaluatorId) {
		this.evaluatorId = evaluatorId;
	}
	public Integer getIntervieweeId() {
		return intervieweeId;
	}
	public void setIntervieweeId(Integer intervieweeId) {
		this.intervieweeId = intervieweeId;
	}
	public Integer getInterviewRound() {
		return interviewRound;
	}
	public void setInterviewRound(Integer interviewRound) {
		this.interviewRound = interviewRound;
	}

}