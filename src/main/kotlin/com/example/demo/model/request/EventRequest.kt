package com.example.demo.model.request

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate


data class EventRequest(
    val name: String,
    val description: String,
    @JsonFormat(pattern = "dd-MM-yyyy")
    val time: LocalDate,
    val picture: String,
    val tagId: Long,
    val contactIds: List<Long>?,
    val notifications: List<NotificationRequest> = listOf()
)
