package com.example.demo.service

import com.example.demo.database.entity.Contact
import com.example.demo.database.entity.User
import com.example.demo.model.request.ContactRequest
import com.example.demo.model.request.CustomGiftRequest
import com.example.demo.model.request.LinkedGiftRequest
import com.example.demo.model.response.ContactResponse
import com.example.demo.model.response.GiftResponse

interface ContactService {
    fun getById(id: Long): Contact
    fun getAll(): List<ContactResponse>
    fun create(request: ContactRequest, hostUser: User, friendUser: User?): ContactResponse
    fun update(id: Long, request: ContactRequest, friendUser: User?): ContactResponse
    fun delete(id: Long): String

    fun createCustomGift(id: Long, request: CustomGiftRequest): GiftResponse
    fun createLinkedGift(id: Long, request: LinkedGiftRequest): GiftResponse
    fun getGifts(id: Long): List<GiftResponse>
}