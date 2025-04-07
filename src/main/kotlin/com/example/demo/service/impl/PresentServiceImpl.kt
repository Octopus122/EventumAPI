package com.example.demo.service.impl

import com.example.demo.database.entity.Present
import com.example.demo.database.entity.WishList
import com.example.demo.database.repository.PresentDao
import com.example.demo.exception.type.NotFoundException
import com.example.demo.model.request.PresentRequest
import com.example.demo.model.response.PresentResponse
import com.example.demo.service.PresentService
import com.example.demo.util.mapper.PresentMapper
import org.springframework.stereotype.Service

@Service
class PresentServiceImpl(
    private val dao: PresentDao,
    private val mapper: PresentMapper
): PresentService {
    override fun getResponseById(id: Long): PresentResponse = mapper
        .entityToResonse(dao.findById(id).orElseThrow{throw NotFoundException("present")})

    override fun getEntityById(id: Long): Present = dao.findById(id).orElseThrow{throw NotFoundException("present")}

    override fun getAll(): List<PresentResponse> = dao.findAll().map { mapper.entityToResonse(it) }

    override fun create(request: PresentRequest, wishlist: WishList): PresentResponse = mapper
        .entityToResonse(dao.save(mapper.createRequestToEntity(request, wishlist)))

    override fun create(request: PresentRequest) = dao.save(mapper.createRequestToEntity(request, null))

    override fun update(id: Long, request: PresentRequest): PresentResponse = mapper
        .entityToResonse(
            dao.save(
                mapper.updateRequestToEntity(
                    dao.findById(id).orElseThrow{ throw NotFoundException("present")},
                    request
                )
            )
        )

    override fun delete(id: Long): String {
        dao.delete(dao.findById(id).orElseThrow{throw NotFoundException("present")})
        return "Подарок успешно удален"
    }

}