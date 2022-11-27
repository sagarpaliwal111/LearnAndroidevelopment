package com.sagarpaliwal.learnAndroiddevelopement.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sagarpaliwal.learnAndroiddevelopement.R
import com.sagarpaliwal.learnAndroiddevelopement.databinding.FragmentJavaMainQuizBinding

class JavaMainQuiz : Fragment() {
    private lateinit var binding: FragmentJavaMainQuizBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentJavaMainQuizBinding.inflate(inflater, container, false)
        binding.playQuizHomeBtn.setOnClickListener {
            findNavController().navigate(R.id.action_javaMainQuiz_to_java_Quiz_First)
        }
        binding.QuizSet2.setOnClickListener {
            findNavController().navigate(R.id.java_Quiz_Second)
        }
        binding.QuizSet3.setOnClickListener {
            findNavController().navigate(R.id.java_Quiz_Third)
        }
        binding.QuizSet4.setOnClickListener {
            findNavController().navigate(R.id.java_Quiz_Four)
        }
        binding.QuizSet5.setOnClickListener {
            findNavController().navigate(R.id.java_Quiz_Five)
        }
        binding.QuizSet6.setOnClickListener {
            findNavController().navigate(R.id.java_Quiz_Six)
        }
        binding.QuizSet7.setOnClickListener {
            findNavController().navigate(R.id.java_Quiz_Seven)
        }
        binding.QuizSet8.setOnClickListener {
            findNavController().navigate(R.id.java_Quiz_Eight)
        }
        binding.QuizSet9.setOnClickListener {
            findNavController().navigate(R.id.java_Quiz_Nine)
        }
        binding.QuizSet10.setOnClickListener {
            findNavController().navigate(R.id.java_Quiz_Ten)
        }
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.homeFragment)
        }
        return binding.root
    }

}