package com.adwise.chatbot.repository

import com.adwise.chatbot.model.Agent
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AgentRepository : MongoRepository<Agent, String>