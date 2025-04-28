package com.example.demo.controller

import com.example.demo.model.request.UserLoginRequest
import com.example.demo.model.request.UserRegisterRequest
import com.example.demo.model.response.UserResponse
import com.example.demo.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class LoginController(
    private val service: UserService
) {
    @PostMapping("/login")
    fun login(@RequestBody request: UserLoginRequest): Result<UserResponse> = service.login(request)

    @PostMapping("/register")
    fun requester(@RequestBody request: UserRegisterRequest) = service.create(request)
}