package com.example.rickandmorty.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameOverViewModel(scoreParam: Int, totalQuestionsParam: Int) : ViewModel() {
    // The final score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    // The final score
    private val _totalQuestions = MutableLiveData<Int>()
    val totalQuestions: LiveData<Int>
        get() = _totalQuestions


    init {
        _score.value = scoreParam
        _totalQuestions.value = totalQuestionsParam
    }

}