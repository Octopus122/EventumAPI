package com.example.demo.service

import com.example.demo.database.entity.Event
import com.example.demo.database.entity.User
import com.example.demo.model.request.EventRequest
import com.example.demo.model.request.EventUpdateRequest
import com.example.demo.model.request.NotificationRequest
import com.example.demo.model.response.ContactResponse
import com.example.demo.model.response.EventResponse

interface EventService {
    fun create(request: EventRequest, hostUser: User): EventResponse
    fun update(id: Long, request: EventRequest): EventResponse
    fun getEntity(id: Long): Event
    fun getById(id: Long): EventResponse
    fun getAll(): List<EventResponse>
    fun delete(id: Long): String


    fun addContact(id: Long, contactId: Long): EventResponse
    fun getContacts(id: Long): List<ContactResponse>
    fun removeContact(id: Long, contactId: Long): EventResponse
}