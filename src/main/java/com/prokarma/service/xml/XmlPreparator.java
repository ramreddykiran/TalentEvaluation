package com.prokarma.service.xml;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.prokarma.model.IntervieweeEvaluation;

@Component
public class XmlPreparator {
	
	private static final Logger logger = LoggerFactory.getLogger(XmlPreparator.class);

	@SuppressWarnings("restriction")
	public String prepareXml(IntervieweeEvaluation intervieweeEvaluation) {
		StringWriter stringWriter = new StringWriter();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(IntervieweeEvaluation.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(intervieweeEvaluation, stringWriter);
		} catch (Exception e) {
			logger.error("Unable to parse xml forinterviewee evaluation details {}",e);
		}
		String xml = stringWriter.toString();
		return xml;
	}

	@SuppressWarnings("restriction")
	public IntervieweeEvaluation prepareIntervieweeEvaluation(String xml) {
		IntervieweeEvaluation intervieweeEvaluation = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(IntervieweeEvaluation.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			intervieweeEvaluation = (IntervieweeEvaluation) unmarshaller.unmarshal(new StringReader(xml));
		} catch (Exception e) {
			logger.error("Unable to parse interviewee evaluation details from xml {} due to exception {}",e);
			}
		return intervieweeEvaluation;
	}

}
