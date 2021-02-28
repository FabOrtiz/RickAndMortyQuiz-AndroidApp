package com.example.rickandmorty.screens.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GameOverViewModelFactory(private val score: Int, private val totalQuestions: Int) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameOverViewModel::class.java)) {
            return GameOverViewModel(score, totalQuestions) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}