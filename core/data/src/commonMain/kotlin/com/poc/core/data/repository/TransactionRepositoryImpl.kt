package com.poc.core.data.repository

import com.poc.core.data.mappers.toDomain
import com.poc.core.database.PocPdvDatabase
import com.poc.core.domain.models.Transaction
import com.poc.core.domain.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope

class TransactionRepositoryImpl(
    private val database: PocPdvDatabase
) : TransactionRepository {
    override fun getAllTransactions(): Flow<List<Transaction>> {
        return database
            .transactionDao
            .findAllTransactions()
            .map { transaction ->
                supervisorScope {
                    transaction.map { it.toDomain() }
                }
            }

    }

    override suspend fun getById(transactionId: Long): Result<Transaction> {
        return runCatching {
            database
                .transactionDao
                .findTransactionById(transactionId)
                ?.toDomain() ?: throw Exception("Transaction not found")
        }
    }

}