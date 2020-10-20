package com.demo.point.domain.repository

interface PointQueueRepository {
	fun<T> send(payload: T): Unit
}