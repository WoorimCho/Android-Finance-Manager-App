package com.example.accountmanagertrialzone.stocks.room.entity

import com.example.accountmanagertrialzone.room.entity.StockEntity

sealed interface StockEvent {
    object SaveStock: StockEvent
    data class SetStockName(val name: String): StockEvent
    data class SetStockPrice(val marketPrice: Int): StockEvent
    data class SetStocksOwned(val stocksOwned: Int): StockEvent
    data class SetStockNotes(val stockNotes: String): StockEvent

    object ShowDialog: StockEvent
    object HideDialog: StockEvent

    data class SortStocks(val sortType: StockSortType): StockEvent
    data class DeleteStock(val stock: StockEntity): StockEvent

}