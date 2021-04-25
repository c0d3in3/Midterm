package com.example.midterm.second_task

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.addFormatter() {
    this.addTextChangedListener(object: TextWatcher{
        override fun afterTextChanged(s: Editable?) {
            val initial: String = s.toString()
            var processed = initial.replace("\\D".toRegex(), "")
            processed = processed.replace("(\\d{4})(?=\\d)".toRegex(), "$1 ")
            if (initial != processed)
                s?.replace(0, initial.length, processed)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}