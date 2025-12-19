package com.g1quiz.app

import android.os.Bundle
import android.widget.Button

import android.content.Intent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    private lateinit var restartBtn: Button
    private lateinit var closeBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)
        val resultText = findViewById<TextView>(R.id.resultText)
        resultText.text = "Your Score: ${score} out of ${total}"

        restartBtn = findViewById(R.id.restartBtn)
        closeBtn = findViewById(R.id.closeBtn)

        restartBtn.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
            finish()
        }

        closeBtn.setOnClickListener {
            finishAffinity()
        }
    }
}
