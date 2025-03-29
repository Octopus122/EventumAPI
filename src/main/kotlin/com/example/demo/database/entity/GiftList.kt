package com.example.demo.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "giftlist")
class GiftList (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @OneToOne(mappedBy = "giftList")
    var contact: Contact,

    @OneToMany(mappedBy = "giftList")
    var gifts: MutableSet<Gift>
)