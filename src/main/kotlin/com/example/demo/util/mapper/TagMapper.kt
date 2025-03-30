package com.example.demo.util.mapper

import com.example.demo.database.entity.Tag
import com.example.demo.model.request.TagRequest
import com.example.demo.model.response.TagResponse
import com.example.demo.util.validator.HexValidatior
import org.springframework.stereotype.Component

@Component
class TagMapper(
    private val hexValidator: HexValidatior
) {
    fun entityToResponse(entity: Tag) : TagResponse = TagResponse(
        entity.id,
        entity.name,
//        entity.colorCode
    )
    fun createEntity(request: TagRequest): Tag = Tag(
        name = request.name,
//        colorCode = hexValidator.mapHexCode(request.colorCode)
    )
    fun updateEntity(entity: Tag, request: TagRequest): Tag = entity.apply {
        name = request.name
//        colorCode = request.colorCode
    }
}