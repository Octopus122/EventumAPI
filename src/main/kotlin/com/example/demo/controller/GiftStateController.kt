package com.example.demo.controller

import com.example.demo.model.request.GiftStateRequest
import com.example.demo.model.response.GiftStateResponse
import com.example.demo.service.GiftStateService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("giftstates")
class GiftStateController(
    private val service: GiftStateService
) {
    @GetMapping("/")
    fun getAll():List<GiftStateResponse> = service.getAll()

    @PostMapping("/")
    fun create(@RequestBody request: GiftStateRequest): GiftStateResponse = service.create(request)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: GiftStateRequest): GiftStateResponse =
        service.update(id, request)

    @DeleteMapping("'{id}")
    fun delete (@PathVariable id: Long): String = service.delete(id)
}