package com.example.demo.service

import com.example.demo.database.entity.User
import com.example.demo.model.request.UserRegisterRequest
import com.example.demo.model.request.UserRequest
import com.example.demo.model.response.*
import org.springframework.stereotype.Service

interface UserService {
    fun getEntityById(id: Long): User
    fun getByEmail(email: String): UserResponse
    fun getByName(name: String): UserContactResponse
    fun getAll(): List<UserResponse>
    fun create(request: UserRegisterRequest): UserResponse
    fun update(id: Long, user: UserRequest): UserResponse
    fun getContacts(id: Long): List<ContactResponse>
    fun getEvents(id: Long): List<EventResponse>
    fun getWishList(id: Long): WishListResponse
    fun delete(id: Long): String
//    fun getByIds(ids: List<Long>): List<User>
}