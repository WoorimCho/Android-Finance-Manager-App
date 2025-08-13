package com.example.accountmanagertrialzone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.accountmanagertrialzone.room.AppDataBase
import com.example.accountmanagertrialzone.room.entity.AccountEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//data class AccountItem(val account: String, val totalInvested: Float, val cashBalance: Float, val investment: Float, val notes: String)

class AccountViewerAdapter(private val context: Context, private val items: List<AccountEntity>):
    ArrayAdapter<AccountEntity>(context, 0, items){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.account_viewer_cell, parent, false)
        }

        val item = items[position]

        view!!.findViewById<TextView>(R.id.ViewAccountName).apply{
            text = item.name
        }
        view.findViewById<TextView>(R.id.ViewTotalAmountValue).apply{
//            text = SimpleDateFormat("d").format(item.amount)
            text =String.format("%d", item.amount)
        }
        view.findViewById<TextView>(R.id.ViewAmountAvailableValue).apply{
//            text = SimpleDateFormat("d").format(item.availableAmount)
            text =String.format("%d", item.availableAmount)
        }
        view.findViewById<TextView>(R.id.ViewInvestedAmountValue).apply {
//            text = SimpleDateFormat("d").format(item.investedAmount)
            text =String.format("%d", item.investedAmount)
        }
        view.findViewById<TextView>(R.id.ViewNotes).apply {
            text = item.notes
        }
        view.findViewById<TextView>(R.id.Remove_Account).apply {
            setOnClickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    AppDataBase.getInstance(context).getAccountDao().delete(item)
                }
                (context as AccountViewerActivity).recreate()

               // CoroutineScope(Dispatchers.Main).launch {
//                    items.toMutableList().removeAt(position)
//                    notifyDataSetChanged()

                }//
                //context;
                //notifyDataSetChanged();
            }
//        }


        return view
    }




}