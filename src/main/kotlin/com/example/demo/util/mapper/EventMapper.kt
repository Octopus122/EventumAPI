package com.example.demo.util.mapper

import com.example.demo.database.entity.Event
import com.example.demo.database.entity.Tag
import com.example.demo.database.entity.User
import com.example.demo.model.request.EventRequest
import com.example.demo.model.request.EventUpdateRequest
import com.example.demo.model.response.EventResponse
import org.springframework.stereotype.Component

@Component
class EventMapper(
    private val tagMapper: TagMapper,
    private val contactMapper: ContactMapper
) {
    fun entityToResponse(entity: Event) =
            EventResponse(
            entity.id,
            entity.name,
                entity.description.toString(),
                entity.time.toString(),
            entity.picture,
            entity.tag?.id ?: 0,
            entity.hostUser.id
            )
    fun createRequestToEntity(request: EventRequest, tag: Tag?, user: User) = Event(
        name = request.name,
        description = request.description,
        time = request.time,
        picture = request.picture,
        tag = tag,
        hostUser = user
    )
    fun updateRequestToEntity(entity: Event, request: EventRequest, tag: Tag?) = entity.apply {
        name = request.name
        description = request.description
        time = request.time
        picture = request.picture
        this.tag = tag
    }
}