package com.example.demo.util.mapper

import com.example.demo.database.entity.WishList
import com.example.demo.model.request.WishListRequest
import com.example.demo.model.response.WishListResponse
import org.springframework.stereotype.Component

@Component
class WishListMapper(
    private val presentMapper: PresentMapper
) {
    fun entityToResponse(entity: WishList): WishListResponse = WishListResponse(
        entity.id,
        entity.isAvalable,
        entity.presents.map{
            presentMapper.entityToResponse(it)
        }
    )
    fun updateRequestToEntity(entity: WishList, request: WishListRequest): WishList = entity.apply {
        isAvalable = request.isAvailable
    }
}