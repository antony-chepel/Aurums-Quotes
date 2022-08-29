package com.aurumquotes.app.screens

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aurumquotes.app.MainActivity
import com.aurumquotes.app.R

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Thread {
            Thread.sleep(2000)
            runOnUiThread {
                startActivity(Intent(this,MainActivity::class.java))
            }
        }.start()

    }
}