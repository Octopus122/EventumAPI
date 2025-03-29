package com.example.demo.database.repository

import com.example.demo.database.entity.Event
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EventDao: CrudRepository<Event, Long> {
}