package com.example.demo.exception.type

class NotFoundException(override val message: String?): Exception(message) {
}