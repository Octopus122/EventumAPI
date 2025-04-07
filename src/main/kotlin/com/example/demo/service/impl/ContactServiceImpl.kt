package com.example.demo.service.impl

import com.example.demo.database.entity.Contact
import com.example.demo.database.entity.User
import com.example.demo.database.entity.WishList
import com.example.demo.database.repository.ContactDao
import com.example.demo.exception.type.NotFoundException
import com.example.demo.model.request.ContactRequest
import com.example.demo.model.request.CustomGiftRequest
import com.example.demo.model.request.LinkedGiftRequest
import com.example.demo.model.response.ContactResponse
import com.example.demo.model.response.GiftResponse
import com.example.demo.service.ContactService
import com.example.demo.service.GiftService
import com.example.demo.service.UserService
import com.example.demo.util.mapper.ContactMapper
import com.example.demo.util.mapper.GiftMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ContactServiceImpl(
    private val dao: ContactDao,
    private val mapper: ContactMapper,
    private val giftService: GiftService,
    private val giftMapper: GiftMapper
) : ContactService{
    override fun getById(id: Long): Contact = dao.findById(id).orElseThrow{throw NotFoundException("contact")}

    override fun getAll(): List<ContactResponse>  = dao.findAll().map { mapper.entityToResponse(it, getGifts(it.id)) }

    override fun create(request: ContactRequest, hostUser: User, friendUser: User?): ContactResponse {
        val entity = dao.save(mapper.createRequestToEntity(request,hostUser,friendUser))
        return mapper.entityToResponse(entity, getGifts(entity.id))
    }

    override fun update(id: Long, request: ContactRequest, friendUser: User?): ContactResponse {
        val entity = dao.save(mapper
            .updateEntityToResponse(
                dao.findById(id).orElseThrow { throw NotFoundException("contact") },
                request,
                friendUser
            )
        )
        return mapper.entityToResponse(entity, getGifts(entity.id))

    }

    override fun delete(id: Long): String {
        val entity = dao.findById(id).orElseThrow { throw NotFoundException("contact") }
        try {
            for (gift in entity.gifts) {
                giftService.delete(gift.id)
            }
        }catch (_:Exception){}
        dao.delete(entity)
        return "Контакт успешно удален"
    }

    override fun createCustomGift(id: Long, request: CustomGiftRequest): GiftResponse {
        val entity = dao.findById(id).orElseThrow { throw NotFoundException("contact") }
        return giftService.createCustom(request, entity)
    }

    override fun createLinkedGift(id: Long, request: LinkedGiftRequest): GiftResponse {
        val entity = dao.findById(id).orElseThrow { throw NotFoundException("contact") }
        return giftService.createLinked(request, entity)
    }

    override fun getGifts(id: Long) = dao.findById(id).orElseThrow { throw NotFoundException("contact") }.gifts.map {
        giftMapper.entityToResponse(it, giftService.getCount(id))
    }
}