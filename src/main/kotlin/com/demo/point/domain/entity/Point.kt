package com.demo.point.domain.entity

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinToString
import org.hibernate.annotations.GenericGenerator
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Point(
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)")
	private val id: UUID? = null,

	@Column(name = "member_id")
	private val memberId: Long,

	@Column(name = "point")
	private val point: Int,

	@Column(name = "sign")
	private val sign: String,

	@Column(name = "code")
	private val code: String,

	@Column(name = "expired_at")
	private val expiredAt: LocalDateTime? = null,

	@CreatedDate
	@Column(name = "created_at")
	private val createdAt: LocalDateTime? = LocalDateTime.now()
) {
	override fun toString() = kotlinToString(properties = toStringProperties)
	override fun equals(other: Any?) = kotlinEquals(other = other, properties = equalsAndHashCodeProperties)

	companion object {
		private val equalsAndHashCodeProperties = arrayOf(Point::id)
		private val toStringProperties = arrayOf(
			Point::id,
			Point::memberId,
			Point::point,
			Point::sign,
			Point::code,
			Point::expiredAt,
			Point::createdAt,
		)
	}
}