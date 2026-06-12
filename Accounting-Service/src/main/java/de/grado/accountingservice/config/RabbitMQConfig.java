package de.grado.accountingservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig
{

    public static final String ACCOUNTING_QUEUE = "accounting.queue";
    public static final String ACCOUNTING_EXCHANGE = "accounting.exchange";
    public static final String ACCOUNTING_ROUTING_KEY = "accounting.routing.key";

    @Bean
    public Queue accountingQueue()
    {
        return QueueBuilder.durable(ACCOUNTING_QUEUE).build();
    }

    @Bean
    public DirectExchange accountingExchange()
    {
        return new DirectExchange(ACCOUNTING_EXCHANGE);
    }

    @Bean
    public Binding accountingBinding()
    {
        return BindingBuilder
                .bind(accountingQueue())
                .to(accountingExchange())
                .with(ACCOUNTING_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter()
    {
        return new SimpleMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter)
    {

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}