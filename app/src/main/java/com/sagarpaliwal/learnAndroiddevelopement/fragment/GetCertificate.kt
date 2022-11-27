package com.sagarpaliwal.learnAndroiddevelopement.fragment

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.sagarpaliwal.learnAndroiddevelopement.databinding.FragmentGetCertificateBinding

class GetCertificate : Fragment() {


    /********************************* Google Form for Certificate -- Embed in App Using WebView:  Code *************************************/

    private lateinit var binding: FragmentGetCertificateBinding
    private var html =
        "<iframe src=\"https://docs.google.com/forms/d/e/1FAIpQLSc1gYSJye-6BmbF4X5BZYkzlD7JEQMk-8H_QnOiegkssH1IHA/viewform?embedded=true\" width=\"1020\" height=\"1420\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\">Loading…</iframe>"

    @SuppressLint("SetJavaScriptEnabled", "SourceLockedOrientationActivity")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        (activity as AppCompatActivity).supportActionBar?.title = "Android Development"
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = FragmentGetCertificateBinding.inflate(inflater, container, false)

        /********************************* WebView Setting *************************************/

        val progressBar = binding.progressbarCertificate
        val webView = binding.webViewCertificate
        webView.settings.javaScriptEnabled = true
        webView.settings.useWideViewPort = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.domStorageEnabled = true
        webView.loadData(html, "text/html", null)
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