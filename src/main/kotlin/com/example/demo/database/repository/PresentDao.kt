package com.example.demo.database.repository

import com.example.demo.database.entity.Present
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PresentDao: CrudRepository<Present, Long> {
}