package com.prokarma.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prokarma.model.IntervieweeEvaluation;

@Repository
public class IntervieweeEvaluationDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<IntervieweeEvaluation> fetchIntervieweeEvaluations(Integer intervieweeId) {
		Session session = sessionFactory.getCurrentSession();
		List<IntervieweeEvaluation> intervieweeEvaluations = session
				.createQuery(String.format("from IntervieweeEvaluation where intervieweeid=%d", intervieweeId)).list();
		//in above query, table name INTERVIEWEEEVALUATION should not be passed. IntervieweeEvaluation is java class name 
		return intervieweeEvaluations;
	}

	public void save(List<IntervieweeEvaluation> intervieweeEvaluations) {
		Session session = sessionFactory.getCurrentSession();
		for(IntervieweeEvaluation entry : intervieweeEvaluations) {
			session.saveOrUpdate(entry);
		}
		
	}

}
