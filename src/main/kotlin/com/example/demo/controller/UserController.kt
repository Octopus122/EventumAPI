package com.example.demo.controller

import com.example.demo.database.entity.User
import com.example.demo.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController(
    private val service: UserService
) {
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): User
    {
        return service.getById(id)
    }
}