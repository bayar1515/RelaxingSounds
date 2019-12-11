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
    private lateinit var favoritesFragment:FavoritesFragment
    private var isFromNavigation = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        favoritesFragment = FavoritesFragment()
        nav_view.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        openFragment(favoritesFragment)

        toolbar_top.setNavigationIcon(R.drawable.ic_navigation_back)
        toolbar_top.setNavigationOnClickListener { onBackPressed() }
    }

    private val mOnNavigationItemSelectedListener = com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                openFragment(favoritesFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
               openFragment(LibraryFragment())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    fun openFragment(fragment:Fragment){
        try {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment,fragment.tag)
            transaction.addToBackStack(null)
            transaction.commitAllowingStateLoss()
        }catch (e:IllegalStateException){
            Log.e("fragment state :", e.message)
        }
    }

    fun setAppBarTitle(title:String) {
        toolbar_title.text = title
    }

    override fun onBackPressed() {
        Log.e("asdasd",(supportFragmentManager.backStackEntryCount).toString())
        if (supportFragmentManager.backStackEntryCount < 2)
            finish()
        else
            supportFragmentManager.popBackStack()
    }
}
