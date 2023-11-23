package com.example.mad_3_lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.Random
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat

class NumbDrawingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numb_drawing)


        val numbersGeneratedButton = findViewById<TextView>(R.id.generateNumbersButton)
        val number_good = ContextCompat.getColor(this, R.color.number_good)
        val number_bad = ContextCompat.getColor(this, R.color.number_bad)

        val winmessage = findViewById<TextView>(R.id.winmessage)


        //here I recall selected numbers from the previous activity
        val receivedNumbers = intent.getIntArrayExtra("SELECTEDNUMBERS")
        val numbersRecieved = receivedNumbers?.toMutableSet()
        //if recieved numbers are not null they will be displayed in textView
        if (receivedNumbers != null) {
            val numbersSet = receivedNumbers.toMutableSet()
            val numbersTextView = findViewById<TextView>(R.id.numbersUser)
            numbersTextView.text = "${numbersSet.joinToString(", ")}"
        }

        //it's a function that generated a set of 6 random numbers with no repetitions
        fun generateRandomNumbers(): MutableSet<Int> {
            val random = Random()
            val numbers = mutableSetOf<Int>()

            while (numbers.size < 6) {
                val randomNumber = random.nextInt(49) + 1
                numbers.add(randomNumber)}
            return numbers
        }
        fun getButtonID(index:Int):Int{
            val buttonIDs = arrayOf(
                R.id.randomnumber1, R.id.randomnumber2,
                R.id.randomnumber3, R.id.randomnumber4,
                R.id.randomnumber5, R.id.randomnumber6
            )
            return buttonIDs[index]
        }
        fun updateButtonLabels(randomNumbers: Set<Int>) {
            // Update each button with a random number
            val buttonIds = arrayOf(
                R.id.randomnumber1, R.id.randomnumber2,
                R.id.randomnumber3, R.id.randomnumber4,
                R.id.randomnumber5, R.id.randomnumber6
            )
            for ((index, buttonId) in buttonIds.withIndex()) {
                val button = findViewById<Button>(buttonId)
                button.text = "${randomNumbers.elementAt(index)}"
            }
        }
        fun checkNumbersChangeButtons(RandomNumbers:MutableSet<Int>, recievedNumbers:MutableSet<Int>){
            val buttonIds = arrayOf(
                R.id.randomnumber1, R.id.randomnumber2,
                R.id.randomnumber3, R.id.randomnumber4,
                R.id.randomnumber5, R.id.randomnumber6
            )
            for ((index, RandomNumber) in RandomNumbers.withIndex()){
                if (recievedNumbers.contains(RandomNumber)){

                    val button = findViewById<Button>(getButtonID(index))
                    button.setBackgroundColor(number_good)
                }else{
                    val button = findViewById<Button>(getButtonID(index))
                    button.setBackgroundColor(number_bad)
                }
            }
        }
        fun showButtons() {
            for (number in 0 until 6) {
                val button = findViewById<Button>(getButtonID(number))
                Handler(Looper.getMainLooper()).postDelayed({
                    button.visibility = View.VISIBLE
                }, (number + 1) * 1000L)
            }
            Handler(Looper.getMainLooper()).postDelayed({
                numbersGeneratedButton.isEnabled = true
            }, (7) * 1000L)
        }
        fun hideButtons() {
            for (number in 0 until 6) {
                val button = findViewById<Button>(getButtonID(number))
                button.visibility = View.INVISIBLE
            }
        }
        fun checkWin(RandomNumbers:MutableSet<Int>, recievedNumbers:MutableSet<Int>){
            val commonNumbers = RandomNumbers.intersect(recievedNumbers)
            winmessage.visibility = View.VISIBLE
            if (commonNumbers.size < 3){
                winmessage.text = "You had less then 3 common numbers! Maybe next time!"
            }else if (commonNumbers.size == 3){
                winmessage.text = "You had 3 common numbers! You fig with poppy seeds!"
            } else if (commonNumbers.size == 4){
                winmessage.text = "You had 4 common numbers! You win a broken penny!"
            }else if (commonNumbers.size == 5){
                winmessage.text = "You had 5 common numbers! Cha-ching!"
            }else if (commonNumbers.size == 6){
                winmessage.text = "You had 6 common numbers! One way ticket to rich-ville!"

            }}


        numbersGeneratedButton.setOnClickListener {

            val RandomNumbers = generateRandomNumbers()
            numbersGeneratedButton.isEnabled = false
            hideButtons()
            updateButtonLabels(RandomNumbers)
            if (numbersRecieved != null) {
                checkNumbersChangeButtons(RandomNumbers, numbersRecieved)

            }
            showButtons()
            if (numbersRecieved != null) {
                checkWin(RandomNumbers, numbersRecieved)
            }
        }

    }
}