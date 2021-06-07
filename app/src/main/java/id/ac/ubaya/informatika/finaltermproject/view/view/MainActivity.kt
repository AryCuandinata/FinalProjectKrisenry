package id.ac.ubaya.informatika.finaltermproject.view.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import id.ac.ubaya.informatika.finaltermproject.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this,R.id.hostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        bottomNav.setupWithNavController(navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }

}