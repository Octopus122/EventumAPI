package com.example.demo.service

import com.example.demo.database.entity.User
import com.example.demo.database.entity.WishList
import com.example.demo.model.request.WishListRequest
import com.example.demo.model.response.WishListResponse

interface WishListService {
    fun getEntityById(id: Long): WishList
    fun getResponseById(id: Long): WishListResponse
    fun getAll(): List<WishListResponse>
    fun create(hostUser: User): WishList // при создании пустой виш лист просто прикрепляется к пользователю
    fun update(id: Long, request: WishListRequest): WishListResponse
    fun delete(id: Long): String
}