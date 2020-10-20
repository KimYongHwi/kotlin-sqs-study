package com.demo.point.component

import com.demo.point.domain.entity.Point
import com.demo.point.domain.repository.PointRepository
import com.demo.point.web.http.dto.CreatePointDto
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component


@Component
class PointQueueConsumer(
	private val pointRepository: PointRepository) {

	@SqsListener(value = ["\${cloud.aws.sqs.endpoint}"], deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	fun conume(@Payload dto: CreatePointDto, @Headers headers: Map<String, String>) {
		try {
			val point = Point(null, dto.memberId, dto.point, dto.sign, dto.code, dto.expiredAt)
			pointRepository.save(point)
		} catch (e: Exception) {
			println(e)
		}
	}
}