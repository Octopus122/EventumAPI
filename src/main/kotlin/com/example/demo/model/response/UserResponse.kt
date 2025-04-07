package com.example.demo.model.response


data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val picture: String,
//    val confirm_mail: Boolean,
    val contacts: List<ContactNoGiftsResponse>,
    val events: List<EventResponse>,
    val wishlist: WishListResponse?,
    val tags: List<TagResponse?>
)
