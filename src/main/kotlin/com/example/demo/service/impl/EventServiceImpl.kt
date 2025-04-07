package com.example.demo.service.impl

import com.example.demo.database.entity.Event
import com.example.demo.database.entity.User
import com.example.demo.database.repository.EventDao
import com.example.demo.exception.type.NotFoundException
import com.example.demo.model.request.EventRequest
import com.example.demo.model.request.EventUpdateRequest
import com.example.demo.model.request.NotificationRequest
import com.example.demo.model.response.EventResponse
import com.example.demo.model.response.NotificationResponse
import com.example.demo.service.ContactService
import com.example.demo.service.EventService
import com.example.demo.service.NotificationService
import com.example.demo.service.TagService
import com.example.demo.util.mapper.EventMapper
import com.example.demo.util.mapper.NotificationMapper
import org.springframework.stereotype.Service

@Service
class EventServiceImpl(
    private val dao: EventDao,
    private val mapper: EventMapper,
    private val notificationService: NotificationService,
    private val tagService: TagService,
    private val notificationMapper: NotificationMapper,
    private val contactService: ContactService
): EventService {
    override fun create(request: EventRequest, hostUser: User): EventResponse {
        var entity = mapper.createRequestToEntity(
            request,
            tagService.getById(request.tagId),
            hostUser
        )
        for (contactId in request.contactIds){
            try{
                entity.contacts.add(contactService.getById(contactId))
            }catch (_: Exception){}
        }
        entity = dao.save(entity)
        for (notification in request.notifications){
            notificationService.create(notification, entity)
        }
        return mapper.entityToResponse(entity)
    }

    override fun update(id: Long, request: EventUpdateRequest) = mapper.entityToResponse(
        dao.save(
            mapper.updateRequestToEntity(
                dao.findById(id).orElseThrow { throw NotFoundException("event") },
                request,
                tagService.getById(request.tagId)
                ))
    )

    override fun getById(id: Long) = dao.findById(id).orElseThrow { throw NotFoundException("event") }

    override fun getAll(): List<EventResponse> = dao.findAll().map { mapper.entityToResponse(it) }

    override fun delete(id: Long): String {
        dao.delete(dao.findById(id).orElseThrow { throw NotFoundException("event") })
        return "Событие успешно удалено"
    }

    override fun createNotification(id: Long, notificationRequest: NotificationRequest): NotificationResponse {
        val entity = dao.findById(id).orElseThrow { throw NotFoundException("event") }
        return notificationService.create(notificationRequest, entity)
    }

    override fun getNotifications(id: Long): List<NotificationResponse> {
        val entity = dao.findById(id).orElseThrow { throw NotFoundException("event") }
        return entity.notifications.map { notificationMapper.entityToResponse(it) }
    }

    override fun addContact(id: Long, contactId: Long): EventResponse {
        val entity = dao.findById(id).orElseThrow { throw NotFoundException("event") }
        val contact = contactService.getById(contactId)
        entity.contacts.add(contact)
        return  mapper.entityToResponse(dao.save(entity))
    }

    override fun removeContact(id: Long, contactId: Long): EventResponse {
        val entity = dao.findById(id).orElseThrow { throw NotFoundException("event") }
        val contact = contactService.getById(contactId)
        entity.contacts.remove(contact)
        return  mapper.entityToResponse(dao.save(entity))
    }
}