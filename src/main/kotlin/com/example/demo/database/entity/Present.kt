package com.example.demo.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "present")
class Present (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @Column(name = "title", nullable = false)
    var title:String,

    @Column(name = "description")
    var description: String,

    @ManyToOne(cascade = arrayOf(CascadeType.PERSIST))
    @JoinColumn(name = "wishlist_id")
    var wishlist: WishList?
)