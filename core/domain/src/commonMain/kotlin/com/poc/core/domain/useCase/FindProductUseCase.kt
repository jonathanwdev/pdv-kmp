package com.poc.core.domain.useCase

import com.poc.core.domain.models.Product
import com.poc.core.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.flowOn

interface FindProductUseCase {
    fun execute(query: String): Flow<Product?>
}


class FindProductUseCaseImpl(
    private val productRepository: ProductRepository
) : FindProductUseCase {
    override fun execute(query: String): Flow<Product?> {
        val product = productRepository.getProductLocal()
            .flowOn(Dispatchers.IO)
            .onEmpty {
                emitAll(productRepository.getProductRemote().flowOn(Dispatchers.IO))
            }.onEmpty {
                emit(null)
            }.catch {
                emit(null)
            }

        return product
    }
}