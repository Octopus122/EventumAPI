package com.example.demo.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "contact")
data class Contact( // alike to users, but created by one user and don't have accounts
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "name", nullable = false)
    var name: String,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val hostUser: User,

    @OneToMany
    @JoinColumn(name = "user_id")
    val friendUser: User?,

    @OneToOne(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "giftlist_id", nullable = false)
    var giftList: GiftList,

    @ManyToMany(mappedBy = "contacts")
    var events: MutableSet<Event>

)