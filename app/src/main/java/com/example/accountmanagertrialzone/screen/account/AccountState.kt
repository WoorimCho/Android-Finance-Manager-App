package com.example.accountmanagertrialzone.screen.account

import com.example.accountmanagertrialzone.room.entity.AccountEntity
import com.example.accountmanagertrialzone.screen.types.SortType

data class AccountState(
    val accounts: List<AccountEntity> = emptyList(),
    val name: String = "",
    val amount: Int = 0,
    val investedAmount: Int = 0,
    val availableAmount: Int = 0,
    val notes: String = "",
    val isAddingAccount: Boolean = false,
    val sortType: SortType = SortType.ACCOUNT_NAME


    )
