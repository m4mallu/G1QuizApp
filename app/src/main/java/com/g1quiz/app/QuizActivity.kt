package com.g1quiz.app

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.g1quiz.app.models.Question
import java.io.InputStreamReader
import androidx.core.graphics.toColorInt

class QuizActivity : AppCompatActivity() {

    private lateinit var questions: List<Question>
    private lateinit var endQuizBtn: Button
    private var currentIndex = 0
    private var score = 0

    private lateinit var questionText: TextView
    private lateinit var questionImage: ImageView
    private lateinit var optionButtons: List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionText = findViewById(R.id.questionText)
        questionImage = findViewById(R.id.questionImage)
        endQuizBtn = findViewById(R.id.endQuizBtn)

        optionButtons = listOf(
            findViewById(R.id.option1),
            findViewById(R.id.option2),
            findViewById(R.id.option3),
            findViewById(R.id.option4)
        )

        questions = loadQuestions()
        loadQuestion()

        optionButtons.forEachIndexed { index, button ->
            button.setOnClickListener { handleAnswer(index) }
        }

        endQuizBtn.setOnClickListener {
            showResults()
        }
    }

    private fun loadQuestions(): List<Question> {
        val input = assets.open("questions.json")
        val reader = InputStreamReader(input, "UTF-8")
        val listType = object : TypeToken<List<Question>>() {}.type
        return Gson().fromJson(reader, listType)
    }

    private fun loadQuestion() {
        if (currentIndex >= questions.size) {
            showResults()
            return
        }
        val q = questions[currentIndex]

        questionText.text = q.questionText ?: ""

        if (!q.image.isNullOrEmpty()) {
            val resId = resources.getIdentifier(q.image, "drawable", packageName)
            if (resId != 0) {
                questionImage.setImageResource(resId)
                questionImage.visibility = ImageView.VISIBLE
            } else {
                questionImage.visibility = ImageView.GONE
            }
        } else {
            questionImage.visibility = ImageView.GONE
        }

        for (i in optionButtons.indices) {
            optionButtons[i].text = q.options[i]
            optionButtons[i].isEnabled = true
            // Reverted to setting the default gray background color
            optionButtons[i].setBackgroundColor("#E0E0E0".toColorInt())
        }
    }

    private fun handleAnswer(selectedIndex: Int) {
        val correctIndex = questions[currentIndex].correctIndex

        optionButtons.forEach { it.isEnabled = false }

        if (selectedIndex == correctIndex) {
            score++
            // Reverted to setting the green background color
            optionButtons[selectedIndex].setBackgroundColor("#4CAF50".toColorInt())
        } else {
            // Reverted to setting the red background color
            optionButtons[selectedIndex].setBackgroundColor("#F44336".toColorInt())
            // Reverted to setting the green background color for the correct option
            optionButtons[correctIndex].setBackgroundColor("#4CAF50".toColorInt())
        }

        Handler(Looper.getMainLooper()).postDelayed({
            goToNextQuestion()
        }, 1200)
    }

    private fun goToNextQuestion() {
        if (currentIndex < questions.size - 1) {
            currentIndex++
            loadQuestion()
        } else {
            // Use showResults() instead of duplicating code
            showResults()
        }
    }

    private fun showResults() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("score", score)
        intent.putExtra("total", questions.size)
        startActivity(intent)
        finish()
    }
}
