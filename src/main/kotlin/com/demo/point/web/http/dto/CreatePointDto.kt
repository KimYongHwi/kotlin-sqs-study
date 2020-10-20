package com.demo.point.web.http.dto

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import java.time.LocalDateTime

data class CreatePointDto @JsonCreator constructor(
	@JsonProperty("memberId") val memberId: Long,
	@JsonProperty("point") val point: Int,
	@JsonProperty("sign") val sign: String,
	@JsonProperty("code") val code: String,

	@JsonProperty("expiredAt")
	@JsonDeserialize(using = LocalDateTimeDeserializer::class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	val expiredAt: LocalDateTime
)