package com.g1quiz.app.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.g1quiz.app.models.Question

object QuestionRepository {
    fun loadQuestions(context: Context): List<Question> {
        val json = context.assets.open("questions.json").bufferedReader().use { it.readText() }
        val listType = object : TypeToken<List<Question>>() {}.type
        return Gson().fromJson(json, listType)
    }
}
