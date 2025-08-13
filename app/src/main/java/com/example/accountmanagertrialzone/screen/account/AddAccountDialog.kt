package com.example.accountmanagertrialzone.screen.account


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api  //is this necessary???
@Composable
fun AddAccountDialog(
    state: AccountState,
    onEvent: (AccountEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = Modifier,
        onDismissRequest = {
                           onEvent(AccountEvent.HideDialog)
                           },
        title = { Text(text = "Add Account") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.name,
                    onValueChange = {
                        onEvent(AccountEvent.SetAccountName(it))
                    },
                    placeholder = {
                        Text(text = "Account Name")
                    }
                )
                TextField(
                    value = state.amount.toString(),  //Value needs to be String class
                    onValueChange = {
                                    onEvent(AccountEvent.SetAccountAmount(it.toIntOrNull() ?: 0))
                    },
                    placeholder = {
                        Text(text = "Account Amount")
                    }
                )
                TextField(
                    value = state.notes,
                    onValueChange = {
                        onEvent(AccountEvent.SetNotes(it))
                    },
                    placeholder = {
                        Text(text = "Account Notes")
                    }
                )
            }
        },
        confirmButton = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(
                    onClick = {
                        onEvent(AccountEvent.SaveAccount)
                    }
                ) {
                    Text(text = "Save Account")
                }
            }
        }

    )
}