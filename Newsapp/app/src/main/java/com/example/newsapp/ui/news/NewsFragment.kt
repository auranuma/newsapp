package com.example.newsapp.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.core.hide
import com.example.newsapp.core.showToast
import com.example.newsapp.databinding.FragmentNewsBinding

class NewsFragment : Fragment(R.layout.fragment_news) {

    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setupNewsObserver()
    }

    private fun setupNewsObserver() {

        viewModel.newsLiveData.observe(viewLifecycleOwner) { news ->
            if (news == null) {
                binding.pbNews.hide()
                requireContext().showToast("Something went wrong!")
            } else {
                newsAdapter = NewsAdapter(news.article)
                binding.rvArticle.adapter = newsAdapter
                binding.pbNews.hide()
            }
        }
    }

    private fun initRecyclerView() {
        binding.rvArticle.setHasFixedSize(true)
        binding.rvArticle.layoutManager = LinearLayoutManager(context)
    }
}