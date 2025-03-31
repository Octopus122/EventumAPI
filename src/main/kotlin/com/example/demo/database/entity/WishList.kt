package com.example.demo.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "wishlist")
class WishList (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @Column(name = "is_available")
    var isAvalable: Boolean = false,

    @OneToOne(mappedBy = "wishlist")
    var user: User?,

    @OneToMany(mappedBy = "wishlist")
    var presents: MutableSet<Present> = mutableSetOf()
)