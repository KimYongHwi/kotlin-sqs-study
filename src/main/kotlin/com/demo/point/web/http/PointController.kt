package com.demo.point.web.http

import com.demo.point.data.point.SqsPointRepository
import com.demo.point.web.http.dto.CreatePointDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/points"])
class PointController(
	val pointQueueRepository: SqsPointRepository) {

	@PostMapping
	fun savePoint(@RequestBody dto: CreatePointDto) {
		pointQueueRepository.send(dto)
	}
}