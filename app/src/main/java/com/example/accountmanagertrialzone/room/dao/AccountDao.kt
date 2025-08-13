package com.example.accountmanagertrialzone.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.accountmanagertrialzone.room.entity.AccountEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface AccountDao {
//    @Query("SELECT * FROM account")
//    fun getAll(): List<Account>
//
//    @Query("SELECT * FROM account WHERE id = :id")
//    fun get(id: Int): List<Account>

    @Update
    suspend fun update(account: AccountEntity)

    @Upsert
    suspend fun upsert(vararg account: AccountEntity)   //what is vararg?

    @Delete
    suspend fun delete(account: AccountEntity)

    @Query("SELECT * FROM accountEntity ORDER BY name ASC")
    fun getAccountsOrderedByName(): List<AccountEntity> //Flow<List<AccountEntity>>

    @Query("SELECT * FROM accountEntity ORDER BY notes ASC")
    fun getAccountsOrderedByNotes(): Flow<List<AccountEntity>>


//    @Query("DELETE FROM account WHERE id = :id")
//    suspend fun delete(id: Int)
}