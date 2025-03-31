package com.example.demo.model.response

data class WishListResponse(
    val id: Long,
    val isAvailable: Boolean,
    val presents: List<PresentResponse>
)
