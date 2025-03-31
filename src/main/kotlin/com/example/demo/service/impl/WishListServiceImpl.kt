package com.example.demo.service.impl

import com.example.demo.database.entity.User
import com.example.demo.database.entity.WishList
import com.example.demo.database.repository.WishListDao
import com.example.demo.exception.type.NotFoundException
import com.example.demo.model.request.WishListRequest
import com.example.demo.model.response.WishListResponse
import com.example.demo.service.WishListService
import com.example.demo.util.mapper.WishListMapper
import org.springframework.stereotype.Service

@Service
class WishListServiceImpl(
    private val dao: WishListDao,
    private val mapper: WishListMapper
) : WishListService {
    override fun getEntityById(id: Long): WishList = dao.findById(id).orElseThrow{throw NotFoundException("wishlist")}

    override fun getResponseById(id: Long): WishListResponse = mapper
        .entityToResponse(dao.findById(id).orElseThrow{throw NotFoundException("wishlist")})

    override fun getAll(): List<WishListResponse> = dao.findAll().map {
        mapper.entityToResponse(it)
    }

    override fun create(hostUser: User): WishList = dao.save(WishList(
        user = hostUser
    ))

    override fun update(id: Long, request: WishListRequest): WishListResponse = mapper
        .entityToResponse(
            mapper.updateRequestToEntity(
                dao.findById(id).orElseThrow{throw NotFoundException("wishlist")},
                request
            )
        )

    override fun delete(id: Long): String {
        dao.delete(dao.findById(id).orElseThrow{throw NotFoundException("wishlist")})
        return "Вишлист удалён"
    }
}