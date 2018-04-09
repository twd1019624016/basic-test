package upaiyun;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Demo4 {

	public static void main(String[] args) throws IOException, TimeoutException {

		ConnectionFactory connectionFactory = new ConnectionFactory();

		Connection connection = connectionFactory.newConnection();

		Channel channel = connection.createChannel();

		DeclareOk queueDeclare = channel.queueDeclare("QUEUE_NAME", false, false, false, null);
		String message = "Hello World!";
		channel.basicPublish("", "QUEUE_NAME", null, message.getBytes("UTF-8"));
		System.out.println(" [x] Sent '" + message + "'");

		channel.close();
		connection.close();

		channel.exchangeDeclare("", "direct");
	}
}
