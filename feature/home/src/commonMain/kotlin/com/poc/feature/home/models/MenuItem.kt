package com.poc.feature.home.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.ui.graphics.vector.ImageVector
import org.jetbrains.compose.resources.StringResource
import pocpdv.feature.home.generated.resources.Res
import pocpdv.feature.home.generated.resources.exchange_desc
import pocpdv.feature.home.generated.resources.exchange_label
import pocpdv.feature.home.generated.resources.new_sale_desc
import pocpdv.feature.home.generated.resources.new_sale_label
import pocpdv.feature.home.generated.resources.product_catalog_desc
import pocpdv.feature.home.generated.resources.product_catalog_label
import pocpdv.feature.home.generated.resources.transaction_desc
import pocpdv.feature.home.generated.resources.transaction_label

data class MenuItem(
    val label: StringResource,
    val destination: MenuDestination,
    val icon: ImageVector,
    val description: StringResource? = null
)

enum class MenuDestination {
    SALE,
    CATALOG,
    EXCHANGE,
    TRANSACTIONS
}


val menuItems = listOf<MenuItem>(
    MenuItem(
        label = Res.string.new_sale_label,
        destination = MenuDestination.SALE,
        description = Res.string.new_sale_desc,
        icon = Icons.Default.ShoppingCart,
    ),
    MenuItem(
        label = Res.string.exchange_label,
        destination = MenuDestination.EXCHANGE,
        description = Res.string.exchange_desc,
        icon = Icons.Default.SwapHoriz,
    ),
    MenuItem(
        label = Res.string.transaction_label,
        destination = MenuDestination.TRANSACTIONS,
        description = Res.string.transaction_desc,
        icon = Icons.Default.History,

        ),
    MenuItem(
        label = Res.string.product_catalog_label,
        destination = MenuDestination.CATALOG,
        description = Res.string.product_catalog_desc,
        icon = Icons.AutoMirrored.Filled.MenuBook,
    )
)
