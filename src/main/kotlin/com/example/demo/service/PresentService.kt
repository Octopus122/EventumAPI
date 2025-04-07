package com.example.demo.service

import com.example.demo.database.entity.Present
import com.example.demo.database.entity.WishList
import com.example.demo.model.request.PresentRequest
import com.example.demo.model.response.PresentResponse

interface PresentService {
    fun getResponseById(id: Long): PresentResponse
    fun getEntityById(id: Long): Present
    fun getAll(): List<PresentResponse>
    fun create(request: PresentRequest, wishlist: WishList): PresentResponse
    fun create(request: PresentRequest): Present
    fun update(id: Long, request: PresentRequest): PresentResponse
    fun delete(id: Long): String
}