package com.example.accountmanagertrialzone.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.accountmanagertrialzone.room.entity.StockEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StockDao {
    @Update
    fun update(stock: StockEntity)

    @Upsert
    fun upsert(stock: StockEntity)

    @Delete
    fun delete(stock: StockEntity)

    @Query("SELECT * FROM StockEntity Order By id ASC")
    fun getStocksOrderedById(): Flow<List<StockEntity>>

    @Query("SELECT * FROM StockEntity Order By name ASC")
    fun getStocksOrderedByName(): Flow<List<StockEntity>>

    @Query("SELECT * FROM StockEntity Order By marketPrice ASC")
    fun getStocksOrderedByPrice(): Flow<List<StockEntity>>

    @Query("SELECT * FROM StockEntity Order By stocksOwned ASC")
    fun getStocksOrderedByOwned(): Flow<List<StockEntity>>

    @Query("SELECT * FROM StockEntity Order By ownedValue ASC")
    fun getStocksOrderedByOwnedValue(): Flow<List<StockEntity>>

    @Query("SELECT * FROM StockEntity Order By stockNotes ASC")
    fun getStocksOrderedByNotes(): Flow<List<StockEntity>>

}