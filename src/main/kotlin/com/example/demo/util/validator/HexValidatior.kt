package com.example.demo.util.validator

import com.example.demo.exception.type.IncorrectHexCode
import org.springframework.stereotype.Component
import java.util.*


@Component
class HexValidatior {
    private val allowedChars: Set<Char> = "1234567890ABCDEF".toSet()
    private val allowedLength = 7
    fun mapHexCode(hexCode: String): String{
        if (hexCode.length != allowedLength) throw IncorrectHexCode("Неправильная длина")
        val code = hexCode.uppercase(Locale.getDefault())
        for (c in code.substring(1).toCharArray()) {
            if (!allowedChars.contains(c)) throw IncorrectHexCode(String.format("Inappropriate letter: %c", c))
        }
        return code;

    }
}