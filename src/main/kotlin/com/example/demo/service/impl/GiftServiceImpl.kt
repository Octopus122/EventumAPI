package com.example.demo.service.impl

import com.example.demo.database.entity.Contact
import com.example.demo.database.entity.GiftState
import com.example.demo.database.entity.Present
import com.example.demo.database.entity.User
import com.example.demo.database.repository.GiftDao
import com.example.demo.exception.type.NotFoundException
import com.example.demo.model.request.CustomGiftRequest
import com.example.demo.model.request.LinkedGiftRequest
import com.example.demo.model.request.PresentRequest
import com.example.demo.model.response.GiftResponse
import com.example.demo.service.GiftService
import com.example.demo.service.GiftStateService
import com.example.demo.service.PresentService
import com.example.demo.util.mapper.GiftMapper
import org.springframework.stereotype.Service

@Service
class GiftServiceImpl(
    private val dao: GiftDao,
    private val mapper: GiftMapper,
    private val presentService: PresentService,
    private val stateService: GiftStateService
): GiftService {
    override fun createCustom(request: CustomGiftRequest, contact: Contact): GiftResponse {
        val state = stateService.getByName("custom")
        if (state != null){
            val present = presentService.create(PresentRequest(
                request.title,
                request.description
            ))
            val entity = dao.save(mapper.createRequestToResponse(
                present,
                state,
                contact
            ))
            return mapper.entityToResponse(entity, dao.getGiftNumber(entity.id))
        }
        else throw Exception("Ошибка поиска состояния подарка")
    }

    override fun createLinked(request: LinkedGiftRequest, contact: Contact): GiftResponse {
        val state = stateService.getByName("linked")
        if (state != null){
            val present = presentService.getEntityById(request.presentId)
            val entity = dao.save(mapper.createRequestToResponse(
                present,
                state,
                contact
            ))
            return mapper.entityToResponse(entity, dao.getGiftNumber(entity.id))

        }
        else throw Exception("Ошибка поиска состояния подарка")
    }

    override fun getById(id: Long): GiftResponse {
        val entity = dao.findById(id).orElseThrow{throw NotFoundException("gift")}
        return mapper.entityToResponse(
            entity,
            if (entity.state.name == "custom") null else dao.getGiftNumber(id)
        )
    }

    override fun getAll(): List<GiftResponse> = dao.findAll().map {
        mapper.entityToResponse(it, if (it.state.name == "custom") null else dao.getGiftNumber(it.id))
    }

    override fun updateCustom(id: Long, request: CustomGiftRequest): GiftResponse {
        val entity = dao.findById(id).orElseThrow{throw NotFoundException("gift")}
        if (entity.state.name != "custom") throw Exception("Нельзя изменить данный подарок")
        presentService.update(entity.present.id, PresentRequest(
            request.title,
            request.description
        )
        )
        return mapper.entityToResponse(
            dao.findById(id).orElseThrow{throw NotFoundException("gift")},
            if (entity.state.name == "custom") null else dao.getGiftNumber(id)
        )
    }

    override fun delete(id: Long): String {
        val entity = dao.findById(id).orElseThrow{throw NotFoundException("gift")}
        var presentId: Long? = null
        if (
            entity.state.name == "custom"  ||
            (entity.state.name == "deleted" && dao.getGiftNumber(entity.present.id)<= 1)
            ) presentId = entity.present.id
        dao.delete(entity)
        if (presentId != null) presentService.delete(presentId)
        return "Удаление прошло успешно"
    }

    override fun getCount(id: Long): Long? = dao.getGiftNumber(id)
}