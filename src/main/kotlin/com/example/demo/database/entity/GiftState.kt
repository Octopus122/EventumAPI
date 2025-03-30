package com.example.demo.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "gift_state")
class GiftState (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @Column(name = "name", nullable = false)
    var name: String
)