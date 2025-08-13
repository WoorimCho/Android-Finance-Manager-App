package com.example.accountmanagertrialzone.screen.stock


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.accountmanagertrialzone.room.dao.StockDao
import com.example.accountmanagertrialzone.room.entity.StockEntity

class StockDialog(
    val dao : StockDao
) {
    var name: String = ""
    var marketPrice: Int = 0
    var stocksOwned: Int = 0
    //var ownedValue: Int = marketPrice * stocksOwned,
    var stockNotes: String = ""

    @Composable
    fun addStock(modifier: Modifier = Modifier) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = {   },
            title = { Text(text = "Add Stock") },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextField(
                        value = "name",
                        onValueChange = {
                            name = it
                        },
                        placeholder = {
                            Text(text = "Stock Name")
                        }
                    )
                    TextField(
                        value = "Market Price",
                        onValueChange = {
                            marketPrice = it.toInt()
                        },
                        placeholder = {
                            Text(text = "Market Price")
                        }
                    )
                    TextField(
                        value = "Stocks Owned",
                        onValueChange = {
                            stocksOwned = it.toInt()
                        },
                        placeholder = {
                            Text(text = "Stocks Owned")
                        }
                    )
                    TextField(
                        value = "Notes",
                        onValueChange = {
                            stockNotes = it
                        },
                        placeholder = {
                            Text(text = "Notes")
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
                            dao.upsert(StockEntity(
                                name = name,
                                marketPrice = marketPrice,
                                stocksOwned = stocksOwned,
                                stockNotes = stockNotes
                            ))
                        }
                    ) {
                        Text(text = "Save Stock")
                    }
                }
            }

        )
    }
}