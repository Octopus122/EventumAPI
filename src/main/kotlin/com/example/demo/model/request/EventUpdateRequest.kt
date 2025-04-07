package com.example.demo.model.request

import java.time.LocalDateTime

data class EventUpdateRequest(
    val name: String,
    val description: String,
    val time: LocalDateTime,
    val picture: String,
    val tagId: Long,
    val contactIds: List<Long>,
    val notifications: List<NotificationRequest>
)
