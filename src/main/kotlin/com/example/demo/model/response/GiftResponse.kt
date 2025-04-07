package com.example.demo.model.response

data class GiftResponse(
    var id: Long,
    var presentTitle: String,
    var presentDescription: String,
    var contactId: Long,
    var state: String,
    var giftNumber: Long?
)
