package com.example.demo.controller

import com.example.demo.model.request.CustomGiftRequest
import com.example.demo.model.response.GiftResponse
import com.example.demo.service.GiftService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("gifts")
class GiftController(
    private val service: GiftService
) {
    @PutMapping("/{id}")
    fun updateCustom(@PathVariable id: Long, @RequestBody request: CustomGiftRequest): GiftResponse = service
        .updateCustom(id, request)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = service.delete(id)
}