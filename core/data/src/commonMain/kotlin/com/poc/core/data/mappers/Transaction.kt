package com.poc.core.data.mappers

import com.poc.core.database.entities.SaleEntity
import com.poc.core.database.entities.TransactionEntity
import com.poc.core.domain.models.PaymentMethodEnum
import com.poc.core.domain.models.Sale
import com.poc.core.domain.models.Transaction
import com.poc.core.domain.models.TransactionTypeEnum


fun Transaction.toEntity(): TransactionEntity {
    return TransactionEntity(
        timestamp = timestamp,
        amount = amount,
        type = type.name,
        paymentMethod = paymentMethod.name,
        referenceId = referenceId
    )
}


fun TransactionEntity.toDomain(): Transaction {
    return Transaction(
        transactionId = transactionId,
        timestamp = timestamp,
        amount = amount,
        type = TransactionTypeEnum.valueOf(type),
        paymentMethod = PaymentMethodEnum.valueOf(paymentMethod),
        referenceId = referenceId
    )
}

fun SaleEntity.toTransactionEntity() : TransactionEntity{
    return TransactionEntity(
        timestamp = timestamp,
        amount = totalValue,
        type = TransactionTypeEnum.SALE.name,
        paymentMethod = paymentMethod,
        referenceId = saleId
    )
}
