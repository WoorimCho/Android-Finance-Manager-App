package com.example.accountmanagertrialzone.screen.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.accountmanagertrialzone.room.dao.AccountDao
import com.example.accountmanagertrialzone.room.entity.AccountEntity
import com.example.accountmanagertrialzone.screen.types.SortType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class AccountViewModel(
    private val dao : AccountDao
): ViewModel() {
    private val _sortType = MutableStateFlow(SortType.ACCOUNT_NAME)
    private val _accounts = _sortType.flatMapLatest { sortType ->
        when(sortType){
            SortType.ACCOUNT_NAME -> dao.getAccountsOrderedByName()
            SortType.ACCOUNT_NOTE -> dao.getAccountsOrderedByNotes()
        }
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(AccountState())
    val state = combine(_state, _sortType, _accounts) { state, sortType, accounts ->
        state.copy(
            accounts = accounts,
            sortType = sortType
        )
    }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AccountState())

    fun onEvent(event: AccountEvent){
        when(event) {
            is AccountEvent.DeleteAccount -> {
                viewModelScope.launch {
                    dao.delete(event.account)
                }
            }
            AccountEvent.HideDialog ->{
                _state.update { it.copy(
                    isAddingAccount = false
                )}
            }
            AccountEvent.SaveAccount ->{
                val name = state.value.name
                val notes = state.value.notes
                val availableAmount = state.value.availableAmount
                val investedAmount = state.value.investedAmount
                val amount = state.value.amount

                if (name.isBlank() || notes.isBlank()){
                    return
                }
                val account = AccountEntity(
                    name = name,
                    notes = notes,
                    availableAmount = availableAmount,
                    investedAmount = investedAmount,
                    amount = amount
                )
                viewModelScope.launch {
                    dao.upsert(account)
                }
                _state.update { it.copy(
                    isAddingAccount = false,
                    name = "",
                    notes = "",
                    availableAmount = 0,
                    investedAmount = 0,
                    amount = 0
                ) }

            }
            is AccountEvent.SetAccountAmount -> {
                _state.update { it.copy(
                    amount = event.amount
                )}
            }
            is AccountEvent.SetAccountName ->{
                _state.update { it.copy(
                    name = event.name
                )}
            }
            is AccountEvent.SetAvailableAmount ->{
                _state.update { it.copy(
                    availableAmount = event.availableAmount
                )}
            }
            is AccountEvent.SetInvestedAmount ->{
                _state.update { it.copy(
                    investedAmount = event.investedAmount
                )}
            }
            is AccountEvent.SetNotes -> {
                _state.update { it.copy(
                    notes = event.notes
                )}
            }
            AccountEvent.ShowDialog -> {
                _state.update { it.copy(
                isAddingAccount = true
                ) }
            }
            is AccountEvent.SortAccounts ->{
                _sortType.value = event.sortType
            }

        }
    }
}

/**
 * AccountViewModel takes its DAO as a constructor argument, so it needs a factory.
 */
class AccountViewModelFactory(
    private val dao: AccountDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            "Unknown ViewModel class: $modelClass"
        }
        return AccountViewModel(dao) as T
    }
}

