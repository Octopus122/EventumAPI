package com.example.demo.util.mapper

import com.example.demo.database.entity.User
import com.example.demo.database.entity.WishList
import com.example.demo.model.request.UserRegisterRequest
import com.example.demo.model.request.UserRequest
import com.example.demo.model.response.UserContactResponse
import com.example.demo.model.response.UserResponse
import com.example.demo.util.cipher.HashPasswordService
import org.springframework.stereotype.Component

@Component
class UserMapper(
    private val passwordService: HashPasswordService,
    private val wishListMapper: WishListMapper,
    private val tagMapper: TagMapper,
    private val contactMapper: ContactMapper,
) {
    fun createRequestToEntity(request: UserRegisterRequest): User = User(
        name = request.name,
        email = request.email,
        picture = request.picture,
        password = passwordService.hashPassword(request.password)
    )

    fun updateRequestToEntity(entity: User, request: UserRequest) :User = entity.apply {
        this.name = request.name
        this.email = request.email
        this.picture = request.picture
    }

    fun entityToResponse(entity: User): UserResponse {
        val wishList = entity.wishlist
        if (wishList != null)
        return UserResponse(
            entity.id,
            entity.name,
            entity.email,
            entity.picture,
            entity.contacts.map { contactMapper.entityToResponseNoGifts(it) },
            listOf(),
            wishListMapper.entityToResponse(wishList),
            entity.tags.map { tagMapper.entityToResponse(it) }
        )
        else throw Exception("Не определен вишлист")
    }
    fun entityToContactResponse(entity: User): UserContactResponse {
        val wishList = entity.wishlist
        if (wishList != null)
         return UserContactResponse(
            entity.name,
            entity.picture,
            wishList.id
        )
        else throw Exception("Не определен вишлист")
    }
}