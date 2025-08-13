package com.example.accountmanagertrialzone.screen.account

import com.example.accountmanagertrialzone.room.entity.AccountEntity
import com.example.accountmanagertrialzone.screen.types.SortType

sealed interface AccountEvent {
    object SaveAccount: AccountEvent
    data class SetAccountName(val name: String): AccountEvent
    data class SetAccountAmount(val amount: Int): AccountEvent
    data class SetAvailableAmount(val availableAmount: Int): AccountEvent
    data class SetInvestedAmount(val investedAmount: Int): AccountEvent
    data class SetNotes(val notes: String): AccountEvent

    object ShowDialog: AccountEvent
    object HideDialog: AccountEvent
    data class SortAccounts(val sortType: SortType): AccountEvent
    data class DeleteAccount(val account: AccountEntity): AccountEvent

}