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
    @JoinColumn(name = "host_user_id", nullable = false)
    val hostUser: User,

    @ManyToOne
    @JoinColumn(name = "friend_user_id")
    var friendUser: User?,

    @OneToMany(mappedBy = "contact", cascade = [CascadeType.ALL])
    val gifts: MutableSet<Gift> = mutableSetOf(),

    @ManyToMany(mappedBy = "contacts")
    val events: MutableSet<Event> = mutableSetOf()

)