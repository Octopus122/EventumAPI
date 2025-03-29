package com.example.demo.database.repository

import com.example.demo.database.entity.User
import org.springframework.data.repository.CrudRepository

interface UserDao: CrudRepository<User, Long> {
    fun findByEmail(email: String?):User?
    fun findByName(name: String?):User?
}