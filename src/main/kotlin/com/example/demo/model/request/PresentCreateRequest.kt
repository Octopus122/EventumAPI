package com.example.demo.model.request


data class PresentCreateRequest (
    val title: String,
    val description: String,
    val wishListId: Long?
)