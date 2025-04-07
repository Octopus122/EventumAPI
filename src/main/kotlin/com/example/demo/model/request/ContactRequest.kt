package com.example.demo.model.request

import org.slf4j.LoggerFactoryFriend

data class ContactRequest (
    val name: String,
    val friendLogin: String?
)