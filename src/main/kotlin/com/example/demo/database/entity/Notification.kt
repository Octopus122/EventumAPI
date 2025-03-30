package com.example.demo.database.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "notification")
class Notification (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "description")
    var description: String?,

    @Column(name = "time")
    var time: LocalDateTime,

    @ManyToOne
    @JoinColumn(name = "event", nullable = false)
    var event: Event
)