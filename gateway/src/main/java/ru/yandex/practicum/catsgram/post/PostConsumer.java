package ru.yandex.practicum.catsgram.post;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostConsumer {
    private final PulsarClient client;
    private final PostMessageListener messageListener;
    private Consumer<String> consumer;

    @PostConstruct
    private void postConstruct() throws PulsarClientException {
        this.consumer = client.newConsumer(Schema.STRING)
                .topic("my-topic")
                .subscriptionName("my-subscription")
                .subscriptionType(SubscriptionType.Shared)
                .messageListener(messageListener)
                .subscribe();
    }

    @PreDestroy
    public void preDestroy() throws PulsarClientException {
        consumer.close();
    }
}
