package com.example.demo.exception.type

import java.lang.Exception

class WrongLoginDataException(override val message: String?): Exception(message) {
}