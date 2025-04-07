package com.example.demo.controller

import com.example.demo.model.request.PresentRequest
import com.example.demo.service.PresentService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("presents")
class PresentController(
    private val service: PresentService
) {
    @PutMapping("/{id}")
    fun updatePresent(@PathVariable id: Long, request: PresentRequest) = service
        .update(id, request)

    @DeleteMapping("/{id}")
    fun deletePresent(@PathVariable id: Long) = service.delete(id)
}