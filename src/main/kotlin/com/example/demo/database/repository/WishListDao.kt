package com.example.demo.database.repository

import com.example.demo.database.entity.WishList
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface WishListDao: CrudRepository<WishList, Long> {
}