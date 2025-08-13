package com.example.accountmanagertrialzone

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.accountmanagertrialzone.room.AppDataBase
import com.example.accountmanagertrialzone.screen.account.AccountEvent
import com.example.accountmanagertrialzone.screen.account.AccountState
import com.example.accountmanagertrialzone.screen.account.AddAccountDialog
import com.example.accountmanagertrialzone.screen.stock.StockDialog
import com.example.accountmanagertrialzone.screen.types.SortType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    state: AccountState,
    onEvent: (AccountEvent) -> Unit,
    db: AppDataBase
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(AccountEvent.ShowDialog)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = "Add Account"
                )
            }
        },
        modifier = Modifier.padding(16.dp)
    )
    {  padding ->
        if(state.isAddingAccount){
            AddAccountDialog(state = state, onEvent = onEvent)
        }

        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        )
        {
            item{
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    SortType.values().forEach { sortType ->
                        Row(
                            modifier = Modifier
                                .clickable {
                                    onEvent(AccountEvent.SortAccounts(sortType))
                                },
                            verticalAlignment = Alignment.CenterVertically
                        )
                        {
                            RadioButton(
                                selected = state.sortType == sortType,
                                onClick = {
                                    onEvent(AccountEvent.SortAccounts(sortType))
                                }
                            )
                            Text(text = sortType.name)
                        }
                    }
                }
            }
            items(state.accounts){account ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Text(text = account.name, //removed .name
                                fontSize = 20.sp
                            )
                            Text(text = "Amount: $${account.amount}",
                                    fontSize = 15.sp,
                                    color = androidx.compose.ui.graphics.Color.Black
                            )
                        }
                        Text(text = account.notes,
                            fontSize = 12.sp,
                            color = androidx.compose.ui.graphics.Color.DarkGray
                        )
                    }
                    IconButton(onClick = {
                        onEvent(AccountEvent.DeleteAccount(account))
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Account")
                    }

                }
            }
            item {
                //Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth()) {
                    IconButton(onClick = {
                        //StockDialog(db.getStockDao()).addStock()
                    })  {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add Stock")
                    }
                }
            }
        }
    }
}