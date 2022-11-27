package com.sagarpaliwal.learnAndroiddevelopement.fragment

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.sagarpaliwal.learnAndroiddevelopement.databinding.FragmentAboutUsBinding

class AboutUs : Fragment() {
    private lateinit var binding: FragmentAboutUsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
    /********************************* About Us Code *************************************/
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        (activity as AppCompatActivity).supportActionBar?.title = "About Us"
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Inflate the layout for this fragment
        binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        binding.contact.setOnClickListener {
            val emailIntent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "appstraa@gmail.com", null
                )
            )
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")
            startActivity(emailIntent)
        }


        return binding.root
    }

}
