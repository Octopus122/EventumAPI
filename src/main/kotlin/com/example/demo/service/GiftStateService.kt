package com.example.demo.service

import com.example.demo.database.entity.GiftState
import com.example.demo.model.request.GiftStateRequest
import com.example.demo.model.response.GiftStateResponse

interface GiftStateService {
    fun create(request: GiftStateRequest): GiftStateResponse
    fun getById(id: Long): GiftState
    fun getAll(): List<GiftStateResponse>
    fun update(id: Long, request: GiftStateRequest): GiftStateResponse
    fun delete(id: Long): String

    fun getByName(name: String): GiftState?
}