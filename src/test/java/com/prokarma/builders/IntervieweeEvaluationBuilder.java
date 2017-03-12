package com.prokarma.builders;

import org.apache.commons.lang3.builder.Builder;

import com.prokarma.model.IntervieweeEvaluation;
import com.prokarma.model.IntervieweeEvaluationId;

public class IntervieweeEvaluationBuilder implements Builder<IntervieweeEvaluation> {

	private String evaluatorName = "evaluator1";
	private Integer skillId = 1001;
	private Integer technicalRating = 4;
	private Integer personalRating = 4;
	private String interviewType = "face to face";
	private IntervieweeEvaluationId intervieweeEvaluationId;

	public IntervieweeEvaluation build() {
		IntervieweeEvaluation intervieweeEvaluation = new IntervieweeEvaluation();
		intervieweeEvaluation.setEvaluatorName(evaluatorName);
		intervieweeEvaluation.setInterviewType(interviewType);
		intervieweeEvaluation.setPersonalRating(personalRating);
		intervieweeEvaluation.setSkillId(skillId);
		intervieweeEvaluation.setTechnicalRating(technicalRating);
		intervieweeEvaluation.setIntervieweeEvaluationId(intervieweeEvaluationId);
		return intervieweeEvaluation;
	}

	public IntervieweeEvaluationBuilder withEvaluatorName(String evaluatorName) {
		this.evaluatorName = evaluatorName;
		return this;
	}

	public IntervieweeEvaluationBuilder withIntervieweeEvaluationId(IntervieweeEvaluationId intervieweeEvaluationId) {
		this.intervieweeEvaluationId = intervieweeEvaluationId;
		return this;
	}

}
