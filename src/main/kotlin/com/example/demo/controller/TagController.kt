package com.example.demo.controller

import com.example.demo.model.request.TagRequest
import com.example.demo.service.TagService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("tags")
class TagController(
    private val service: TagService
) {
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: TagRequest) = service.update(id, request)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)
}