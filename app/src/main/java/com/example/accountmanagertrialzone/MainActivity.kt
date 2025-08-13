package com.example.accountmanagertrialzone

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
//import com.example.accountmanagertrialzone.room.entity.DataBase
import com.example.accountmanagertrialzone.ui.theme.AccountManagerTrialZoneTheme
import com.example.accountmanagertrialzone.room.AppDataBase
//import com.example.accountmanagertrialzone.screen.account.AccountViewModel
import com.example.accountmanagertrialzone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBind : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBind.root)

        AppDataBase.getInstance(this)


        val toolbar: Toolbar = findViewById(R.id.toolbar_main_menu);
        setSupportActionBar(toolbar)
        buttonClickEvents()

    }

    private fun buttonClickEvents() {
        mainBind.toAccountEditor.setOnClickListener {
            val intent = Intent(this@MainActivity, AccountEditorActivity::class.java)
            startActivity(intent)
        }
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

}




//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    AccountManagerTrialZoneTheme {
//        Greeting("Android")
//    }
//}