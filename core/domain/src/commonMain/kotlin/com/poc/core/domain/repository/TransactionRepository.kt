package com.poc.core.domain.repository

import com.poc.core.domain.models.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    fun getAllTransactions():  Flow<List<Transaction>>
    suspend fun getById(transactionId: Long): Result<Transaction>
}