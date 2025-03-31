package com.example.demo.service.impl

import com.example.demo.database.entity.User
import com.example.demo.database.repository.UserDao
import com.example.demo.exception.type.NotFoundException
import com.example.demo.model.request.UserRegisterRequest
import com.example.demo.model.request.UserRequest
import com.example.demo.model.response.*
import com.example.demo.service.UserService
import com.example.demo.util.mapper.UserMapper
import com.example.demo.util.mapper.WishListMapper
import org.springframework.stereotype.Service


@Service
class UserServiceImpl(
    private val dao: UserDao,
    private val mapper: UserMapper,
    private val wishlistMapper: WishListMapper,
    private val wishListService: WishListServiceImpl

    ) : UserService {
    override fun getEntityById(id: Long): User = dao.findById(id).orElseThrow { throw NotFoundException("user") }

    override fun getByEmail(email: String): UserResponse {
        val entity = dao.findByEmail(email)
        if (entity == null) throw NotFoundException("user")
        return mapper.entityToResponse(entity)
    }
    override fun getByName(name: String): UserContactResponse {
        val entity = dao.findByName(name)
        if (entity == null) throw NotFoundException("user")
        return mapper.entityToContactResponse(entity)
    }

    override fun getAll(): List<UserResponse> = dao.findAll().map {
        mapper.entityToResponse(it)
    }

    override fun create(request: UserRegisterRequest): UserResponse {
        val entity = dao.save(User(
            name = request.name,
            picture = request.picture,
            email = request.email,
            password = request.password
        ))

        entity.wishlist = wishListService.create(entity)
        return mapper.entityToResponse(dao.save(entity))
    }

    override fun update(id: Long, user: UserRequest): UserResponse = mapper
        .entityToResponse(
            dao.save(
                mapper.updateRequestToEntity(
                    dao.findById(id).orElseThrow { throw NotFoundException("user") },
                    user
                )
            )
        )

    override fun getContacts(id: Long): List<ContactResponse> {
        TODO("Not yet implemented")
    }

    override fun getEvents(id: Long): List<EventResponse> {
        TODO("Not yet implemented")
    }

    override fun getWishList(id: Long): WishListResponse {
        val wishlist = dao.findById(id).orElseThrow { throw NotFoundException("user") }.wishlist
        if (wishlist == null) throw Exception("Неопределён вишлист")
        else return wishlistMapper.entityToResponse(wishlist)
    }

    override fun delete(id: Long): String {
        dao.delete(dao.findById(id).orElseThrow { throw NotFoundException("user") })
        return "Пользователь успешно удален"
    }


}