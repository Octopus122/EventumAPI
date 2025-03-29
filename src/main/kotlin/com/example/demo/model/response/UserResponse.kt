package com.example.demo.model.response

data class UserResponse(
    val id: Long,
    val email: String,
    val picture: String,
    val password: String,
//    val confirm_mail: Boolean,
//    val friends: ContactResponse,
//    val events: EventResponse
)
