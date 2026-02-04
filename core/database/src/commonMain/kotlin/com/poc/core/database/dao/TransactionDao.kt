package com.poc.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.poc.core.database.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert
    suspend fun insertTransaction(transaction: TransactionEntity): Long

    @Query("SELECT * FROM transactions")
    fun findAllTransactions(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE transactionId = :transactionId")
    suspend fun findTransactionById(transactionId: Long): TransactionEntity?

}