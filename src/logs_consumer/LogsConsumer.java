package logs_consumer;
import com.rabbitmq.client.*;
import java.io.IOException;

public class LogsConsumer {
  private static final String LOGS_QUEUE = "logs";

  public static void main(String[] argv) throws Exception {
	ConnectionFactory factory = new ConnectionFactory();
	factory.setHost("fox.rmq.cloudamqp.com");
	factory.setUsername("xhffrluv");
	factory.setPassword("zIU2WWJtvjsDUv_BrwCoU60RWcekbpvP");
	factory.setVirtualHost("xhffrluv");
    final Connection connection = factory.newConnection();
    final Channel channel = connection.createChannel();
    channel.queueDeclare(LOGS_QUEUE, true, false, false, null);

    final Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        try {
        	System.out.println(new String(body, "UTF-8"));
        } finally {
            channel.basicAck(envelope.getDeliveryTag(), false);
        }
      }
    };

    channel.basicConsume(LOGS_QUEUE, false, consumer);
  }
}
  
