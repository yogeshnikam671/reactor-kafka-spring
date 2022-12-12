package com.example.reactorkafkaspring.service

import com.example.reactorkafkaspring.model.Item
import com.example.reactorkafkaspring.producer.CheckoutDataProducer
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CheckoutService(
    private val checkoutDataProducer: CheckoutDataProducer
) {

    fun checkout(item: Item): Mono<String> {
        println("Checking out the item : $item")
        return checkoutDataProducer.produce(item).map {
            "Checked out item : ${item.name} successfully! \n Shipping in progress..."
        }
    }
}