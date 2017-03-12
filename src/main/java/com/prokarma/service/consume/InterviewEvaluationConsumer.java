package com.prokarma.service.consume;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.prokarma.model.IntervieweeEvaluation;
import com.prokarma.service.publish.IntervieweeEvaluationPublisher;
import com.prokarma.service.xml.XmlPreparator;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.QueueingConsumer;

@Component
public class InterviewEvaluationConsumer {

	private static final Logger logger = LoggerFactory.getLogger(IntervieweeEvaluationPublisher.class);

	@Value("${rabbitmq.queue.name}")
	private String queueName;

	@Autowired
	private ConnectionFactory connectionFactory;
	
	@Autowired
	private XmlPreparator xmlPreparator;

	public List<IntervieweeEvaluation> consumeXmls() {
		logger.debug("consuming xml to rabbitmq");
		List<IntervieweeEvaluation> intervieweeEvaluations = null;
		try {
			Connection connection = connectionFactory.newConnection();
			Channel channel = connection.createChannel();
			channel.queueDeclare(queueName, false, false, false, null);

			channel.basicQos(1);

			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(queueName, false, consumer);

			intervieweeEvaluations = consumeXmlsAndPrepareIntervieweeEvaluations(channel, consumer);
		} catch (Exception e) {
			logger.error("Exception occured while consuming xml from queue {} , {}", queueName, e);
		}
		return intervieweeEvaluations;
	}

	private List<IntervieweeEvaluation> consumeXmlsAndPrepareIntervieweeEvaluations(Channel channel,
			QueueingConsumer consumer) throws InterruptedException, IOException {
		List<IntervieweeEvaluation> intervieweeEvaluations = Lists.newArrayList();
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery(1000);
			if(Optional.fromNullable(delivery).isPresent()) {
				String xml = new String(delivery.getBody());
				logger.debug("preparing interviewee evaluation object from xml {}",xml);
				intervieweeEvaluations.add(xmlPreparator.prepareIntervieweeEvaluation(xml));
				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			} else {
				break;
			}
		}
		return intervieweeEvaluations;
	}

}
