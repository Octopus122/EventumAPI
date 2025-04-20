package com.example.demo.service.impl

import com.example.demo.database.entity.User
import com.example.demo.database.repository.UserDao
import com.example.demo.exception.type.NotFoundException
import com.example.demo.exception.type.WrongLoginDataException
import com.example.demo.model.request.*
import com.example.demo.model.response.*
import com.example.demo.service.*
import com.example.demo.util.cipher.HashPasswordService
import com.example.demo.util.mapper.*
import org.springframework.stereotype.Service


@Service
class UserServiceImpl(
    private val dao: UserDao,
    private val mapper: UserMapper,
    private val wishlistMapper: WishListMapper,
    private val wishListService: WishListService,
    private val tagService: TagService,
    private val tagMapper: TagMapper,
    private val presentService: PresentService,
    private val contactService: ContactService,
    private val contactMapper: ContactMapper,
    private val eventMapper: EventMapper,
    private val eventService: EventService,
    private val passwordService: HashPasswordService

    ) : UserService {
    override fun getEntityById(id: Long): User = dao.findById(id).orElseThrow { throw NotFoundException("user") }

    override fun getById(id: Long): UserResponse = mapper.entityToResponse(getEntityById(id))


    override fun getByEmail(email: String): User {
        val entity = dao.findByEmail(email) ?: throw NotFoundException("user")
        return entity
    }
    override fun getByName(name: String): User {
        val entity = dao.findByName(name) ?: throw NotFoundException("user")
        return entity
    }

    override fun getAll(): List<UserResponse> = dao.findAll().map {
        mapper.entityToResponse(it)
    }

    override fun create(request: UserRegisterRequest): UserResponse {
        val entity = dao.save(mapper.createRequestToEntity(request))

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
        val user = dao.findById(id).orElseThrow { throw NotFoundException("user") }
        return user.contacts.map { contactMapper.entityToResponse(it, contactService.getGifts(it.id)) }
    }

    override fun createContact(id: Long, contactRequest: ContactRequest): ContactResponse {
        val user = dao.findById(id).orElseThrow { throw NotFoundException("user") }
        var friend: User? = null
        if (contactRequest.friendLogin != null){
            try{
                friend = dao.findByName(contactRequest.friendLogin)
            }
            catch (_: Exception){}
        }
        return contactService.create(contactRequest, user, friend)
    }

    override fun updateContact(contactId: Long, contactRequest: ContactRequest): ContactResponse{
        val friend = if (contactRequest.friendLogin != null ) dao.findByName(contactRequest.friendLogin) else null
        return contactService.update(contactId, contactRequest, friend)
    }

    override fun getEvents(id: Long): List<EventResponse> {
        val entity = dao.findById(id).orElseThrow { throw NotFoundException("user") }
        return entity.events.map { eventMapper.entityToResponse(it) }
    }

    override fun createEvent(id: Long, eventRequest: EventRequest): EventResponse {
        val entity = dao.findById(id).orElseThrow { throw NotFoundException("user") }
        return eventService.create(eventRequest, entity)
    }

    override fun getWishList(id: Long): WishListResponse {
        val wishlist = dao.findById(id).orElseThrow { throw NotFoundException("user") }.wishlist
        if (wishlist == null) throw Exception("Неопределён вишлист")
        else return wishlistMapper.entityToResponse(wishlist)
    }

    override fun createPresent(id: Long, presentRequest: PresentRequest): PresentResponse{
        val wishlist = dao.findById(id).orElseThrow { throw NotFoundException("user") }.wishlist
        if (wishlist == null) throw Exception("Неопределён вишлист")
        else return presentService.create(presentRequest, wishlist)
    }

    override fun updateWishList(id: Long, wishListRequest: WishListRequest): WishListResponse {
        val wishlist = dao.findById(id).orElseThrow { throw NotFoundException("user") }.wishlist
        if (wishlist == null) throw Exception("Неопределён вишлист")
        else return wishListService.update(wishlist.id, wishListRequest)
    }

    override fun delete(id: Long): String {
        dao.delete(dao.findById(id).orElseThrow { throw NotFoundException("user") })
        return "Пользователь успешно удален"
    }

    override fun createTag(id: Long, tagRequest: TagRequest): TagResponse? {
        val user = dao.findById(id).orElseThrow{throw NotFoundException("user")}
        return tagService.create(tagRequest, user)
    }

    override fun getTags(id: Long): List<TagResponse?> {
        val user = dao.findById(id).orElseThrow{throw NotFoundException("user")}
        return user.tags.map { tagMapper.entityToResponse(it) }
    }

    override fun login(request: UserLoginRequest): UserResponse {
        val user = dao.findByEmail(request.email) ?: throw WrongLoginDataException("Неправильный логин")
        if (!passwordService.verifyPassword(request.password, user.password))
            throw WrongLoginDataException("Неправильный пароль")
        return mapper.entityToResponse(user)
    }
}