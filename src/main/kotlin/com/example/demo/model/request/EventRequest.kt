package com.example.demo.model.request

import java.time.LocalDateTime


data class EventRequest(
    val name: String,
    val description: String,
    val time: LocalDateTime,
    val picture: String,
    val tagId: Long,
    val userId: Long,
    val contactIds: List<Long>,
    val notifications: List<NotificationRequest>
)
