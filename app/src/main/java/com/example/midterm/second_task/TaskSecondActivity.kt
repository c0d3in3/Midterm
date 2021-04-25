package com.example.midterm.second_task

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.midterm.R
import java.util.*

class TaskSecondActivity : AppCompatActivity() {

    private lateinit var amountEt: EditText
    private lateinit var fullNameEt: EditText
    private lateinit var creditCardEt: EditText
    private lateinit var expMonthEt: EditText
    private lateinit var expYearEt: EditText
    private lateinit var cvvEt: EditText
    private lateinit var buyNowButtonEt: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_second)

        amountEt = findViewById(R.id.amountEditText)
        fullNameEt = findViewById(R.id.fullNameEditText)
        creditCardEt = findViewById(R.id.creditCardEditText)
        expMonthEt = findViewById(R.id.expirationMonthEditText)
        expYearEt = findViewById(R.id.expirationYearEditText)
        cvvEt = findViewById(R.id.cvvEditText)
        buyNowButtonEt = findViewById(R.id.buyNowButton)

        setListeners()
    }

    private fun setListeners() {
        buyNowButtonEt.setOnClickListener {
            makeBuy()
        }
        creditCardEt.addFormatter()
    }

    private fun makeBuy() {
        val fullName = fullNameEt.text.toString()
        if (!isFullNameValid(fullName)) return

        if (expMonthEt.text.isBlank() || expYearEt.text.isBlank() || creditCardEt.text.isBlank() || cvvEt.text.isBlank() || amountEt.text.isBlank()) {
            baseContext.toast(getString(R.string.fill_all_fields))
            return
        }
        val expMonth = expMonthEt.text.toString().toInt()
        if (expMonth > 12 || expMonth <= 0) baseContext.toast(getString(R.string.invalid_month))

        val expYear = expYearEt.text.toString().toInt()
        if (isCardExpired(expMonth, expYear)) {
            baseContext.toast(getString(R.string.card_is_expired))
            return
        }

        val cvv = cvvEt.text.toString()
        if (!isCVVValid(cvv)) return

        val data = CardData(
            amount = amountEt.text.toString().toDouble(),
            fullName = fullName,
            cvv = cvv,
            expiry = "$expMonth/$expYear"
        )
        navigateToSubmit(data)
    }

    private fun navigateToSubmit(data: CardData) {
        val intent = Intent(this, SubmitActivity::class.java)
        intent.putExtra("data", data)
        startActivity(intent)
    }

    private fun isFullNameValid(fullName: String): Boolean {
        if (fullName.isBlank()) {
            Toast.makeText(
                applicationContext,
                getString(R.string.fullname_is_empty),
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    private fun isCVVValid(cvv: String): Boolean {
        if (cvv.length < 3) {
            baseContext.toast(getString(R.string.cvv_must_be_3_digit))
            return false
        }
        return true
    }

    private fun isCardExpired(expMonth: Int, expYear: Int): Boolean {
        val currentDate = Calendar.getInstance()
        val currYear = currentDate.get(Calendar.YEAR).toString().removeRange(0, 2).toInt()
        val currMonth = currentDate.get(Calendar.MONTH) + 1
        return if (currYear > expYear) true
        else expYear == currYear && currMonth >= expMonth
    }

    private fun Context.toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}