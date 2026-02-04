package com.poc.core.domain.repository

import com.poc.core.domain.models.Exchange
import com.poc.core.domain.models.request.CreateExchangeRequest

interface ExchangeRepository {
    suspend fun createExchange(request: CreateExchangeRequest): Result<Unit>
    suspend fun findExchangeById(id: Long): Result<Exchange>
}