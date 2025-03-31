package com.example.demo.model.request

data class UserRegisterRequest (
    val name: String,
    val email: String,
    val picture: String,
    val password: String
)