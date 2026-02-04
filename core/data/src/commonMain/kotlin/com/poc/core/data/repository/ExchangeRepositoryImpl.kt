package com.poc.core.data.repository

import androidx.room.Transaction
import com.poc.core.data.mappers.toDomain
import com.poc.core.database.PocPdvDatabase
import com.poc.core.database.entities.ExchangeEntity
import com.poc.core.database.entities.ExchangeItemEntity
import com.poc.core.database.entities.TransactionEntity
import com.poc.core.domain.models.Exchange
import com.poc.core.domain.models.PaymentMethodEnum
import com.poc.core.domain.models.TransactionTypeEnum
import com.poc.core.domain.models.request.CreateExchangeRequest
import com.poc.core.domain.repository.ExchangeRepository
import kotlin.random.Random
import kotlin.time.Clock

class ExchangeRepositoryImpl(
    private val database: PocPdvDatabase
) : ExchangeRepository {
    @Transaction
    override suspend fun createExchange(request: CreateExchangeRequest): Result<Unit> {
        return runCatching {
            val time = Clock.System.now().toEpochMilliseconds()
            val exchangeId = Random.nextLong(100_000, 1_000_000)
            val exchangeEntity = ExchangeEntity(
                exchangeId = exchangeId,
                originalSaleId = request.originalSaleId,
                timestamp = time,
                reason = request.reason,
                creditValue = request.totalCreditValue
            )
            val exchangeItemEntities = request.items.map { itemRequest ->
                ExchangeItemEntity(
                    exchangeItemId = 0,
                    exchangeId = exchangeId,
                    saleItemId = itemRequest.saleItemId,
                    quantity = itemRequest.quantity,
                    creditValue = itemRequest.creditValue,
                    reason = itemRequest.reason
                )
            }
            database.exchangeDao.performExchangeTransaction(
                exchange = exchangeEntity,
                items = exchangeItemEntities
            )
            database.transactionDao.insertTransaction(
                TransactionEntity(
                    timestamp = time,
                    type = TransactionTypeEnum.EXCHANGE.name,
                    amount = request.totalCreditValue,
                    paymentMethod = PaymentMethodEnum.CREDIT_CARD.name,
                    referenceId = exchangeId
                )
            )
            Result.success(Unit)
        }
    }

    override suspend fun findExchangeById(id: Long): Result<Exchange> {
        return runCatching {
            database.exchangeDao.getExchangeDeepDetails(id)?.toDomain() ?: throw Exception("Exchange not found")
        }
    }

}