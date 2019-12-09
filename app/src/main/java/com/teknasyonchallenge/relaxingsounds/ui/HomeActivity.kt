package com.teknasyonchallenge.relaxingsounds.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.teknasyonchallenge.relaxingsounds.R
import com.teknasyonchallenge.relaxingsounds.ui.favorites.FavoritesFragment
import com.teknasyonchallenge.relaxingsounds.ui.library.LibraryFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        nav_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                openFragment(FavoritesFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
               openFragment(LibraryFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment:Fragment){
        try {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment,fragment.tag)
            transaction.commitAllowingStateLoss()
        }catch (e:IllegalStateException){
            Log.e("fragment state :", e.message)
        }
    }
}
