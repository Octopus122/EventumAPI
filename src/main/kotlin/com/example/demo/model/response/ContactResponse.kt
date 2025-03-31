package com.example.demo.model.response

import com.example.demo.database.entity.GiftList

data class ContactResponse (
    val id: Long,
    val name: String,
    val hostUserId: Long,
    val friendUser: UserContactResponse?,
    val giftList: GiftListResponse,
    val events: List<EventResponse>
)
