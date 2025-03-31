package com.example.demo.controller

import com.example.demo.database.entity.User
import com.example.demo.model.request.UserRegisterRequest
import com.example.demo.model.response.UserResponse
import com.example.demo.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController(
    private val service: UserService
) {
    @PostMapping("/")
    fun createUser(@RequestBody request: UserRegisterRequest): UserResponse = service.create(request)

    @GetMapping("/")
    fun getAll(): List<UserResponse> = service.getAll()
}