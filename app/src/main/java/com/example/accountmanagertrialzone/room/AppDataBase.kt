package com.example.accountmanagertrialzone.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.accountmanagertrialzone.room.dao.AccountDao
import com.example.accountmanagertrialzone.room.dao.StockDao
import com.example.accountmanagertrialzone.room.entity.AccountEntity
import com.example.accountmanagertrialzone.room.entity.StockEntity

@Database(entities = [
    AccountEntity::class,
    StockEntity::class
], version = 1)


abstract class AppDataBase : RoomDatabase() {
    abstract fun getAccountDao(): AccountDao
    abstract fun getStockDao(): StockDao

    companion object {
        lateinit var instance: AppDataBase

        fun getInstance(context: Context): AppDataBase {
            if (::instance.isInitialized) return instance

            synchronized(this) {
                instance = Room.databaseBuilder(
                    context,
                    AppDataBase::class.java, "AccountManager"
                ).build()
                return instance
            }
        }

        fun destroyInstance(context: Context) {
            if (::instance.isInitialized) instance.close()
            instance = null!!
            context.deleteDatabase("AccountManager")
        }
    }

}
