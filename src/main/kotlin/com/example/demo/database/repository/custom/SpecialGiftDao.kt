package com.example.demo.database.repository.custom

interface SpecialGiftDao {
    fun getGiftNumber(presentId: Long): Long
}