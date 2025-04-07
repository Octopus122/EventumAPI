package com.example.demo.service.impl

import com.example.demo.database.entity.Event
import com.example.demo.database.repository.NotificationDao
import com.example.demo.exception.type.NotFoundException
import com.example.demo.model.request.NotificationRequest
import com.example.demo.model.request.NotificationUpdateRequest
import com.example.demo.model.response.NotificationResponse
import com.example.demo.service.NotificationService
import com.example.demo.util.mapper.NotificationMapper
import org.springframework.stereotype.Service

@Service
class NotificationServiceImpl(
    private val dao: NotificationDao,
    private val mapper: NotificationMapper
): NotificationService {
    override fun create(request: NotificationRequest, event: Event) = mapper
        .entityToResponse(dao.save(mapper.createRequestToEntity(request, event)))

    override fun update(id: Long, request: NotificationUpdateRequest): NotificationResponse {
        val entity = dao.findById(id).orElseThrow { throw NotFoundException("notification") }
        return mapper.entityToResponse(dao.save(mapper.updateRequestToEntity(entity,request)))
    }

    override fun getById(id: Long) = mapper
        .entityToResponse(dao.findById(id).orElseThrow { throw NotFoundException("notification") })

    override fun getAll() = dao.findAll().map { mapper.entityToResponse(it) }

    override fun delete(id: Long): String {
        val entity = dao.findById(id).orElseThrow { throw NotFoundException("notification") }
        dao.delete(entity)
        return "Уведомление успешно удалено"
    }

}