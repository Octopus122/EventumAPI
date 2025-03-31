package com.example.demo.service

import com.example.demo.database.entity.WishList
import com.example.demo.model.request.PresentCreateRequest
import com.example.demo.model.request.PresentUpdateRequest
import com.example.demo.model.response.PresentResponse

interface PresentService {
    fun getById(id: Long): PresentResponse
    fun getAll(): List<PresentResponse>
    fun create(request: PresentCreateRequest, wishlist: WishList): PresentResponse
    fun update(id: Long, request: PresentUpdateRequest): PresentResponse
    fun delete(id: Long): String
}