package com.example.demo.service

import com.example.demo.database.entity.User
import com.example.demo.model.request.*
import com.example.demo.model.response.*

interface UserService {
    fun getEntityById(id: Long): User
    fun getByEmail(email: String): User
    fun getByName(name: String): User
    fun getAll(): List<UserResponse>
    fun create(request: UserRegisterRequest): UserResponse
    fun update(id: Long, user: UserRequest): UserResponse

    fun getContacts(id: Long): List<ContactResponse>
    fun createContact(id: Long, contactRequest: ContactRequest): ContactResponse
    fun updateContact(contactId: Long, contactRequest: ContactRequest): ContactResponse

    fun getEvents(id: Long): List<EventResponse>
    fun createEvent(id: Long, eventRequest: EventRequest): EventResponse

    fun getWishList(id: Long): WishListResponse
    fun createPresent(id: Long, presentRequest: PresentRequest): PresentResponse
    fun updateWishList(id: Long, wishListRequest: WishListRequest): WishListResponse

    fun delete(id: Long): String

    fun createTag(id: Long, tagRequest: TagRequest): TagResponse?
    fun getTags(id: Long): List<TagResponse?>

    fun login(request: UserLoginRequest): UserResponse
//    fun getByIds(ids: List<Long>): List<User>
}