package com.poc.feature.sale.screens.bag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.poc.core.domain.repository.ProductRepository
import com.poc.core.presentation.format.formatMoney
import com.poc.feature.sale.mappers.toSaleItemUi
import com.poc.feature.sale.models.SaleFlowData
import com.poc.core.domain.models.SaleItemUI
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class BagViewModel(
    private val saleFlowData: SaleFlowData,
    private val productRepository: ProductRepository
) : ViewModel() {
    private val _event = Channel<BagEvent>()
    val event = _event.receiveAsFlow()

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(BagState())


    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                val saleId = Random.nextLong(100_000, 1_000_000)
                _state.update {
                    it.copy(
                        saleId = saleId,
                    )
                }
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = BagState()
        )

    private fun BagState.recalculateTotals(newItems: List<SaleItemUI>): BagState {
        val totalWithTax = newItems.sumOf { it.totalPrice * it.quantity }
        val subtotal = newItems.sumOf { it.unitPrice * it.quantity }
        val totalTax = newItems.sumOf { it.tax * it.quantity }
        return this.copy(
            items = newItems,
            subtotalAmount = subtotal,
            subtotalAmountFormatted = subtotal.formatMoney(),
            totalTaxAmount = totalTax,
            totalTaxAmountFormatted = totalTax.formatMoney(),
            totalAmount = totalWithTax,
            totalAmountFormatted = totalWithTax.formatMoney(),
            productSku = ""
        )
    }


    private fun findProductBySku() {
        if(_state.value.productSku.isBlank()) return
        viewModelScope.launch {
            productRepository
                .getProductBySkuLocal(_state.value.productSku)
                .fold(
                    onSuccess = { product ->
                        _state.update { prevState ->
                            val productUi = product.toSaleItemUi()
                            val newList = if (prevState.items.any { it.productSku == productUi.productSku }) {
                                prevState.items.map { item ->
                                    if (_state.value.productSku == item.productSku.toString()) {
                                        item.copy(quantity = item.quantity + 1)
                                    } else item
                                }
                            } else {
                                prevState.items + productUi
                            }
                            prevState.recalculateTotals(newList)
                        }
                    },
                    onFailure = {
                        _event.send(BagEvent.OnFindProductError)
                        _state.update { prevState ->
                            prevState.copy(
                                productSku = ""
                            )
                        }
                    }
                )
        }
    }

    private fun onAddItemClick(sku: Int) {
        _state.update { prevState ->
            val newList = prevState.items.map { item ->
                if (item.productSku == sku) item.copy(quantity = item.quantity + 1) else item
            }
            prevState.recalculateTotals(newList)
        }
    }

    private fun onRemoveItemClick(sku: Int) {
        _state.update { prevState ->
            val newList = prevState.items.mapNotNull { item ->
                if (item.productSku == sku) {
                    if (item.quantity > 1) item.copy(quantity = item.quantity - 1) else null
                } else item
            }
            prevState.recalculateTotals(newList)
        }
    }

    private fun updateSaleFlowState() {
        saleFlowData.saleFlowState = saleFlowData.saleFlowState.copy(
            saleId = _state.value.saleId,
            taxPercentage = _state.value.taxPercentage,
            totalAmount = _state.value.totalAmount,
            totalAmountFormatted = _state.value.totalAmountFormatted,
            subtotalAmount = _state.value.subtotalAmount,
            subtotalAmountFormatted = _state.value.subtotalAmountFormatted,
            totalTaxAmount = _state.value.totalTaxAmount,
            totalTaxAmountFormatted = _state.value.totalTaxAmountFormatted,
            items = _state.value.items
        )
    }

    fun onAction(action: BagAction) {
        when (action) {
            BagAction.OnSearchProduct -> findProductBySku()
            is BagAction.OnChangeSkuInput -> _state.update { it.copy(productSku = action.sku) }
            is BagAction.OnAddMoreItems -> onAddItemClick(action.sku)
            is BagAction.OnRemoveItems -> onRemoveItemClick(action.sku)
            BagAction.OnChargeClick -> updateSaleFlowState()
            else -> Unit
        }
    }

}