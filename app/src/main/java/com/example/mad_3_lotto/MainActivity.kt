package com.example.mad_3_lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toLoginText = findViewById<TextView>(R.id.alreadyuser)
        val fullText = "Already a user? Login"
        val clickableWord = "Login"
        val spannableString = SpannableString(fullText)
        val startIndex = fullText.indexOf(clickableWord)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Handle the click event here
                // For example, navigate to LoginActivity
                val intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        spannableString.setSpan(clickableSpan, startIndex, startIndex + clickableWord.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        toLoginText.text = spannableString
        toLoginText.movementMethod = LinkMovementMethod.getInstance()

        val buttonGame = findViewById<Button>(R.id.btn_play)

        val inputName = findViewById<EditText>(R.id.text_name)
        val inputMail = findViewById<EditText>(R.id.text_email)

        //here below if no name/phone/email were provided they values are defined as "unknown

        buttonGame.setOnClickListener{
            var name = inputName.text.toString()
            if (name.isEmpty()) {
                name = "unknown"
            }
            var email = inputMail.text.toString()
            if (email.isEmpty()) {
                email = "unknown"
            }

            val intent = Intent(this, NumbSelectionActivity::class.java)
            intent.putExtra("NAME",name)
            intent.putExtra("EMAIL", email)
            startActivity(intent)
        }
    }
}