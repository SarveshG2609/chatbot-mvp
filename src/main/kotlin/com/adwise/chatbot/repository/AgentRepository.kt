package com.adwise.chatbot.repository

import com.adwise.chatbot.model.Agent
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface AgentRepository : MongoRepository<Agent, String> {
    fun findByName(name: String): Optional<Agent>
}