package com.adwise.chatbot.controller

import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.bson.Document

@RestController
@RequestMapping("/api/health")
class HealthCheckController(private val mongoTemplate: MongoTemplate) {

    @GetMapping("/mongodb")
    fun checkMongoDBConnection(): ResponseEntity<Map<String, Any>> {
        return try {
            // Try to execute a simple command to check connection
            mongoTemplate.db.runCommand(Document("ping", 1))
            ResponseEntity.ok(mapOf(
                "status" to "UP",
                "message" to "Successfully connected to MongoDB"
            ))
        } catch (e: Exception) {
            ResponseEntity.status(503).body(mapOf(
                "status" to "DOWN",
                "message" to "Failed to connect to MongoDB: ${e.message}"
            ))
        }
    }

    @GetMapping("/")
    fun checkHealth(): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.ok(mapOf(
            "status" to "UP",
            "message" to "Chatbot service is running"
        ))
    }
} 