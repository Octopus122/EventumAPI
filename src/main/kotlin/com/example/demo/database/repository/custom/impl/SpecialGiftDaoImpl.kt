package com.example.demo.database.repository.custom.impl

import com.example.demo.database.repository.custom.SpecialGiftDao
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext

class SpecialGiftDaoImpl: SpecialGiftDao {
    @PersistenceContext
    private lateinit var em: EntityManager
    override fun getGiftNumber(presentId: Long): Long {
        return em.createQuery(
            "select count(g.id) as count " +
                    "from Gift g " +
                    "where g.present.id = $presentId ",
            Long::class.java).resultList[0]
    }
}