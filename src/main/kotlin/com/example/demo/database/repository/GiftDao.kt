package com.example.demo.database.repository

import com.example.demo.database.entity.Gift
import com.example.demo.database.repository.custom.SpecialGiftDao
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GiftDao: CrudRepository<Gift, Long>, SpecialGiftDao {
}