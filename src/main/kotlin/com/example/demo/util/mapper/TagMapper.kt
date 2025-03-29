package com.example.demo.util.mapper

import com.example.demo.database.entity.Tag
import com.example.demo.model.request.TagRequest
import com.example.demo.model.response.TagResponse
import org.springframework.stereotype.Component

@Component
class TagMapper {
    fun entityToResponse(entity: Tag) : TagResponse = TagResponse(
        entity.id,
        entity.name,
        entity.colorCode
    )
    fun createEntity(request: TagRequest): Tag = Tag(
        name = request.name,
        colorCode = request.colorCode
    )
}