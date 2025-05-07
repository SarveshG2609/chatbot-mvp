package com.adwise.chatbot.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.UUID

@Document(collection = "agents")
data class Agent(
    @Id
    val agentId: String = UUID.randomUUID().toString(),
    val name: String,
    val contactNumber: String,
    val createdAt: Instant = Instant.now(),
    val updatedAt: Instant = Instant.now()
) 