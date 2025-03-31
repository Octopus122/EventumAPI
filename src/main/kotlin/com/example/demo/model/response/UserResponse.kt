package com.example.demo.model.response

data class UserResponse(
    val id: Long,
    val email: String,
    val picture: String,
//    val confirm_mail: Boolean,
    val contacts: List<ContactResponse>,
    val events: List<EventResponse>,
    val wishlist: WishListResponse?
)
