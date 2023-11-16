package com.example.mad_3_lotto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val switchView = findViewById<Switch>(R.id.switch_age)
        val buttonGame = findViewById<Button>(R.id.btn_play)
        buttonGame.isEnabled = false

        // below is my solution for first problem
        //in form of if/else statement my activity changes the switchView.text
        //from "YES" to "NO" depending on the state of switch
        switchView.setOnCheckedChangeListener {_,isChecked ->
            buttonGame.isEnabled = isChecked
            if (isChecked){
                switchView.text = "YES"
            } else {
                switchView.text = "NO"
            }
        }

        val inputName = findViewById<EditText>(R.id.text_name)
        val inputMail = findViewById<EditText>(R.id.text_email)
        val inputPhone = findViewById<EditText>(R.id.text_telephone)

        //here below if no name/phone/email were provided they values are defined as "unknown"
        buttonGame.setOnClickListener{
            var name = inputName.text.toString()
            if (name.isEmpty()) {
                name = "unknown"
            }
            var email = inputMail.text.toString()
            if (email.isEmpty()) {
                email = "unknown"
            }
            var phone = inputPhone.text.toString()
            if (phone.isEmpty()) {
                phone = "unknown"
            }

            //here below those variables: name, email and phone are pushed ahead to
            //NumbSelectionActivity for future use
            val intent = Intent(this, NumbSelectionActivity::class.java)
            intent.putExtra("NAME",name)
            intent.putExtra("EMAIL", email)
            intent.putExtra("PHONE",phone)
            startActivity(intent)
        }
    }
}