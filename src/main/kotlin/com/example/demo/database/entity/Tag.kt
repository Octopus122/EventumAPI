package com.example.demo.database.entity

import jakarta.persistence.*
@Entity
@Table(name = "tag")
class Tag (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @Column(name = "name", nullable = false)
    var name: String,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var hostUser: User

    //@Column(name = "colorcode", nullable = false)
    //var colorCode: String
)