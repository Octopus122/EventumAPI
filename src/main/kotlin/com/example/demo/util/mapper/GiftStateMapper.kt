package com.example.demo.util.mapper

import com.example.demo.database.entity.GiftState
import com.example.demo.database.entity.Tag
import com.example.demo.model.request.GiftStateRequest
import com.example.demo.model.request.TagRequest
import com.example.demo.model.response.GiftStateResponse
import com.example.demo.model.response.TagResponse
import org.springframework.stereotype.Component

@Component
class GiftStateMapper {
    fun entityToResponse(entity: GiftState) : GiftStateResponse = GiftStateResponse(
        entity.id,
        entity.name
    )
    fun createEntity(request: GiftStateRequest): GiftState = GiftState(
        name = request.name
    )
    fun updateEntity(entity: GiftState, request: GiftStateRequest): GiftState = entity.apply {
        name = request.name
    }
}