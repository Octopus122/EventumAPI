package com.example.demo.service.impl

import com.example.demo.database.repository.GiftStateDao
import com.example.demo.exception.type.NotFoundException
import com.example.demo.model.request.GiftStateRequest
import com.example.demo.model.response.GiftStateResponse
import com.example.demo.service.GiftStateService
import com.example.demo.util.mapper.GiftStateMapper
import org.springframework.stereotype.Service

@Service
class GiftStateServiceImpl(
    private val dao: GiftStateDao,
    private val mapper: GiftStateMapper
): GiftStateService {
    override fun create(request: GiftStateRequest): GiftStateResponse {
        return mapper.entityToResponse(dao.save(mapper.createEntity(request)))
    }

    override fun getById(id: Long): GiftStateResponse = mapper
        .entityToResponse(dao.findById(id).orElseThrow{throw NotFoundException("tag") })

    override fun getAll(): List<GiftStateResponse> = dao.findAll()
        .map { mapper.entityToResponse(it) }

    override fun update(id: Long, request: GiftStateRequest): GiftStateResponse {
        val entity = dao.findById(id).orElseThrow{throw NotFoundException("tag") }
        return mapper.entityToResponse(dao.save(mapper.updateEntity(entity,  request)))
    }

    override fun delete(id: Long): String {
        val entity = dao.findById(id).orElseThrow{throw NotFoundException("tag") }
        dao.delete(entity)
        return "GiftState успешно удален"
    }
}