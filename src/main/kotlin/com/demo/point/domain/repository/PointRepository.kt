package com.demo.point.domain.repository

import com.demo.point.domain.entity.Point
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PointRepository: JpaRepository<Point, UUID>