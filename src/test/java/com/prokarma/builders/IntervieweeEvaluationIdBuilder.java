package com.prokarma.builders;

import org.apache.commons.lang3.builder.Builder;

import com.prokarma.model.IntervieweeEvaluationId;

public class IntervieweeEvaluationIdBuilder implements Builder<IntervieweeEvaluationId> {

	public Integer evaluatorId = 3423;
	public Integer intervieweeId = 5323;
	public Integer interviewRound = 1;

	public IntervieweeEvaluationId build() {
		IntervieweeEvaluationId intervieweeEvaluationId = new IntervieweeEvaluationId();
		intervieweeEvaluationId.setEvaluatorId(evaluatorId);
		intervieweeEvaluationId.setIntervieweeId(intervieweeId);
		intervieweeEvaluationId.setInterviewRound(interviewRound);
		return intervieweeEvaluationId;
	}
	
	public IntervieweeEvaluationIdBuilder withEvaluatorId(Integer evaluatorId){
		this.evaluatorId = evaluatorId;
		return this;
	}
	
	public IntervieweeEvaluationIdBuilder withIntervieweeId(Integer intervieweeId){
		this.intervieweeId = intervieweeId;
		return this;
	}
	
	public IntervieweeEvaluationIdBuilder withInterviewRound(Integer interviewRound){
		this.interviewRound = interviewRound;
		return this;
	}

}
