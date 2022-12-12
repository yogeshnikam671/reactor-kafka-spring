package com.example.reactorkafkaspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReactorKafkaSpringApplication

fun main(args: Array<String>) {
    runApplication<ReactorKafkaSpringApplication>(*args)
}
