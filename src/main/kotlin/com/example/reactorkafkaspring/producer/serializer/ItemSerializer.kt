package com.example.reactorkafkaspring.producer.serializer

import com.example.reactorkafkaspring.model.Item
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.kafka.common.serialization.Serializer

class ItemSerializer : Serializer<Item> {
    override fun serialize(topic: String?, data: Item): ByteArray {
        return jacksonObjectMapper().writeValueAsBytes(data)
    }
}