package com.example.demo.controller

import com.example.demo.model.request.TagRequest
import com.example.demo.model.response.TagResponse
import com.example.demo.service.TagService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("tags")
class TagController(
    private val service: TagService
) {

    @GetMapping("/")
    fun getAll():List<TagResponse> = service.getAll()

    @PostMapping("/")
    fun create(@RequestBody request: TagRequest): TagResponse = service.create(request)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: TagRequest): TagResponse =
        service.update(id, request)

    @DeleteMapping("{id}")
    fun delete (@PathVariable id: Long): String = service.delete(id)
}