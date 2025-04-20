package com.example.demo.model.request

import java.time.LocalDateTime

data class NotificationRequest(
    val name: String,
    val description: String,
    val time: LocalDateTime
)
