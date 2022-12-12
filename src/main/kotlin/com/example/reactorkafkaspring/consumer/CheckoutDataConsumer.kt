package com.example.reactorkafkaspring.consumer

import com.example.reactorkafkaspring.consumer.deserializer.ItemDeserializer
import com.example.reactorkafkaspring.model.Item
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.IntegerDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.kafka.receiver.KafkaReceiver
import reactor.kafka.receiver.ReceiverOptions
import reactor.kafka.receiver.internals.ConsumerFactory
import javax.annotation.PostConstruct

@Component
class CheckoutDataConsumer(
    @Value("\${app.kafka.bootstrap-server}") val bootstrapServer: String,
    @Value("\${app.kafka.checkout-topic}") val topic: String
) {
    private val receiverOptions: ReceiverOptions<Int, Item> = ReceiverOptions.create<Int?, Item?>(
        mapOf(
            ConsumerConfig.CLIENT_ID_CONFIG to "checkoutId",
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServer,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to IntegerDeserializer::class.java,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to ItemDeserializer::class.java,
            ConsumerConfig.GROUP_ID_CONFIG to "checkoutGroupId"
        )
    ).subscription(listOf(topic))

    @PostConstruct
    fun startConsumption() {
        println("Started consuming from the topic :$topic...")
        KafkaReceiver.create(receiverOptions)
            .receive()
            .map {
                println("Shipped the checked out item : ${it.value()}")
                it.value()
            }.subscribe()
    }
}