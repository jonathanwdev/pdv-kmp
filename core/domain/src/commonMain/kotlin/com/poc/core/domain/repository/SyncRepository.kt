package com.poc.core.domain.repository

interface SyncRepository {
    suspend fun syncProducts(): Unit
}