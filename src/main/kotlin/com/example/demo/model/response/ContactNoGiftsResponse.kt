package com.example.demo.model.response

data class ContactNoGiftsResponse (
    val id: Long,
    val name: String,
    val hostUserId: Long,
    val friendUser: UserContactResponse?
)