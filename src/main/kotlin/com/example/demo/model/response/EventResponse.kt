package com.example.demo.model.response

import com.example.demo.model.request.NotificationRequest
import java.time.LocalDateTime

data class EventResponse (
    val id: Long,
    val name: String,
    val description: String?,
    val time: LocalDateTime,
    val picture: String,
    val tag: TagResponse?,
    val userId: Long,
    val contacts: List<ContactNoGiftsResponse>,
    val notifications: List<NotificationResponse>
)
