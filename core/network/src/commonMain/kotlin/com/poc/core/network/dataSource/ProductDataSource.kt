package com.poc.core.network.dataSource

import com.poc.core.network.model.ProductResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface ProductDataSource {
    suspend fun getProducts(): List<ProductResponse>

}


class ProductDataSourceImpl(
    private val httpClient: HttpClient
) : ProductDataSource {
    override suspend fun getProducts(): List<ProductResponse> {
        return httpClient.get("https://fakestoreapi.com/products").body<List<ProductResponse>>()

    }
}