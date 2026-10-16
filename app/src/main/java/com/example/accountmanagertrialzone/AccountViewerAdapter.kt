package com.example.accountmanagertrialzone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.accountmanagertrialzone.room.entity.AccountEntity

/**
 * The adapter no longer reaches for the database. Deleting raises a callback and
 * the Activity hands it to the ViewModel, so there is one path to the data.
 */
class AccountViewerAdapter(
    private val context: Context,
    private val onDelete: (AccountEntity) -> Unit
) : ArrayAdapter<AccountEntity>(context, 0) {

    fun submit(accounts: List<AccountEntity>) {
        clear()
        addAll(accounts)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView
            ?: LayoutInflater.from(context).inflate(R.layout.account_viewer_cell, parent, false)

        val item = getItem(position) ?: return view

        view.findViewById<TextView>(R.id.ViewAccountName).text = item.name
        view.findViewById<TextView>(R.id.ViewTotalAmountValue).text =
            String.format("%d", item.amount)
        view.findViewById<TextView>(R.id.ViewAmountAvailableValue).text =
            String.format("%d", item.availableAmount)
        view.findViewById<TextView>(R.id.ViewInvestedAmountValue).text =
            String.format("%d", item.investedAmount)
        view.findViewById<TextView>(R.id.ViewNotes).text = item.notes

        view.findViewById<TextView>(R.id.Remove_Account).setOnClickListener {
            onDelete(item)
        }

        return view
    }
}
