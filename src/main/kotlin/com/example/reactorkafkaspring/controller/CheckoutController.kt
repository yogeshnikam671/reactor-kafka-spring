package com.example.reactorkafkaspring.controller

import com.example.reactorkafkaspring.model.Item
import com.example.reactorkafkaspring.service.CheckoutService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class CheckoutController(
    private val checkoutService: CheckoutService
) {

    @PostMapping("/checkout")
    fun checkout(
        @RequestBody item: Item
    ): Mono<String> {
        return checkoutService.checkout(item)
    }
}