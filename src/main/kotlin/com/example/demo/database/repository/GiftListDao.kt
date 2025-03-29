package com.example.demo.database.repository

import com.example.demo.database.entity.GiftList
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GiftListDao :CrudRepository<GiftList, Long> {
}