package com.example.accountmanagertrialzone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import com.example.accountmanagertrialzone.databinding.ActivityGeneralListBinding
import com.example.accountmanagertrialzone.room.AppDataBase
import com.example.accountmanagertrialzone.room.entity.AccountEntity
import com.example.accountmanagertrialzone.screen.account.AccountEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class AccountViewerActivity : AppCompatActivity() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private lateinit var binding: ActivityGeneralListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("activity", "onCreate")
        super.onCreate(savedInstanceState)
        binding = ActivityGeneralListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar_account_viewer)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_home_button)



//        val db = AppDataBase.getInstance(this)
//        val listItems = listOf(
//            AccountEntity("First",0,0,0,"None" ),
//            AccountEntity("bakdwl",1000,1000,0,"" ),
//            AccountEntity("kbagl",9000,0,9000,"KBLawgdb" ),
//            AccountEntity("Last",2,1,1,"bdawdlf" )
//        )
//        val listItems = db.getAccountDao().getAccountsOrderedByName()
        //
//        val test: List<AccountEntity> =listItems.flattenToList()
       // val adapter = AccountViewerAdapter(this, listItems.toList)
//        val adapter: AccountViewerAdapter
//        val context = this



//        ButtonClickEvents()

    }

    override fun onResume() {
        super.onResume()

        Log.d("activity", "onResume")

        val basicList = binding.basicList
        buildAccountList()?.let {
            val adapter = AccountViewerAdapter(this, it)
            //        runBlocking {
            //            adapter = AccountViewerAdapter(context, listItems.flattenToList())
            //        }
            basicList.adapter = adapter
        }

    }

    private fun buildAccountList(): List<AccountEntity>? {
        val accountDB = AppDataBase.getInstance(this).getAccountDao()
        var accountList: List<AccountEntity>? = null

//        coroutineScope.async(Dispatchers.IO) {
//            accountList = accountDB.getAccountsOrderedByName()
//            Log.d("buildAccountList", "accountList = $accountList")
//            withContext(Dispatchers.Main) {
//                return@withContext accountList
//            }
//        }
        runBlocking {
            coroutineScope.async(Dispatchers.IO) {
                accountList = accountDB.getAccountsOrderedByName()
                Log.d("buildAccountList", "accountList = $accountList")
            }.await()
        }
        Log.d("buildAccountList", "accountList 2 = $accountList")

            return accountList

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.testing_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.toAccountEditor
            -> {
                val intent = Intent(this, AccountEditorActivity::class.java)
                startActivity(intent)
            }
            R.id.toStockEditor
            -> {
                val intent = Intent(this, StockEditorActivity::class.java)
                startActivity(intent)
            }
            R.id.toAccountViewer
            -> {
                val intent = Intent(this, AccountViewerActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

//    @Composable
//    private fun ButtonClickEvents() {
//        binding.Remove_Account.setOnClickListener {
//
//
//        }
////        FloatingActionButton(onClick = {val accountDB = AppDataBase.getInstance(this).getAccountDao() }) {
//
////        }
////        RemoveAccount.setOnClickListener {
//
////        }
//
//    }

//    suspend fun <T> Flow<List<T>>.flattenToList() =
//        flatMapConcat { it.asFlow() }.toList()
}