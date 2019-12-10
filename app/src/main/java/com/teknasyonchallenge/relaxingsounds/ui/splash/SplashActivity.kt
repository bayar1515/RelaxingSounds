package com.teknasyonchallenge.relaxingsounds.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.teknasyonchallenge.relaxingsounds.R
import com.teknasyonchallenge.relaxingsounds.ui.HomeActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var handler: Handler
    private val SPLASH_DELAY: Long = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()
        handler = Handler()
        handler.postDelayed(mRunnable, SPLASH_DELAY)
    }

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    public override fun onDestroy() {
        handler.removeCallbacks(mRunnable)
        super.onDestroy()
    }
}
