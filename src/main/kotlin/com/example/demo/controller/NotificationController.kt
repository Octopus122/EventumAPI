package com.example.demo.controller

import com.example.demo.model.request.NotificationUpdateRequest
import com.example.demo.service.NotificationService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("notifications")
class NotificationController(
    private val service: NotificationService
) {
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: NotificationUpdateRequest) = service.update(id, request)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)
}