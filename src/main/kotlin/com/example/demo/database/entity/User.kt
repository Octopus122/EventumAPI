package com.example.demo.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "email", nullable = false, unique = true)
    var email: String,
    @Column(name = "picture", nullable = true)
    var picture: String,

    @Column(name = "password", nullable = true)
    var password: String,

//    @Column(name = "confirm_email", nullable = false) // заглушка на случай добавления
//    var isConfirmed: Boolean = false,

    @OneToMany(cascade = arrayOf(CascadeType.ALL), mappedBy = "hostUser")
    var contacts: MutableSet<Contact> = mutableSetOf(),

    @OneToMany(cascade = arrayOf(CascadeType.ALL), mappedBy = "hostUser")
    var events: MutableSet<Event> = mutableSetOf(), // events, created by user

    @OneToOne(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "wishlist_id", nullable = false) // c пользователем сохдается wishList
    var wishlist: WishList
) {
}