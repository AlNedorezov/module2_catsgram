package ru.yandex.practicum.catsgram.post;

import jakarta.annotation.PreDestroy;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostProducer {
    Producer<String> stringProducer;

    @Autowired
    public PostProducer(PulsarClient client) throws PulsarClientException {
        this.stringProducer = client.newProducer(Schema.STRING)
                .topic("my-topic")
                .create();
    }

    public void sendPostEvent(PostDto postDto) throws PulsarClientException {
        stringProducer.send("Created a new post with author=" + postDto.getAuthor());
    }

    @PreDestroy
    public void onPreDestroy() throws PulsarClientException {
        stringProducer.close();
    }
}
