package com.example.demo.util.mapper

import com.example.demo.database.entity.Contact
import com.example.demo.database.entity.User
import com.example.demo.model.request.ContactRequest
import com.example.demo.model.response.*
import org.slf4j.LoggerFactoryFriend
import org.springframework.stereotype.Component

@Component
class ContactMapper(
    private val wishListMapper: WishListMapper,
    private val giftMapper: GiftMapper
) {
    fun entityToResponse(entity: Contact, gifts: List<GiftResponse>): ContactResponse = ContactResponse(
        entity.id,
        entity.name,
        entity.hostUser.id,
        entityToContactResponse(entity.friendUser),
        gifts.map { it.id }
    )
    fun entityToResponseNoGifts(entity: Contact): ContactNoGiftsResponse = ContactNoGiftsResponse(
        entity.id,
        entity.name,
        entity.hostUser.id,
        entityToContactResponse(entity.friendUser)
    )

    fun entityToContactResponse(entity: User?): UserContactResponse? {
        if (entity == null) return null
        val wishList = entity.wishlist
        if (wishList != null)
            return UserContactResponse(
                entity.name,
                entity.picture,
                if (wishList.isAvalable) wishList.id else null
            )
        else throw Exception("Не определен вишлист")
    }
    fun createRequestToEntity(request: ContactRequest, hostUser: User, friend: User?): Contact = Contact(
        name = request.name,
        hostUser = hostUser,
        friendUser = friend,
    )

    fun updateEntityToResponse(entity: Contact, request: ContactRequest, friend: User?) = entity.apply {
        name = request.name
        friendUser = friend
    }
}