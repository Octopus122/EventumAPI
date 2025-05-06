package com.example.demo.model.response

import com.example.demo.model.request.NotificationRequest
import java.time.LocalDate

data class EventResponse (
    val id: Long,
    val name: String,
    val description: String,
    val time: String, // yyyy-MM-dd format
    val picture: String,
    val tag: Long,
    val userId: Long,
)
