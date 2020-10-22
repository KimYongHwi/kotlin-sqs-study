package com.demo.point.domain.repository

import com.demo.point.domain.cache.MemberPoint
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberPointCacheRepository: CrudRepository<MemberPoint, Long> {}