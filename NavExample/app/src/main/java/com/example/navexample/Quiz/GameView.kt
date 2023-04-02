package com.example.navexample.Quiz

import androidx.lifecycle.ViewModel
import com.example.navexample.Quiz.data.Question
import com.example.navexample.Quiz.data.questions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private var questionIndex = 0
    private val usedQuestions = mutableListOf<Int>()

    private val _uiState = MutableStateFlow(
        GameUiState(
            currentQuestion = nextQuestion(),
        )
    )
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private fun nextQuestion(): Question {
        if (usedQuestions.size == questions.size) {
            return questions[questionIndex]
        }

        var index = (questions.indices).random()
        while (usedQuestions.contains(index)) {
            index = (questions.indices).random()
        }
        usedQuestions.add(index)
        questionIndex = index
        questions[index].options = questions[index].options.shuffled()
        return questions[index]
    }

    private fun checkAnswer(answer: String): Boolean {
        val correct = answer == _uiState.value.currentQuestion.correctAnswer
        if (usedQuestions.size == 10) {
            _uiState.update { currentState ->
                currentState.copy(
                    isGameOver = true
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    currentQuestionCount = currentState.currentQuestionCount.inc()
                )
            }
        }
        if (correct) {
            _uiState.update { currentState ->
                currentState.copy(
                    score = currentState.score.inc()
                )
            }
        }
        return correct
    }

    fun checksum() {
        if (_uiState.value.selectedAnswer == "") {
            _uiState.update { currentState ->
                currentState.copy(
                    result = "Select One or Quit"
                )
            }
        } else {
            val isCorrect = checkAnswer(_uiState.value.selectedAnswer)
            val result = if (isCorrect) {
                "Nice One GO next"
            } else {
                "No Dude 'DO BETTER!'"
            }
            _uiState.update { currentState ->
                currentState.copy(
                    result = result,
                    selectedAnswer = "",
                    currentQuestion = nextQuestion()
                )
            }
        }
    }

    fun selectedAnswer(option: String) {
        _uiState.update { currentState ->
            currentState.copy(
                selectedAnswer = option
            )
        }
    }

    fun reset() {
        questionIndex = 0
        usedQuestions.clear()
        _uiState.value = GameUiState(
            isGameOver = false,
            currentQuestion = nextQuestion(),
            selectedAnswer = "",
            result = ""
        )
    }
}