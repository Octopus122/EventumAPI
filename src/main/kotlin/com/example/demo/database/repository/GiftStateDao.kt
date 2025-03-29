package com.example.demo.database.repository

import com.example.demo.database.entity.GiftState
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GiftStateDao:CrudRepository<GiftState, Long> {
}