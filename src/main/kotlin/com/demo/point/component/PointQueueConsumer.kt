package com.demo.point.component

import com.demo.point.domain.cache.MemberPoint
import com.demo.point.domain.entity.Point
import com.demo.point.domain.repository.MemberPointCacheRepository
import com.demo.point.domain.repository.PointRepository
import com.demo.point.web.http.dto.CreatePointDto
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class PointQueueConsumer(
	private val pointRepository: PointRepository,
	private val memberPointCacheRepository: MemberPointCacheRepository) {

	@SqsListener(value = ["\${cloud.aws.sqs.endpoint}"], deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
	fun consume(@Payload dto: CreatePointDto, @Headers headers: Map<String, String>) {
		try {
			val point = Point(null, dto.memberId, dto.point, dto.sign, dto.code, dto.expiredAt)
			val memberPoint: MemberPoint = memberPointCacheRepository.findById(dto.memberId).orElse(MemberPoint(dto.memberId, 0))
			val totalPoint: Int = when (dto.sign) {
				"+" -> dto.point + memberPoint.totalPoint
				else -> {
					if (memberPoint.totalPoint == 0) 0
					else memberPoint.totalPoint - dto.point
				}
			}

			pointRepository.save(point)
			memberPointCacheRepository.save(MemberPoint(dto.memberId, totalPoint))
		} catch (e: Exception) {
			println(e)
		}
	}
}