package com.demo.point.domain.cache

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.annotation.Id

@RedisHash("members")
data class MemberPoint(
	@Id
	val memberId: Long,
	val totalPoint: Int) {
}