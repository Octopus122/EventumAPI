package com.example.demo.util.mapper

import com.example.demo.database.entity.Contact
import com.example.demo.database.entity.Gift
import com.example.demo.database.entity.GiftState
import com.example.demo.database.entity.Present
import com.example.demo.model.request.CustomGiftRequest
import com.example.demo.model.response.GiftResponse
import org.springframework.stereotype.Component

@Component
class GiftMapper {
    fun entityToResponse(entity: Gift, count: Long?) = GiftResponse(
        entity.id,
        entity.present.title,
        entity.present.description,
        entity.contact.id,
        entity.state.name,
        count
    )
    fun createRequestToResponse(present: Present,
                                state: GiftState,
                                contact: Contact
    ) = Gift(
        present = present,
        state = state,
        contact = contact
    )
}