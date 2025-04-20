package com.example.demo.model.response

data class ContactResponse (
    val id: Long,
    val name: String,
    val hostUserId: Long,
    val friendUser: UserContactResponse?,
    val giftsIds: List<Long>
)
