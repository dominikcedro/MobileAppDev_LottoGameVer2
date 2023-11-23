package com.example.mad_3_lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        val toRegisterText = findViewById<TextView>(R.id.notUser)
        val fullText = "Not a registered user? Sign up"
        val clickableWord = "Sign up"
        val spannableString = SpannableString(fullText)
        val startIndex = fullText.indexOf(clickableWord)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
        spannableString.setSpan(clickableSpan, startIndex, startIndex + clickableWord.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        toRegisterText.text = spannableString
        toRegisterText.movementMethod = LinkMovementMethod.getInstance()
        val buttonGame = findViewById<Button>(R.id.btn_play)

        buttonGame.setOnClickListener{


            val intent = Intent(this, NumbSelectionActivity::class.java)
            startActivity(intent)
        }

    }
}