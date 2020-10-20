package com.demo.point.data.point

import com.demo.point.domain.repository.PointQueueRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.cloud.aws.messaging.core.SqsMessageHeaders
import org.springframework.stereotype.Repository
import java.util.*


@Repository
class SqsPointRepository(
	@Value("\${cloud.aws.sqs.group.id}")
	private val sqsGroupId: String,

	@Value("\${cloud.aws.sqs.name}")
	private val sqsName: String,

	private val queueMessageTemplate: QueueMessagingTemplate,
) : PointQueueRepository {

	override fun <T> send(payload: T) {
		val headers: MutableMap<String, Any> = HashMap()
		headers[SqsMessageHeaders.SQS_GROUP_ID_HEADER] = sqsGroupId
		headers[SqsMessageHeaders.SQS_DEDUPLICATION_ID_HEADER] = UUID.randomUUID().toString()

		queueMessageTemplate.convertAndSend(sqsName, payload, headers)
	}
}