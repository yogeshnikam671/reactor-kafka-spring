package com.example.reactorkafkaspring.consumer.deserializer

import com.example.reactorkafkaspring.model.Item
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.kafka.common.serialization.Deserializer

class ItemDeserializer: Deserializer<Item> {
    override fun deserialize(topic: String, data: ByteArray): Item {
        return jacksonObjectMapper().readValue(data, Item::class.java)
    }
}