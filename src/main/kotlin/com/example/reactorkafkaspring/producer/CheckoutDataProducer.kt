package com.example.reactorkafkaspring.producer

import com.example.reactorkafkaspring.model.Item
import com.example.reactorkafkaspring.producer.serializer.ItemSerializer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.IntegerSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kafka.sender.KafkaSender
import reactor.kafka.sender.SenderOptions
import reactor.kafka.sender.SenderRecord
import reactor.kafka.sender.SenderResult
import reactor.kafka.sender.internals.ProducerFactory
import java.util.*

@Component
class CheckoutDataProducer(
    @Value("\${app.kafka.bootstrap-server}") val bootstrapServer: String,
    @Value("\${app.kafka.checkout-topic}") val topic: String
) {
    private val senderOptions: SenderOptions<Int, Item> = SenderOptions.create(
        mapOf(
            ProducerConfig.CLIENT_ID_CONFIG to "checkoutId",
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServer,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to IntegerSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to ItemSerializer::class.java
        )
    )

    fun produce(data: Item): Mono<SenderResult<String>> {
        // The UUID is the correlation metadata
        val record = SenderRecord.create(
            ProducerRecord<Int, Item>(topic, data),
            UUID.randomUUID().toString()
        )
        println("Producing the message : $data on topic : $topic")
        return KafkaSender.create(senderOptions).send(Flux.just(record)).next()
    }
}