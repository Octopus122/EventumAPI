package com.example.demo.database.repository

import com.example.demo.database.entity.Notification
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NotificationDao:CrudRepository<Notification, Long> {
}