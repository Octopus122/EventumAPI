package com.example.demo.database.entity

import jakarta.persistence.*
@Entity
@Table(name = "gift")
class Gift (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @ManyToOne
    @JoinColumn(name = "present_id", nullable = false)
    var present: Present,

    @ManyToOne
    @JoinColumn(name = "state_id", nullable = false)
    var state: GiftState,

    @ManyToOne
    @JoinColumn(name = "giftlist_id", nullable = false)
    var giftList: GiftList
)