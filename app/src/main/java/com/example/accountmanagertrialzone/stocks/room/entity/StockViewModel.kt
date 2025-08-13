package com.example.accountmanagertrialzone.stocks.room.entity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.accountmanagertrialzone.room.dao.StockDao
import com.example.accountmanagertrialzone.room.entity.StockEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class StockViewModel(
    private val dao : StockDao
): ViewModel() {
    private val _sortType = MutableStateFlow(StockSortType.STOCK_NAME)
    private val _stocks = _sortType.flatMapLatest { sortType ->
        when (sortType) {
            StockSortType.STOCK_ID -> dao.getStocksOrderedById()
            StockSortType.STOCK_NAME -> dao.getStocksOrderedByName()
            StockSortType.STOCK_PRICE -> dao.getStocksOrderedByPrice()
            StockSortType.STOCK_NOTES -> dao.getStocksOrderedByNotes()
            StockSortType.STOCK_OWNED -> dao.getStocksOrderedByOwned()
            StockSortType.STOCK_VALUE -> dao.getStocksOrderedByOwnedValue()
        }
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(StockState())
    val state = combine (_state, _sortType, _stocks) { state, sortType, stocks ->
        state.copy(
            stocks = stocks,
            sortType = sortType
        )
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), StockState())

    fun onEvent(event: StockEvent) {
        when(event) {
            is StockEvent.DeleteStock -> {
                viewModelScope.launch {
                    dao.delete(event.stock)
                }
            }

            StockEvent.HideDialog -> {
                _state.update {
                    it.copy(
                        isAddingStock = false
                    )
                }
            }

            StockEvent.SaveStock -> {
                val name = state.value.name
                val notes = state.value.stockNotes
                val marketPrice = state.value.marketPrice
                val stocksOwned = state.value.stocksOwned
                val ownedValue = state.value.ownedValue

                if (name.isBlank() || notes.isBlank()) {  //catching any errors that may occur due to empty field(s)
                    return
                }
                val stock = StockEntity(
                    name = name,
                    stockNotes = notes,
                    marketPrice = marketPrice,
                    stocksOwned = stocksOwned,
                    ownedValue = ownedValue
                )
                viewModelScope.launch {
                    dao.upsert(stock)
                }
                _state.update {
                    it.copy(
                        isAddingStock = false,
                        name = "",
                        stockNotes = "",
                        marketPrice = 0,
                        stocksOwned = 0,
                        ownedValue = 0
                    )
                }
            }
                is StockEvent.SetStockName -> {
                    _state.update {
                        it.copy(
                            name = event.name
                        )
                    }
                }
            is StockEvent.SetStockNotes -> {
                _state.update {
                    it.copy(
                        stockNotes = event.stockNotes
                    )
                }
            }
            is StockEvent.SetStockPrice -> {
                _state.update {
                    it.copy(
                        marketPrice = event.marketPrice
                    )
                }
            }
            is StockEvent.SetStocksOwned -> {
                _state.update {
                    it.copy(
                        stocksOwned = event.stocksOwned
                    )
                }
            }
            StockEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        isAddingStock = true
                    )
                }
            }
            is StockEvent.SortStocks -> {
                _sortType.value = event.sortType
            }
        }
    }
}