package com.example.newsapp.ui.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.newsapp.core.Utils
import com.example.newsapp.data.remote.model.Article
import com.example.newsapp.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var article: Article

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        article = DetailsFragmentArgs.fromBundle(requireArguments()).article
        initToolbar()
        setNewDetails()
    }

    private fun setNewDetails() {
        binding.detailTitle.text = article.title
        binding.detailToolbarTitle.text = article.title
        binding.detailAuthor.text = article.author
        binding.detailTime.text = Utils.dateToTimeFormat(article.publishedAt)
        binding.detailPublishedAt.text = Utils.dateFormat(article.publishedAt)

        Glide.with(requireContext()).load(article.urlToImage)
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.detailProgressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.detailProgressBar.visibility = View.GONE
                    return false
                }

            }).into(binding.detailImage)
        initWebView(article.url)
    }

    private fun initWebView(url: String) {
        binding.webView.settings.loadsImagesAutomatically = true
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.domStorageEnabled = true
        binding.webView.settings.setSupportZoom(true)
        binding.webView.settings.builtInZoomControls = true
        binding.webView.settings.displayZoomControls = false
        binding.webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(url)
        binding.detailProgressBar.visibility = View.INVISIBLE
    }

    private fun initToolbar() {
        (activity as AppCompatActivity?)?.setSupportActionBar(binding.detailToolbar)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}