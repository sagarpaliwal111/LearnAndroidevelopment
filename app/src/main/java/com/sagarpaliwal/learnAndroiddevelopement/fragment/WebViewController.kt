package com.sagarpaliwal.learnAndroiddevelopement.fragment

import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewController: WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, url:  String): Boolean {
        view?.loadUrl(url)
        return true
    }

}
