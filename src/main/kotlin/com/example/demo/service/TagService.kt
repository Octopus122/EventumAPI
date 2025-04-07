package com.example.demo.service

import com.example.demo.database.entity.Tag
import com.example.demo.database.entity.User
import com.example.demo.model.request.TagRequest
import com.example.demo.model.response.TagResponse

interface TagService {
    fun create(request: TagRequest, hostUser: User): TagResponse?
    fun getById(id: Long): Tag?
    fun getAll(): List<TagResponse?>
    fun update(id: Long, request: TagRequest): TagResponse?
    fun delete(id: Long): String

}