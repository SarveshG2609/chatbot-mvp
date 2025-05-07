package com.adwise.chatbot.controller

import com.adwise.chatbot.model.Agent
import com.adwise.chatbot.repository.AgentRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import com.fasterxml.jackson.databind.ObjectMapper
import java.time.Instant

@SpringBootTest
@AutoConfigureMockMvc
class AgentControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var agentRepository: AgentRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `test create and get agent`() {
        val agent = Agent(
            agentId = "test123",
            name = "Test Agent",
            contactNumber = "1234567890",
            createdAt = Instant.now(),
            updatedAt = Instant.now()
        )

        // Create agent
        mockMvc.perform(
            post("/api/agents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(agent))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.agentId").value(agent.agentId))
            .andExpect(jsonPath("$.name").value(agent.name))

        // Get agent
        mockMvc.perform(get("/api/agents/${agent.agentId}"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.agentId").value(agent.agentId))
            .andExpect(jsonPath("$.name").value(agent.name))
    }
} 