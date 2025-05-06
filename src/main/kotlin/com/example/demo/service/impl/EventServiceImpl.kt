package com.example.demo.service.impl

import com.example.demo.database.entity.User
import com.example.demo.database.repository.EventDao
import com.example.demo.exception.type.NotFoundException
import com.example.demo.model.request.EventRequest
import com.example.demo.model.request.EventUpdateRequest
import com.example.demo.model.request.NotificationRequest
import com.example.demo.model.response.ContactResponse
import com.example.demo.model.response.EventResponse
import com.example.demo.service.ContactService
import com.example.demo.service.EventService
import com.example.demo.service.TagService
import com.example.demo.util.mapper.ContactMapper
import com.example.demo.util.mapper.EventMapper
import org.springframework.stereotype.Service

@Service
class EventServiceImpl(
    private val dao: EventDao,
    private val mapper: EventMapper,
    private val tagService: TagService,
    private val contactService: ContactService,
    private val contactMapper: ContactMapper,
): EventService {
    override fun create(request: EventRequest, hostUser: User): EventResponse {
        var entity = mapper.createRequestToEntity(
            request,
            tagService.getById(request.tagId),
            hostUser
        )
        request.contactIds?.let {
            for (contactId in it){
                try{
                    entity.contacts.add(contactService.getById(contactId))
                }catch (_: Exception){}
            }
        }
        entity = dao.save(entity)
        return mapper.entityToResponse(entity)
    }

    override fun update(id: Long, request: EventRequest) = mapper.entityToResponse(
        dao.save(
            mapper.updateRequestToEntity(
                dao.findById(id).orElseThrow { throw NotFoundException("event") },
                request,
                tagService.getById(request.tagId)
                ))
    )

    override fun getEntity(id: Long) = dao.findById(id).orElseThrow { throw NotFoundException("event") }

    override fun getById(id: Long): EventResponse = mapper.entityToResponse(getEntity(id))


    override fun getAll(): List<EventResponse> = dao.findAll().map { mapper.entityToResponse(it) }

    override fun delete(id: Long): String {
        dao.delete(dao.findById(id).orElseThrow { throw NotFoundException("event") })
        return "Событие успешно удалено"
    }

    override fun addContact(id: Long, contactId: Long): EventResponse {
        val entity = dao.findById(id).orElseThrow { throw NotFoundException("event") }
        val contact = contactService.getById(contactId)
        entity.contacts.add(contact)
        return  mapper.entityToResponse(dao.save(entity))
    }

    override fun getContacts(id: Long): List<ContactResponse> {
        val entity = dao.findById(id).orElseThrow { throw NotFoundException("event") }
        return entity.contacts.map { contactMapper.entityToResponse(it, contactService.getGifts(it.id)) }
    }

    override fun removeContact(id: Long, contactId: Long): EventResponse {
        val entity = dao.findById(id).orElseThrow { throw NotFoundException("event") }
        val contact = contactService.getById(contactId)
        entity.contacts.remove(contact)
        return  mapper.entityToResponse(dao.save(entity))
    }
}