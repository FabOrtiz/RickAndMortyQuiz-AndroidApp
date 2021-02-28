package com.example.rickandmorty.screens.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.Person.fromBundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {

    private lateinit var binding: FragmentGameOverBinding
    private lateinit var viewModel: GameOverViewModel
    private lateinit var viewModelFactory: GameOverViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_over, container, false)
        val a = GameOverFragmentArgs.fromBundle(arguments!!)
        viewModelFactory = GameOverViewModelFactory(a.score ,a.totalQuestions)
//        viewModelFactory = GameOverViewModelFactory(GameOverViewModelFactory(GameOverFragmentArgs.fromBundle(arguments!!).score)

        viewModel = ViewModelProvider(this, viewModelFactory).get(GameOverViewModel::class.java)


        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}