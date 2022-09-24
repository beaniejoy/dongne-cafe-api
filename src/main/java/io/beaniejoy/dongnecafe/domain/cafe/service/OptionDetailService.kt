package io.beaniejoy.dongnecafe.domain.cafe.service

import io.beaniejoy.dongnecafe.domain.cafe.error.OptionDetailNotFoundException
import io.beaniejoy.dongnecafe.domain.cafe.model.request.OptionDetailUpdateRequest
import io.beaniejoy.dongnecafe.domain.cafe.repository.OptionDetailRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class OptionDetailService(
    private val optionDetailRepository: OptionDetailRepository
) {
    fun bulkUpdate(resources: List<OptionDetailUpdateRequest>) {
        resources.forEach {
            val optionDetail = optionDetailRepository.findByIdOrNull(it.optionDetailId)
                ?: throw OptionDetailNotFoundException(it.optionDetailId)

            if (it.isDelete) {
                optionDetailRepository.delete(optionDetail)
                return@forEach
            }

            optionDetail.updateInfo(it.name, it.extraPrice)
        }
    }
}