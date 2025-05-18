package com.adwise.chatbot.service

import com.adwise.chatbot.model.Agent
import com.adwise.chatbot.repository.AgentRepository
import org.springframework.stereotype.Service

@Service
class AgentService(private val agentRepository: AgentRepository) {
    fun getAgentByName(name: String): Agent {
        return agentRepository.findByName(name).orElseThrow { RuntimeException("Agent not found") }
    }

    fun getAllAgents(): List<Agent> {
        return agentRepository.findAll()
    }

    fun createAgent(agent: Agent): Agent {
        return agentRepository.save(agent)
    }

    fun updateAgent(agent: Agent): Agent {
        return agentRepository.save(agent)
    }
}
