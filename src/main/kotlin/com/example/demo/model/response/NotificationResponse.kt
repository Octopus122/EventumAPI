package com.example.demo.model.response

import java.time.LocalDateTime

data class NotificationResponse(
    val id: Long,
    val name: String,
    val description: String?,
    val time: LocalDateTime
)
