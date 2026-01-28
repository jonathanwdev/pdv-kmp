package com.poc.feature.exchange.screens.selectItems

import kotlin.collections.listOf

data class ReturnItem(
    val id: String,
    val name: String,
    val details: String,
    val price: Double,
    val imageUrl: String,
)

val returnItemsMock = listOf<ReturnItem>(
    ReturnItem(
        id = "1",
        name = "Classic Linen Shirt",
        details = "Navy / Large / SKU-1044",
        price = 85.00,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDw8TrK82Bd3mMXOZxcRQ8vdg25q1ba7f7QSueeX7JsIbQICv1AI7ylhOfLkdgIlx-Kylog3mKyliNH31haF9oOQo39yNvw4dMfLP356mAeVscNvh5pygN9eXkmYusvmBinR9S2TcvjDDNB0O_cryXkumcY7BYxrMiBNIRYydCwR_iicwjgLIOPnSVE-Wq5gt84cneQPHZn8y7PRngEUw0fYQI6clYIXw6LzQXU2LmmPBQcqx4WbiDOFUCABTp4NnaxCihBk9-qssU",
    ),
    ReturnItem(
        id = "2",
        name = "Slim Fit Chinos",
        details = "Khaki / 32 / SKU-2291",
        price = 95.00,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAT4ZJ_Nxpk8fuehwUu341NKj9ZMSEt9nx9wBKSZ-6Tb8U1VMuIbMIsVD0V9ronL2VIdS0asDRFSF87VgRJyDfgXFHP0ZAyQmHcpQDsIBM3HksS9x-cDxLGW-v2FC_nY01ZNx2pAM9agGOcVobqfOkB4gCZ7tFgT5bXQwF8GTENnl1m0tabaRyocv4T-wId4glL3piRioA3FypPO5l-p8NX4Db5DG_hcnmMgDQ_lj140vwx7BOyPmMLyGWfNsdl3-RzJPMN0aJpYWQ",
    ),
    ReturnItem(
        id = "3",
        name = "Tech Runner Shoes",
        details = "Crimson / 10 / SKU-8821",
        price = 120.00,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuB9uK6ovSTDEZ_NfHu6h082U97cJvQP-Ux79eOw9FsaOWC3QK0XmiLH_uqDQSwnTi8bKY7GxQWcfxFrKvfYJ6B1Zl2I7iU9Dxcw1JVA6E8FMk1gsGB-wUbOFZwzc8g8L7thc1k7o5pQZeihCwQ324o6MzZkhlll7hkcBxSN6W1x1KJufLW_bLtlz0rxaA-Wz6WTOdbGbXB2E2BySbsyAPDuxR6JR8yclEn60Gtsth0eynoScOFUlWlJVmvVYV2atz6b9AV0RXcs9Ec",
    ),
    ReturnItem(
        id = "1",
        name = "Classic Linen Shirt",
        details = "Navy / Large / SKU-1044",
        price = 85.00,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDw8TrK82Bd3mMXOZxcRQ8vdg25q1ba7f7QSueeX7JsIbQICv1AI7ylhOfLkdgIlx-Kylog3mKyliNH31haF9oOQo39yNvw4dMfLP356mAeVscNvh5pygN9eXkmYusvmBinR9S2TcvjDDNB0O_cryXkumcY7BYxrMiBNIRYydCwR_iicwjgLIOPnSVE-Wq5gt84cneQPHZn8y7PRngEUw0fYQI6clYIXw6LzQXU2LmmPBQcqx4WbiDOFUCABTp4NnaxCihBk9-qssU",
    ),
    ReturnItem(
        id = "2",
        name = "Slim Fit Chinos",
        details = "Khaki / 32 / SKU-2291",
        price = 95.00,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAT4ZJ_Nxpk8fuehwUu341NKj9ZMSEt9nx9wBKSZ-6Tb8U1VMuIbMIsVD0V9ronL2VIdS0asDRFSF87VgRJyDfgXFHP0ZAyQmHcpQDsIBM3HksS9x-cDxLGW-v2FC_nY01ZNx2pAM9agGOcVobqfOkB4gCZ7tFgT5bXQwF8GTENnl1m0tabaRyocv4T-wId4glL3piRioA3FypPO5l-p8NX4Db5DG_hcnmMgDQ_lj140vwx7BOyPmMLyGWfNsdl3-RzJPMN0aJpYWQ",
    ),
    ReturnItem(
        id = "3",
        name = "Tech Runner Shoes",
        details = "Crimson / 10 / SKU-8821",
        price = 120.00,
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuB9uK6ovSTDEZ_NfHu6h082U97cJvQP-Ux79eOw9FsaOWC3QK0XmiLH_uqDQSwnTi8bKY7GxQWcfxFrKvfYJ6B1Zl2I7iU9Dxcw1JVA6E8FMk1gsGB-wUbOFZwzc8g8L7thc1k7o5pQZeihCwQ324o6MzZkhlll7hkcBxSN6W1x1KJufLW_bLtlz0rxaA-Wz6WTOdbGbXB2E2BySbsyAPDuxR6JR8yclEn60Gtsth0eynoScOFUlWlJVmvVYV2atz6b9AV0RXcs9Ec",
    )
)

data class SelectItemsState(
    val transactionId: String = "#44921-X",
    val currentStep: Int = 2,
    val returnItems: List<ReturnItem> = returnItemsMock
){
        val selectedItems: List<ReturnItem>
        get() = returnItems

        val valueOfReturns: Double
        get() = selectedItems.sumOf { it.price }
}



