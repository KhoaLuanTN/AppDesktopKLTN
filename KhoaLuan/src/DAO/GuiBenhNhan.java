package DAO;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;


import javax.jms.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Entity.LichHen;

public class GuiBenhNhan {

	 private final static String WIRE_LEVEL_ENDPOINT
	 =  "ssl://b-0a568e8a-e103-43cc-a7f7-6725f8bbbf72-1.mq.us-east-2.amazonaws.com:61617";
	 private final static String ACTIVE_MQ_USERNAME = "admin";
	 private final static String ACTIVE_MQ_PASSWORD = "admin20212021";
	


	public  void sendMessage(LichHen lh, String myqueue) throws JMSException {
		 
		final ActiveMQConnectionFactory connectionFactory =
		         createActiveMQConnectionFactory();
				 final PooledConnectionFactory pooledConnectionFactory = createPooledConnectionFactory(connectionFactory);

		// Establish a connection for the producer.
		 final Connection producerConnection = pooledConnectionFactory.createConnection();
		 producerConnection.start();
		
		 // Create a session.
		 final Session producerSession = producerConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		 
		
		 
		 final Destination producerDestination = producerSession.createQueue(myqueue);
		
		 // Create a producer from the session to the queue.
		 final MessageProducer producer = producerSession.createProducer(producerDestination);
		 producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		 
		 Gson gson = new GsonBuilder()
	    		    .setDateFormat("yyyy-MM-dd")
	    		    .create();
			String bntext = gson.toJson(lh);
		 // Create a message.
		 
		 final TextMessage producerMessage = producerSession.createTextMessage(bntext);
		
		 // Send the message.
		 producer.send(producerMessage);
		 System.out.println("Message sent.");
		
		 // Clean up the producer.
		 producer.close();
		 producerSession.close();
		 producerConnection.close();
		 pooledConnectionFactory.stop();
	}

	
	private static PooledConnectionFactory createPooledConnectionFactory(ActiveMQConnectionFactory connectionFactory) {
	 // Create a pooled connection factory.
		 final PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
		 pooledConnectionFactory.setConnectionFactory(connectionFactory);
		 pooledConnectionFactory.setMaxConnections(10);
		return pooledConnectionFactory;
	}
	
	private static ActiveMQConnectionFactory createActiveMQConnectionFactory() {
		 // Create a connection factory.
		 final ActiveMQConnectionFactory connectionFactory =
		         new ActiveMQConnectionFactory(WIRE_LEVEL_ENDPOINT);
		
		 // Pass the username and password.
		 connectionFactory.setUserName(ACTIVE_MQ_USERNAME);
		 connectionFactory.setPassword(ACTIVE_MQ_PASSWORD);
	 return connectionFactory;
	}
		
}

