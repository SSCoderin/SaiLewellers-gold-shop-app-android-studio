package com.Users

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.sj.saijewellers.R

class OwnDesign: AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_own_design)

        // Find the WebView by its id
        webView = findViewById(R.id.webView)

        // Enable JavaScript (optional)
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        // Set WebViewClient to handle links within the WebView
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url.toString())
                return true
            }
        }

        // Set WebChromeClient to display progress (optional)
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                // You can handle progress updates here
            }
        }

        // Load a URL in the WebView
        val url = ""
        webView.loadUrl(url)
    }

    // Handle back button press to navigate back in WebView history
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
