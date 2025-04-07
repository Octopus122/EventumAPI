package com.example.demo.service

import com.example.demo.database.entity.Event
import com.example.demo.database.entity.User
import com.example.demo.model.request.EventRequest
import com.example.demo.model.request.EventUpdateRequest
import com.example.demo.model.request.NotificationRequest
import com.example.demo.model.response.EventResponse
import com.example.demo.model.response.NotificationResponse

interface EventService {
    fun create(request: EventRequest, hostUser: User): EventResponse
    fun update(id: Long, request: EventUpdateRequest): EventResponse
    fun getById(id: Long): Event
    fun getAll(): List<EventResponse>
    fun delete(id: Long): String

    fun createNotification(id: Long, notificationRequest: NotificationRequest): NotificationResponse
    fun getNotifications(id: Long): List<NotificationResponse>

    fun addContact(id: Long, contactId: Long): EventResponse
    fun removeContact(id: Long, contactId: Long): EventResponse
}