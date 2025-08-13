package com.example.accountmanagertrialzone.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class StockEntity (
    val name: String = "",
    val marketPrice: Int = 0,
    val stocksOwned: Int = 0,
    val ownedValue: Int = marketPrice * stocksOwned,
    val stockNotes: String = "",

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)