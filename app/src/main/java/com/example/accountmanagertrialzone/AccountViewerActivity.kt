package com.example.accountmanagertrialzone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.accountmanagertrialzone.databinding.ActivityGeneralListBinding
import com.example.accountmanagertrialzone.room.AppDataBase
import com.example.accountmanagertrialzone.screen.account.AccountEvent
import com.example.accountmanagertrialzone.screen.account.AccountViewModel
import com.example.accountmanagertrialzone.screen.account.AccountViewModelFactory
import kotlinx.coroutines.launch

class AccountViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGeneralListBinding
    private lateinit var adapter: AccountViewerAdapter

    private val viewModel: AccountViewModel by lazy {
        ViewModelProvider(
            this,
            AccountViewModelFactory(AppDataBase.getInstance(this).getAccountDao())
        )[AccountViewModel::class.java]
    }

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

        // Deleting goes through the ViewModel, which writes via the DAO. The query
        // is a Flow, so the list below re-emits on its own. No recreate() needed.
        adapter = AccountViewerAdapter(this) { account ->
            viewModel.onEvent(AccountEvent.DeleteAccount(account))
        }
        binding.basicList.adapter = adapter

        // The state is collected only while the Activity is at least STARTED, and
        // collection stops when it is not. Room does the read off the main thread.
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    Log.d("AccountViewer", "accounts = ${state.accounts.size}")
                    adapter.submit(state.accounts)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.testing_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.toAccountEditor -> startActivity(Intent(this, AccountEditorActivity::class.java))
            R.id.toStockEditor -> startActivity(Intent(this, StockEditorActivity::class.java))
            R.id.toAccountViewer -> startActivity(Intent(this, AccountViewerActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
