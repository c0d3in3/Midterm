package com.example.midterm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.midterm.first_task.TaskFirstActivity
import com.example.midterm.second_task.TaskSecondActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.task1).setOnClickListener {
            val intent = Intent(this, TaskFirstActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.task2).setOnClickListener {
            val intent = Intent(this, TaskSecondActivity::class.java)
            startActivity(intent)
        }
    }
}