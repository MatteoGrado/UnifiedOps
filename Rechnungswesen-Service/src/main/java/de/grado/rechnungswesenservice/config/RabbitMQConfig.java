package de.grado.rechnungswesenservice.config;

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

    public static final String RECHNUNGSWESEN_QUEUE = "rechnungswesen.queue";
    public static final String RECHNUNGSWESEN_EXCHANGE = "rechnungswesen.exchange";
    public static final String RECHNUNGSWESEN_ROUTING_KEY = "rechnungswesen.routing.key";

    @Bean
    public Queue rechnungswesenQueue() {
        return QueueBuilder.durable(RECHNUNGSWESEN_QUEUE).build();
    }

    @Bean
    public DirectExchange rechnungswesenExchange() {
        return new DirectExchange(RECHNUNGSWESEN_EXCHANGE);
    }

    @Bean
    public Binding rechnungswesenBinding() {
        return BindingBuilder
                .bind(rechnungswesenQueue())
                .to(rechnungswesenExchange())
                .with(RECHNUNGSWESEN_ROUTING_KEY);
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
