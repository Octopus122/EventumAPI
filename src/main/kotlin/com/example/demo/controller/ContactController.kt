package com.example.demo.controller

import com.example.demo.model.request.ContactRequest
import com.example.demo.model.request.CustomGiftRequest
import com.example.demo.model.request.LinkedGiftRequest
import com.example.demo.model.response.ContactResponse
import com.example.demo.service.ContactService
import com.example.demo.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("contacts")
class ContactController(
    private val service: ContactService,
    private val userService: UserService
) {
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: ContactRequest): ContactResponse = userService
        .updateContact(id, request)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): String = service.delete(id)

    @PostMapping("/{id}/gifts/custom")
    fun createCustomGift(@PathVariable id: Long, @RequestBody request: CustomGiftRequest) = service
        .createCustomGift(id, request)

    @PostMapping("/{id}/gifts/linked")
    fun createLinkedGift(@PathVariable id: Long, @RequestBody request: LinkedGiftRequest) = service
        .createLinkedGift(id, request)

    @GetMapping("/{id}/gifts")
    fun getGifts(@PathVariable id: Long) = service.getGifts(id)
}