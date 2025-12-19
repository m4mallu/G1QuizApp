package com.g1quiz.app

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.URLSpan
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startBtn = findViewById<Button>(R.id.startQuizBtn)
        val closeBtn = findViewById<Button>(R.id.closeAppBtn) // Find the new button

        startBtn.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }

        closeBtn.setOnClickListener {
            finishAffinity() // This command closes the entire application
        }

        // --- Hyperlink Code ---
        val creditsText: TextView = findViewById(R.id.creditsText)
        creditsText.movementMethod = LinkMovementMethod.getInstance()

        val fullText = creditsText.text.toString()
        val linkText = "M4mallu@GitHub"
        val url = "https://github.com/m4mallu"

        val spannableString = SpannableString(fullText)
        val start = fullText.indexOf(linkText)
        val end = start + linkText.length

        if (start != -1) {
            spannableString.setSpan(URLSpan(url), start, end, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            creditsText.text = spannableString
        }
    }
}
