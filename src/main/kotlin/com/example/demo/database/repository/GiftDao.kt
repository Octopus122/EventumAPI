package com.example.demo.database.repository

import com.example.demo.database.entity.Gift
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GiftDao: CrudRepository<Gift, Long> {
}