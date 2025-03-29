package com.example.demo.service.impl

import com.example.demo.database.entity.User
import com.example.demo.database.repository.UserDao
import com.example.demo.model.request.UserRequest
import com.example.demo.model.response.UserResponse
import com.example.demo.service.UserService
import com.example.demo.util.mapper.UserMapper
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val dao: UserDao,
    private val mapper: UserMapper,

) : UserService{

    override fun getById(id: Long): User {
        return dao.findById(id).orElseThrow{Exception()}
    }

    override fun getByEmail(email: String): User {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<UserResponse> {
        TODO("Not yet implemented")
    }

    override fun update(id: Long, user: UserRequest): UserResponse {
        TODO("Not yet implemented")
    }

    override fun getContacts(id: Long): List<UserResponse> {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long): String {
        TODO("Not yet implemented")
    }

    override fun create(user: UserRequest): UserResponse {
        val entity = mapper.createRequestToEntity(user)
        return mapper.entityToResponse(dao.save(entity))
    }

    override fun getByIds(ids: List<Long>): List<User> {
        TODO("Not yet implemented")
    }
}