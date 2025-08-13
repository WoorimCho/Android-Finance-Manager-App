package com.example.accountmanagertrialzone

//import android.widget.Toolbar
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.accountmanagertrialzone.databinding.ActivityAccountEditorBinding
import com.example.accountmanagertrialzone.room.AppDataBase
import com.example.accountmanagertrialzone.room.entity.AccountEntity
//import com.example.accountmanagertrialzone.room.entity.DataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class AccountEditorActivity : AppCompatActivity() {
    val coroutineScope = CoroutineScope(Dispatchers.Main)

    private lateinit var binding: ActivityAccountEditorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
binding = ActivityAccountEditorBinding.inflate(layoutInflater)
//        enableEdgeToEdge()
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar_account_editor);
        setSupportActionBar(toolbar)



//        DataBase.exec( this)
//        val toolbar: Toolbar = findViewById(R.id.toolbar_account_editor);
//        supportActionBar?.setActionBar(toolbar)

//        supportActionBar?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
//        supportActionBar?.setDisplayShowCustomEnabled(true)
//        supportActionBar?.setCustomView(R.layout.toolbar)
//
//        val home_button = supportActionBar?.getCustomView()?.findViewById<ImageButton>(R.id.home_button)
//
//        home_button?.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//        supportActionBar?.customView.findViewById<>(R.id.home_button)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_home_button)

        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_home_button)




//        val home_button:ImageButton = findViewById(R.id.home_button)
//        val menu_button:ImageButton = findViewById(R.id.menu_button)

//        home_button.setOnClickListener{
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//
//        menu_button.setOnClickListener{
//            val intent = Intent(this, AccountEditorActivity::class.java)
//            startActivity(intent)
//        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.AccountInputTable)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        onClickListener()
        buttonClickEvents()
    }

    private fun buttonClickEvents() {
        binding.SaveButton.setOnClickListener {
            val name = binding.AccountNameInput.text.toString()
            val amount : Int = binding.editTextNumberDecimal.text.toString().toIntOrNull() ?: 0
            val notes = binding.AccountNote.text.toString()

            val accountDB = AppDataBase.getInstance(this).getAccountDao()

            coroutineScope.async(Dispatchers.IO) {
                accountDB.upsert(
                    AccountEntity(
                        name = name,
                        amount = amount,
                        availableAmount = 0,
                        investedAmount = 0,
                        notes = notes
                    )
                )
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        getMenuInflater().inflate(R.menu.testing_menu, menu)
        return true
    }

//    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
//    override fun onBackPressed() {
//        super.onBackPressed()
//                Log.i("TrialActivity", "Home button pressed")
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//    }

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



//    fun onClickListener() {
//        binding.SaveButton.setOnClickListener {
//
//            val account: Account = Account(
//                id = 0,
//                name = binding.AccountNameInput.text.toString(),
//                amount = 0,
//                available_amount = 0,
//                invested_amount = 0,
//                notes = ""
//
//            )
//
//            coroutineScope.launch {
//                val db = DataBase.db.accountDao().insert(account)
//            }
//        }
//    }
//    override fun onContextItemSelected(item: MenuItem): Boolean {
////
//        when (item.itemId) {
//            android.R.id.home -> {
////
//////                finish()
//////                return true
////
////
//////                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
//////                return true
//////
//                Log.i("TrialActivity", "Home button pressed")
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                return true
//            }
//        }
//        return super.onContextItemSelected(item)
//    }

}