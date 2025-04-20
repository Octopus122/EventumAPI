package com.example.demo.controller

import com.example.demo.model.request.EventRequest
import com.example.demo.model.request.EventUpdateRequest
import com.example.demo.model.request.NotificationRequest
import com.example.demo.model.response.EventResponse
import com.example.demo.service.EventService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("events")
class EventController(
    private val service: EventService
) {
    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): EventResponse = service.getById(id)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: EventRequest) = service.update(id, request)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)

    // @PostMapping("/{id}/notifications")
    // fun createNotification(@PathVariable id: Long, @RequestBody notificationRequest: NotificationRequest) = service
    //    .createNotification(id, notificationRequest)

    // @GetMapping("/{id}/notifications") without remote notification system for now
    // fun getNotifications(@PathVariable id: Long) = service.getNotifications(id)

    @PutMapping("/{id}/contacts/{contactId}")
    fun addContact(@PathVariable id: Long, @PathVariable contactId: Long) = service.addContact(id, contactId)

    @DeleteMapping("/{id}/contacts/{contactId}")
    fun removeContact(@PathVariable id: Long, @PathVariable contactId: Long) = service.removeContact(id, contactId)
}