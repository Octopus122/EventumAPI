package com.example.demo.util.mapper

import com.example.demo.database.entity.Event
import com.example.demo.database.entity.Notification
import com.example.demo.model.request.EventRequest
import com.example.demo.model.request.NotificationRequest
import com.example.demo.model.request.NotificationUpdateRequest
import com.example.demo.model.response.NotificationResponse
import org.springframework.stereotype.Component

@Component
class NotificationMapper {
    fun entityToResponse(entity: Notification) = NotificationResponse(
        entity.id,
        entity.name,
        entity.description,
        entity.time
    )
    fun createRequestToEntity(request: NotificationRequest, event: Event) = Notification(
        name = request.name,
        description = request.description,
        time = request.time,
        event = event
    )
    fun updateRequestToEntity(entity: Notification, request: NotificationUpdateRequest) = entity.apply {
        name = request.name
        description = request.description
        time = request.time
    }
}