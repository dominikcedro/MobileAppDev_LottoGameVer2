package com.example.mad_3_lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, NumbSelectionActivity::class.java)
            startActivity(intent)
        }, (3) * 1000L)

    }
}