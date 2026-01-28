package com.poc.feature.sale.models

import androidx.compose.ui.graphics.vector.ImageVector
import com.poc.core.domain.models.PaymentMethodEnum
import org.jetbrains.compose.resources.StringResource

data class PaymentListItem(
    val methodEnum: PaymentMethodEnum,
    val icon: ImageVector,
    val label: StringResource,
)
