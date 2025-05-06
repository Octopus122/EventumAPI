package com.example.demo.util.mapper

import com.example.demo.database.entity.Present
import com.example.demo.database.entity.WishList
import com.example.demo.model.request.PresentRequest
import com.example.demo.model.response.PresentResponse
import org.springframework.stereotype.Component

@Component
class PresentMapper {
    fun entityToResponse(entity: Present):PresentResponse = PresentResponse(
        entity.id,
        entity.title,
        entity.description
    )
    fun createRequestToEntity(request: PresentRequest, wishList: WishList?): Present = Present(
        title = request.title,
        description = request.description,
        wishlist = wishList
    )
    fun updateRequestToEntity(entity: Present, request: PresentRequest):Present = Present(
        id = entity.id,
        title = request.title,
        description = request.description,
        wishlist = entity.wishlist
    )
}