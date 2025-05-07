package com.example.demo.controller

import com.example.demo.model.request.*
import com.example.demo.model.response.*
import com.example.demo.service.UserService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UserController(
    private val service: UserService
) {
    @PostMapping("/")
    fun createUser(@RequestBody request: UserRegisterRequest): UserResponse = service.create(request)

    @PutMapping("/{id}/")
    fun updateUser(@PathVariable id: Long, @RequestBody request: UserRequest): UserResponse =
        service.update(id, request)

    @GetMapping("/")
    fun getAll(): List<UserResponse> = service.getAll()

    @GetMapping("/find/{name}")
    fun getAllUsersByName(@PathVariable name: String): List<UserResponse> = service.getAll()

    @DeleteMapping("/{id}/")
    fun deleteUser(@PathVariable id: Long) = service.delete(id)

    @GetMapping("/{id}/")
    fun getUser(@PathVariable id: Long): UserResponse = service.getById(id)

    @PostMapping("/{id}/tags")
    fun createTag(@PathVariable id: Long, @RequestBody tagRequest: TagRequest) = service.createTag(id, tagRequest)

    @GetMapping("/{id}/tags")
    fun getTags(@PathVariable id: Long) = service.getTags(id)

    @PostMapping("{id}/presents")
    fun createPresent(@PathVariable id: Long, @RequestBody presentRequest: PresentRequest): PresentResponse = service
        .createPresent(id, presentRequest)

    @GetMapping("{id}/wishlist")
    fun getPresents(@PathVariable id: Long): WishListResponse = service.getWishList(id)

    @PutMapping("{id}/wishlist")
    fun updateWishlist(@PathVariable id: Long, @RequestBody wishListRequest: WishListRequest): WishListResponse = service
        .updateWishList(id, wishListRequest)

    @GetMapping("{id}/contacts")
    fun getContacts(@PathVariable id: Long) = service.getContacts(id)

    @PostMapping("{id}/contacts")
    fun createContact(@PathVariable id: Long, @RequestBody contactRequest: ContactRequest) = service
        .createContact(id, contactRequest)

    @PostMapping("{id}/events")
    fun createEvent(@PathVariable id: Long, @RequestBody eventRequest: EventRequest): EventResponse = service
        .createEvent(id, eventRequest)

    @GetMapping("{id}/events")
    fun getEvents(@PathVariable id: Long) = service.getEvents(id)

    @PostMapping("{id}/friends/{friendId}")
    fun createFriend(@PathVariable id: Long, @PathVariable friendId: Long): UserResponse = service
        .createFriend(id, friendId)

    @GetMapping("{id}/friends/{friendId}")
    fun checkFriendship(@PathVariable id: Long, @PathVariable friendId: Long): FriendshipResponse = service
        .checkFriendShip(id, friendId)

    @GetMapping("{id}/friends")
    fun getFriendsUsers(@PathVariable id: Long): List<UserResponse> = service.getFriends(id)
}