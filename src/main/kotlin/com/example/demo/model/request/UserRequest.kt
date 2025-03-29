package com.example.demo.model.request

data class UserRequest (
    val name: String,
    val email: String,
    val picture: String,
    val password: String,
    val friends: List<Long>,
    val events: List<Long>
)