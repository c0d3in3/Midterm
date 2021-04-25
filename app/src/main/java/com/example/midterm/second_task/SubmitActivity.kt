package com.example.midterm.second_task

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.midterm.R
import kotlin.math.exp

class SubmitActivity : AppCompatActivity() {
    private lateinit var amountTv: TextView
    private lateinit var fullNameTv: TextView
    private lateinit var cvvTv: TextView
    private lateinit var expiryTv: TextView
    private lateinit var cardData: CardData
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        amountTv = findViewById(R.id.amountTextView)
        fullNameTv = findViewById(R.id.fullNameTextView)
        cvvTv = findViewById(R.id.cvvTextView)
        expiryTv = findViewById(R.id.expTextView)
        submitButton = findViewById(R.id.submitButton)

        submitButton.setOnClickListener {
            Toast.makeText(baseContext, "გადახდა წარმატებით შესრულდა", Toast.LENGTH_SHORT).show()
            finish()
        }

        cardData = intent.getParcelableExtra("data")!!
        setData(cardData)
    }

    @SuppressLint("SetTextI18n")
    private fun setData(cardData: CardData) {
        amountTv.text = "${cardData.amount}$"
        fullNameTv.text = cardData.fullName
        cvvTv.text = cardData.cvv
        expiryTv.text = cardData.expiry
    }
}