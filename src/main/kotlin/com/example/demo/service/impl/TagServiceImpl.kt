package com.example.demo.service.impl

import com.example.demo.database.entity.Tag
import com.example.demo.database.entity.User
import com.example.demo.database.repository.TagDao
import com.example.demo.exception.type.NotFoundException
import com.example.demo.model.request.TagRequest
import com.example.demo.model.response.TagResponse
import com.example.demo.service.TagService
import com.example.demo.util.mapper.TagMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TagServiceImpl(
    private val dao: TagDao,
    private val mapper: TagMapper
): TagService {

    override fun create(request: TagRequest, hostUser: User) = mapper.entityToResponse(
        dao.save(mapper.createEntity(request, hostUser))
    )
    override fun getById(id: Long): Tag? = dao.findByIdOrNull(id)

    override fun getAll(): List<TagResponse?> = dao.findAll()
        .map { mapper.entityToResponse(it) }

    override fun update(id: Long, request: TagRequest): TagResponse? {
        val entity = dao.findById(id).orElseThrow{throw NotFoundException("tag")}
        return mapper.entityToResponse(dao.save(mapper.updateEntity(entity,  request)))
    }

    override fun delete(id: Long): String {
        val entity = dao.findById(id).orElseThrow{throw NotFoundException("tag")}
        dao.delete(entity)
        return "Tag успешно удален"
    }

}