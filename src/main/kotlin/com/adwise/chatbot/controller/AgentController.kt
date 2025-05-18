package com.adwise.chatbot.controller

import com.adwise.chatbot.model.Agent
import com.adwise.chatbot.repository.AgentRepository
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import com.adwise.chatbot.service.AgentService

@RestController
@RequestMapping("/api/agents")
class AgentController(
    private val agentRepository: AgentRepository,
    private val agentService: AgentService
) {

    private val logger = LoggerFactory.getLogger(AgentController::class.java)

    @PostMapping
    fun createAgent(@RequestBody agent: Agent): ResponseEntity<Any> {
        return try {
            logger.info("Attempting to create agent: $agent")
            val savedAgent = agentRepository.save(agent)
            logger.info("Successfully created agent with ID: ${savedAgent.agentId}")
            ResponseEntity.ok(savedAgent)
        } catch (e: Exception) {
            logger.error("Error creating agent: ${e.message}", e)
            ResponseEntity.status(500).body(mapOf(
                "error" to "Failed to create agent",
                "message" to e.message
            ))
        }
    }

    @GetMapping("/{agentId}")
    fun getAgent(@PathVariable agentId: String): ResponseEntity<Any> {
        return try {
            logger.info("Attempting to fetch agent with ID: $agentId")
            agentRepository.findById(agentId)
                .map { 
                    logger.info("Successfully found agent: $it")
                    ResponseEntity.ok(it) as ResponseEntity<Any>
                }
                .orElseGet {
                    logger.warn("Agent not found with ID: $agentId")
                    ResponseEntity.notFound().build()
                }
        } catch (e: Exception) {
            logger.error("Error fetching agent: ${e.message}", e)
            ResponseEntity.status(500).body(mapOf(
                "error" to "Failed to fetch agent",
                "message" to e.message
            ))
        }
    }

    @GetMapping
    fun getAllAgents(): ResponseEntity<Any> {
        return try {
            val agents = agentService.getAllAgents()
            ResponseEntity.ok(agents)
        } catch (e: Exception) {
            logger.error("Error fetching all agents: ${e.message}", e)
            ResponseEntity.status(500).body(mapOf(
                "error" to "Failed to fetch all agents",
                "message" to e.message
            ))
        }
    }
} 