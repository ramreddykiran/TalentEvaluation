package com.prokarma.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "INTERVIEWEEEVALUATION")
@SuppressWarnings("restriction")
@XmlRootElement(name="emp-eval")
@XmlAccessorType(XmlAccessType.FIELD)
public class IntervieweeEvaluation {

	@Column(name = "evalname")
	@XmlElement(name="eval-name")
	private String evaluatorName;
	
	@XmlElement(name="skill-id")
	private Integer skillId;
	
	@Column(name = "techrating")
	@XmlElement(name="tech-rating")
	private Integer technicalRating;
	
	@XmlElement(name="personal-rating")
	private Integer personalRating;
	
	@XmlElement(name="interview-type")
	private String interviewType;
	
	@EmbeddedId
	@XmlElement(name="emp-eval-id")
	private IntervieweeEvaluationId intervieweeEvaluationId;

	public IntervieweeEvaluationId getIntervieweeEvaluationId() {
		return intervieweeEvaluationId;
	}

	public void setIntervieweeEvaluationId(IntervieweeEvaluationId intervieweeEvaluationId) {
		this.intervieweeEvaluationId = intervieweeEvaluationId;
	}

	public String getEvaluatorName() {
		return evaluatorName;
	}

	public void setEvaluatorName(String evaluatorName) {
		this.evaluatorName = evaluatorName;
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

	public Integer getTechnicalRating() {
		return technicalRating;
	}

	public void setTechnicalRating(Integer technicalRating) {
		this.technicalRating = technicalRating;
	}

	public Integer getPersonalRating() {
		return personalRating;
	}

	public void setPersonalRating(Integer personalRating) {
		this.personalRating = personalRating;
	}

	public String getInterviewType() {
		return interviewType;
	}

	public void setInterviewType(String interviewType) {
		this.interviewType = interviewType;
	}

}
