package com.adwise.chatbot.dto // You might want to create a 'dto' package

data class ContentRequestDTO(
    // Using var because Spring's @ModelAttribute needs mutable properties for binding
    var name: String = "",
    var contactNumber: String = "",
    var format: String = "" // This will hold the selected format from the form
)