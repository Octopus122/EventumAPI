package com.example.demo.database.repository

import com.example.demo.database.entity.Contact
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactDao : CrudRepository<Contact, Long> {
}