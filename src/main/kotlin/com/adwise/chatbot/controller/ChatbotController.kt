package com.adwise.chatbot.controller // Adjust package according to your project structure

import com.adwise.chatbot.dto.ContentRequestDTO // Adjust package
import com.adwise.chatbot.model.Agent // Adjust package
import com.adwise.chatbot.repository.AgentRepository // Adjust package
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.time.Instant // Needed for setting createdAt/updatedAt
import java.util.UUID // Needed for generating UUID for Agent ID


@Controller
@RequestMapping("/chatbot")
class ChatbotController(private val agentRepository: AgentRepository) {

    @GetMapping // Handle GET requests to /chatbot
    fun showChatbotForm(model: Model): String {
        // Add the DTO object to the model for the form binding
        model.addAttribute("contentRequest", ContentRequestDTO())
        model.addAttribute("formats", listOf("Email", "Phone", "SMS", "Other")) // Add format options
        return "chatbotForm" // Return the name of the Thymeleaf template (chatbotForm.html)
    }

    @PostMapping // Handle POST requests to /chatbot (form submission)
    fun submitChatbotForm(@ModelAttribute contentRequest: ContentRequestDTO): String {
        // Spring automatically binds form data to the ContentRequestDTO object

        // You might want to add validation here before saving

        // Create an Agent object from the data in the DTO
        val agentToSave = Agent(
            // Generate a new UUID for the Agent ID
            id = UUID.randomUUID().toString(),
            name = contentRequest.name,
            contactNumber = contentRequest.contactNumber,
            createdAt = Instant.now(), // Set creation timestamp
            updatedAt = Instant.now()  // Set update timestamp
            // Note: The Agent model does NOT have the 'format' field
        )

        agentRepository.save(agentToSave) // Save the Agent data to MongoDB

        // You might want to process or save the 'format' separately if needed for other purposes,
        // but the Agent model itself won't store it based on your requirement.

        // Redirect after successful submission
        return "redirect:/chatbot?success" // Redirect back to the form URL with a success parameter
    }
}
