package dev.java10x.email.configuration;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMq{

    private final String queueName = "email-queue";

    public Queue queue() {
        return new Queue(queueName, true);
    }

}
