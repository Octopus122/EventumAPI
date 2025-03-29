package com.example.demo.service

import com.example.demo.database.entity.User
import com.example.demo.model.request.UserRequest
import com.example.demo.model.response.UserResponse
import org.springframework.stereotype.Service

interface UserService {
    fun getById(id: Long): User
    fun getByEmail(email: String): User
    fun getAll(): List<UserResponse>
    fun update(id: Long, user: UserRequest): UserResponse
    fun getContacts(id: Long): List<UserResponse>
    fun delete(id: Long): String
    fun create(user: UserRequest): UserResponse
    fun getByIds(ids: List<Long>): List<User>
}