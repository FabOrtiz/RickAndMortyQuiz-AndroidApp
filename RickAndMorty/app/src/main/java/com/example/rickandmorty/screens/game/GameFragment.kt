package com.example.rickandmorty.screens.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var gameViewModel: GameViewModel
    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false)

        binding.game = gameViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        gameViewModel.gameEnded.observe(viewLifecycleOwner, Observer<Boolean> { hasFinished ->
            if (hasFinished) gameFinished()
        })
        binding.falseBtn.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
        }

        return binding.root

    }

    private fun gameFinished() {

        val action = GameFragmentDirections.actionGameFragmentToGameOverFragment(gameViewModel.score.value?:0,gameViewModel.totalQuestions)
        NavHostFragment.findNavController(this).navigate(action)

        gameViewModel.onGameFinishComplete()
    }
}