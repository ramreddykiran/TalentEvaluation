package com.prokarma.service.publish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

@Component
public class IntervieweeEvaluationPublisher {

	private static final Logger logger = LoggerFactory.getLogger(IntervieweeEvaluationPublisher.class);

	@Value("${rabbitmq.queue.name}")
	private String queueName;

	@Autowired
	private ConnectionFactory connectionFactory;

	public void publish(String xml) {
		logger.debug("publishing xml to rabbitmq {}", xml);
		try {
			Connection connection = connectionFactory.newConnection();
			Channel channel = connection.createChannel();
			channel.queueDeclare(queueName, false, false, false, null);
			channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, xml.getBytes());
			channel.close();
			connection.close();
			logger.debug("published successfully");
		} catch (Exception e) {
			logger.error("Exception occured while sending xml to queue {}, {}", queueName, e);
		}
	}

}
