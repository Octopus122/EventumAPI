package com.example.demo.database.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "event")
data class Event(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "description")
    var description: String?,

    @Column(name = "time", nullable = false)
    var time: LocalDate,

    @Column(name = "picture", nullable = true)
    var picture: String,

    @ManyToOne
    @JoinColumn(name = "tag_id")
    var tag: Tag?, // tag of event

    @ManyToOne
    @JoinColumn(name = "host_user", nullable = false)
    var hostUser: User,

    @ManyToMany
    @JoinTable(
        name = "event_contacts",
        joinColumns = [JoinColumn(name = "event_id")] ,
        inverseJoinColumns = [JoinColumn(name = "contact_id")]
    )
    var contacts: MutableSet<Contact> = mutableSetOf(), // contacts, which target in event
)