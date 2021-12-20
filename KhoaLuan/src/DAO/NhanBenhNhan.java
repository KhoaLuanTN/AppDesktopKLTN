package DAO;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;

import javax.jms.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Entity.LichHen;

public class NhanBenhNhan {

	 private final static String WIRE_LEVEL_ENDPOINT 
	 ="ssl://b-0a568e8a-e103-43cc-a7f7-6725f8bbbf72-1.mq.us-east-2.amazonaws.com:61617";
	 private final static String ACTIVE_MQ_USERNAME = "admin";
	 private final static String ACTIVE_MQ_PASSWORD = "admin20212021";

	 

	public LichHen  receiveMessage(String myqueue) throws JMSException {
		
		LichHen lh=new LichHen();
		 
		final ActiveMQConnectionFactory connectionFactory =createActiveMQConnectionFactory();
		final PooledConnectionFactory pooledConnectionFactory = createPooledConnectionFactory(connectionFactory);

		// Establish a connection for the consumer.
		 // Note: Consumers should not use PooledConnectionFactory.
		 final Connection consumerConnection = connectionFactory.createConnection();
		 consumerConnection.start();
		
		 // Create a session.
		 final Session consumerSession = consumerConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		 // Create a queue named "MyQueue".
		 final Destination consumerDestination = consumerSession.createQueue(myqueue);
		
		 // Create a message consumer from the session to the queue.
		 final MessageConsumer consumer = consumerSession.createConsumer(consumerDestination);
		
		 // Begin to wait for messages.
		 final Message consumerMessage = consumer.receive(1000);
		
		 // Receive the message when it arrives.
		 final TextMessage consumerTextMessage = (TextMessage) consumerMessage;
		 System.out.println("Message received: " + consumerTextMessage.getText());
		 
		if(consumerTextMessage.getText()!=null) {
			 Gson gson = new GsonBuilder()
		     		    .setDateFormat("yyyy-MM-dd")
		     		    .create();
				 
					lh = gson.fromJson(consumerTextMessage.getText(), LichHen.class);
		}
		else {
			lh=null;
		}
		
		 // Clean up the consumer.
			
		 consumer.close();
		 consumerSession.close();
		 consumerConnection.close();
		 pooledConnectionFactory.stop();
		 
		 return lh;
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
