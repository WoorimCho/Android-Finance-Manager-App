package com.example.accountmanagertrialzone.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.accountmanagertrialzone.room.dao.AccountDao
import com.example.accountmanagertrialzone.room.entity.AccountEntity

@Database(entities = [AccountEntity::class], version = 1)
abstract class AccountDatabase : RoomDatabase() {
    abstract val dao: AccountDao
}