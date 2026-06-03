package de.grado.cdsservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String CDS_QUEUE = "cds.queue";
    public static final String CDS_EXCHANGE = "cds.exchange";
    public static final String CDS_ROUTING_KEY = "cds.routing.key";

    @Bean
    public Queue cdsQueue() {
        return QueueBuilder.durable(CDS_QUEUE).build();
    }

    @Bean
    public DirectExchange cdsExchange() {
        return new DirectExchange(CDS_EXCHANGE);
    }

    @Bean
    public Binding cdsBinding() {
        return BindingBuilder
                .bind(cdsQueue())
                .to(cdsExchange())
                .with(CDS_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new SimpleMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         MessageConverter messageConverter) {

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }
}
