package com.example.demo.service

import com.example.demo.database.entity.Event
import com.example.demo.database.entity.Notification
import com.example.demo.model.request.NotificationRequest
import com.example.demo.model.request.NotificationUpdateRequest
import com.example.demo.model.response.NotificationResponse

interface NotificationService {
    fun create(request: NotificationRequest, event: Event): NotificationResponse
    fun update(id: Long, request: NotificationUpdateRequest): NotificationResponse
    fun getById(id: Long): NotificationResponse
    fun getAll(): List<NotificationResponse>
    fun delete(id: Long): String
}