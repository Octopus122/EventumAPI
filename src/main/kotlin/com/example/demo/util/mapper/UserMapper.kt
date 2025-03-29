package com.example.demo.util.mapper

import com.example.demo.database.entity.User
import com.example.demo.model.request.UserRequest
import com.example.demo.model.response.UserResponse
import com.example.demo.util.cipher.HashPasswordService
import org.springframework.stereotype.Component

@Component
class UserMapper(
    private val passwordService: HashPasswordService
) {
    fun createRequestToEntity(request: UserRequest): User = User(
        name = request.name,
        email = request.email,
        picture = request.picture,
        password = passwordService.hashPassword(request.password)
    )

    fun updateRequestToEntity(entity: User, request: UserRequest) :User = entity.apply {
        this.name = request.name
        this.email = request.email
        this.picture = request.picture
    }

    fun entityToResponse(entity: User): UserResponse = UserResponse(
        entity.id,
        entity.name,
        entity.picture,
        entity.password,
//        entity.contacts,
//        entity.events
    )
}