package com.example.rickandmorty.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmorty.R

data class Question (
    val questionID: Int,
    val answer: Boolean,
    var attempted: Boolean = false,
    var answered: Boolean = false
)

class GameViewModel : ViewModel() {

    private var questionIndex = 0;
    private lateinit var questionBank: MutableList<Question>

    val totalQuestions : Int
        get() = questionBank.size

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _question = MutableLiveData<Int>()
    val question: LiveData<Int>
        get() = _question

    private val _attempted = MutableLiveData<Boolean>()
    val attempted: LiveData<Boolean>
        get() = _attempted

    private val _checkTrue = MutableLiveData<Boolean>()
    val checkTrue: LiveData<Boolean>
        get() = _checkTrue

    private val _checkFalse = MutableLiveData<Boolean>()
    val checkFalse: LiveData<Boolean>
        get() = _checkFalse

    private val _isCorrect = MutableLiveData<Boolean>()
    val questionIsCorrect: LiveData<Boolean>
        get() = _isCorrect

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val gameEnded: LiveData<Boolean>
        get() = _eventGameFinish

    init {
        newGame();
    }

    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    fun questionsAttempted() = questionBank.count{it.attempted}

    fun newGame() {
        resetQuestionBank()
        questionIndex = 0
        _eventGameFinish.value = false
        updateQuestion()
    }

    fun onPrevious(){
        if (questionIndex == 0) {
            questionIndex = questionBank.size - 1
        } else {
            questionIndex -= 1
        }

        updateQuestion()
    }

    fun onNext(){
        if (questionIndex == (questionBank.size - 1)) {
            questionIndex = 0
        } else {
            questionIndex += 1
        }

        updateQuestion()

    }

    fun answerQuestion(answer: Boolean) {
        questionBank[questionIndex].attempted = true
        questionBank[questionIndex].answered = answer

        updateQuestion()
    }

    private fun updateQuestion() {
        _question.value = questionBank[questionIndex].questionID
        _attempted.value =  questionBank[questionIndex].attempted
        _isCorrect.value =  questionBank[questionIndex].answer == questionBank[questionIndex].answered

        _checkFalse.value = questionBank[questionIndex].attempted
                && !questionBank[questionIndex].answer
                && questionBank[questionIndex].answer == questionBank[questionIndex].answered
        _checkTrue.value = questionBank[questionIndex].attempted
                && questionBank[questionIndex].answer
                && questionBank[questionIndex].answer == questionBank[questionIndex].answered

        _score.value = questionBank.count{it.attempted && it.answer == it.answered}

        if(questionsAttempted() == questionBank.size)
            onGameFinish()
    }

    private fun resetQuestionBank() {
        questionBank = mutableListOf(
            Question(R.string.question_1, false),
            Question(R.string.question_2, true),
            Question(R.string.question_3, true),
            Question(R.string.question_4, false),
            Question(R.string.question_5, false)//,
//            Question(R.string.question_6, true),
//            Question(R.string.question_7, false),
//            Question(R.string.question_8, true),
//            Question(R.string.question_9, false),
//            Question(R.string.question_10, false),
//            Question(R.string.question_11, false),
//            Question(R.string.question_12, true),
//            Question(R.string.question_13, false),
//            Question(R.string.question_14, true),
//            Question(R.string.question_15, false),
//            Question(R.string.question_16, false),
//            Question(R.string.question_17, true),
//            Question(R.string.question_18, false),
//            Question(R.string.question_19, false),
//            Question(R.string.question_20, true)
        )

        questionBank.shuffled()
    }
}

