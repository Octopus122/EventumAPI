package com.example.demo.service

import com.example.demo.database.entity.Contact
import com.example.demo.database.entity.User
import com.example.demo.model.request.CustomGiftRequest
import com.example.demo.model.request.LinkedGiftRequest
import com.example.demo.model.response.GiftResponse

interface GiftService {
    fun createCustom(request: CustomGiftRequest, contact: Contact): GiftResponse
    fun createLinked(request: LinkedGiftRequest, contact: Contact): GiftResponse
    fun getById(id: Long): GiftResponse
    fun getAll(): List<GiftResponse>
    fun updateCustom(id: Long, request: CustomGiftRequest): GiftResponse
    fun delete(id: Long): String
    fun getCount(id: Long): Long?
}