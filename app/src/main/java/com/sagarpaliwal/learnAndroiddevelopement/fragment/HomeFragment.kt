package com.sagarpaliwal.learnAndroiddevelopement.fragment

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sagarpaliwal.learnAndroiddevelopement.R
import com.sagarpaliwal.learnAndroiddevelopement.databinding.HomeFragmentBinding


class HomeFragment : Fragment() {

    private val timeDelay = 2000
    private var backPressed: Long = 0
    lateinit var binding: HomeFragmentBinding

    @SuppressLint("SourceLockedOrientationActivity", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /***************************  Night Mode Off- Orientation fix: Portrait -- Action bar  Text Change -- Binding *****************************************/

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        (activity as AppCompatActivity).supportActionBar?.title = "Android Development"
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = HomeFragmentBinding.inflate(inflater, container, false)




        /***************************  Click Listener: For CardView In HomeFragment : With Navigation *****************************************/

        binding.cardviewTutorial.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToTutorialListFragment(1f)
            findNavController().navigate(action)
        }

        binding.cardviewProgram.setOnClickListener {
            findNavController().navigate(R.id.mainProgramFragment)

        }
        binding.cardViewQuestionAnswer.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_expandableListViews)

        }
        binding.cardviewTrypro.setOnClickListener {
            findNavController().navigate(R.id.upgradeProFragment)
        }
        binding.cardviewQuiz.setOnClickListener {
            findNavController().navigate(R.id.javaMainQuiz)
        }

        /***************************  On Back Move: Show Snackbar *****************************************/

        requireActivity().onBackPressedDispatcher.addCallback(this) {

            if (backPressed + timeDelay > System.currentTimeMillis()) {
                activity?.moveTaskToBack(true)
                activity?.finish()

            } else {
                val snackBar = view?.let {
                    Snackbar.make(
                        it, "Press Once again to close application",
                        Snackbar.LENGTH_LONG
                    ).setAction("Action", null)
                }
                snackBar?.setActionTextColor(Color.parseColor("#FFFFFF"))
                val snackBarView = snackBar?.view
                val textViewSn =
                    snackBarView?.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                textViewSn.setTextColor(Color.WHITE)
                textViewSn.textSize = 15f
                textViewSn.textAlignment = View.TEXT_ALIGNMENT_CENTER

                snackBarView.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.snackbar_background)
                snackBar.show()
            }
            backPressed = System.currentTimeMillis()
        }

        return binding.root
    }


}