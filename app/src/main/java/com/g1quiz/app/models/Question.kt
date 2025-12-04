package com.g1quiz.app.models

data class Question(
    val id: Int,
    // question can be a string or an array; normalize to questionText
    val question: Any?,
    val image: String?,
    val options: List<String>,
    val answer: String
) {
    // Helper property used in app: questionText and correctIndex computed at runtime
    val questionText: String?
        get() {
            return when (question) {
                is String -> question
                is List<*> -> question.joinToString("\n") { it?.toString() ?: "" }
                else -> question?.toString()
            }
        }

    val correctIndex: Int
        get() = options.indexOf(answer).let { if (it >= 0) it else 0 }
}
