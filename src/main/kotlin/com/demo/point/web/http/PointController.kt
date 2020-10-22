package com.demo.point.web.http

import com.demo.point.data.point.SqsPointRepository
import com.demo.point.domain.cache.MemberPoint
import com.demo.point.domain.repository.MemberPointCacheRepository
import com.demo.point.web.http.dto.CreatePointDto
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = ["/points"])
class PointController(
	val pointQueueRepository: SqsPointRepository,
	val memberPointCacheRepository: MemberPointCacheRepository) {

	@PostMapping
	fun savePoint(@RequestBody dto: CreatePointDto) {
		pointQueueRepository.send(dto)
	}

	@GetMapping(value = ["/total/{memberId}"])
	fun getTotalPoint(@PathVariable memberId: Long): Optional<MemberPoint> {
		return memberPointCacheRepository.findById(memberId)
	}
}