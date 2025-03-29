package com.example.demo.database.repository

import com.example.demo.database.entity.Tag
import org.springframework.data.repository.CrudRepository

interface TagDao: CrudRepository<Tag, Long> {
}