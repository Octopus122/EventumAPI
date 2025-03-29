package com.example.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.Environment
import org.springframework.core.env.get


@SpringBootApplication
class EventumApiApplication

@Autowired
private val env: Environment? = null

fun main(args: Array<String>) {
	val dbUrl: String? = env?.get("DB_URL")
	println(dbUrl)
	runApplication<EventumApiApplication>(*args)
}


