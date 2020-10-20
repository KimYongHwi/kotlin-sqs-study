package com.demo.point.common.config

import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
@EnableSqs
class SqsConfig {

	@Value("\${cloud.aws.region.static}")
	private val region: String? = null

	@Value("\${cloud.aws.sqs.endpoint}")
	private val endpoint: String? = null

	@Value("\${cloud.aws.sqs.name}")
	private val sqsName: String? = null

	@Bean
	@Primary
	fun amazonSQSAsync(): AmazonSQSAsync? {
		return AmazonSQSAsyncClientBuilder
			.standard()
			.withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(endpoint, region))
			.build()
	}

	@Bean
	fun queueMessagingTemplate(amazonSQSAsync: AmazonSQSAsync?): QueueMessagingTemplate? {
		val queueMessagingTemplate = QueueMessagingTemplate(amazonSQSAsync)
		queueMessagingTemplate.setDefaultDestinationName(sqsName)

		return queueMessagingTemplate
	}

//	@Bean(name = ["sqsAsyncTaskExecutor"])
//	fun asyncTaskExecutor(@Value("\${cloud.aws.sqs.core-thread-count}") coreThreadCount: Int,
//	                      @Value("\${cloud.aws.sqs.max-thread-count}") maxThreadCount: Int,
//	                      @Value("\${cloud.aws.sqs.queue-capacity}") queueCapacity: Int): AsyncTaskExecutor? {
//
//		val asyncTaskExecutor = ThreadPoolTaskExecutor()
//		asyncTaskExecutor.corePoolSize = coreThreadCount
//		asyncTaskExecutor.maxPoolSize = maxThreadCount
//		asyncTaskExecutor.setQueueCapacity(queueCapacity)
//		asyncTaskExecutor.setThreadNamePrefix("threadPoolExecutor-SimpleMessageListenerContainer-")
//		asyncTaskExecutor.initialize()
//
//		return asyncTaskExecutor
//	}

//	@Bean
//	fun simpleMessageListenerContainerFactory(amazonSQS: AmazonSQSAsync?, @Qualifier("sqsAsyncTaskExecutor") asyncTaskExecutor: AsyncTaskExecutor?): SimpleMessageListenerContainerFactory? {
//		val simpleMessageListenerContainerFactory = SimpleMessageListenerContainerFactory()
//		simpleMessageListenerContainerFactory.setTaskExecutor(asyncTaskExecutor)
//
//		return simpleMessageListenerContainerFactory
//	}
}