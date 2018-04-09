package upaiyun;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class RabbitInJava {

	public static void main(String[] args) throws InterruptedException, IOException, TimeoutException {

		com.rabbitmq.client.ConnectionFactory rabbitConnectionFactory = new com.rabbitmq.client.ConnectionFactory();
		rabbitConnectionFactory.setUsername("admin");
		rabbitConnectionFactory.setPassword("admin");
		rabbitConnectionFactory.setHost("192.168.92.130");
		rabbitConnectionFactory.setPort(5672);
		rabbitConnectionFactory.setVirtualHost("/");

		ConnectionFactory cf = new CachingConnectionFactory(rabbitConnectionFactory);

		// set up the queue, exchange, binding on the broker
		RabbitAdmin admin = new RabbitAdmin(cf);

		Queue queue = new Queue("myQueue");
		admin.declareQueue(queue);

		TopicExchange exchange = new TopicExchange("myExchange");
		admin.declareExchange(exchange);

		admin.declareBinding(BindingBuilder.bind(queue).to(exchange).with("foo.*"));

		// set up the listener and container
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cf);
		Object listener = new Object() {
			public void handleMessage(String foo) {
				System.out.println(foo + 2);
			}
		};
		MessageListenerAdapter adapter = new MessageListenerAdapter(listener);
		container.setMessageListener(adapter);
		container.setQueueNames("myQueue");
		container.start();

		// send something
		RabbitTemplate template = new RabbitTemplate(cf);
		template.convertAndSend("myExchange", "foo.bar", "Hello, world!");
		Thread.sleep(1000);
		container.stop();

	}

}
