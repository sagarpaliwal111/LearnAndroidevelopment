package com.sagarpaliwal.learnAndroiddevelopement.fragment

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.sagarpaliwal.learnAndroiddevelopement.R
import com.sagarpaliwal.learnAndroiddevelopement.databinding.FragmentPrivacyPolicyBinding

class PrivacyPolicy : Fragment() {
    private lateinit var binding : FragmentPrivacyPolicyBinding

    @SuppressLint("SetJavaScriptEnabled", "SourceLockedOrientationActivity")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /***************************  Night Mode Off- Orientation fix: Portrait -- Binding *****************************************/

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        // Inflate the layout for this fragment
        binding = FragmentPrivacyPolicyBinding.inflate(inflater, container, false)

        /********************************* WebView Setting *************************************/

        val progressBar = binding.progressbar2
        val root: View = binding.root
        val webView = root.findViewById<WebView>(R.id.webViewPrivacyPolicy)
        webView.settings.javaScriptEnabled = true
        webView.settings.useWideViewPort = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.domStorageEnabled = true
        webView.loadUrl("https://relaxed-almeida-6c683b.netlify.app/")
        webView.webViewClient = WebViewController()
        webView.clearCache(true)
        webView.clearHistory()

        /********************************* After Page Loading Progress bar Visibility Gone *************************************/

        webView.webViewClient = object : WebViewClient() {
            override fun onPageCommitVisible(view: WebView?, url: String?) {
                super.onPageCommitVisible(view, url)
                progressBar.visibility = View.GONE

            }
        }
        return binding.root
    }


}