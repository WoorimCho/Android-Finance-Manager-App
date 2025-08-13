package com.example.accountmanagertrialzone.stocks.room.entity

import com.example.accountmanagertrialzone.room.entity.StockEntity

data class StockState(
    val stocks: List<StockEntity> = emptyList(),
    val name: String = "",
    val marketPrice: Int = 0,
    val stocksOwned: Int = 0,
    val ownedValue: Int = marketPrice * stocksOwned,
    val stockNotes: String = "",
    val isAddingStock: Boolean = false,
    val sortType: StockSortType = StockSortType.STOCK_NAME

)
