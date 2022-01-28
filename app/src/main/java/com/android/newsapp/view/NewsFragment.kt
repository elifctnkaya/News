package com.android.newsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.newsapp.adapter.RecyclerViewAdapter
import com.android.newsapp.databinding.FragmentNewsBinding
import com.android.newsapp.viewmodel.NewsRepository
import com.android.newsapp.viewmodel.NewsViewModel
import com.android.newsapp.viewmodel.RetrofitService
import com.android.newsapp.viewmodel.ViewModelFactory

class NewsFragment : Fragment() {

    private var binding: FragmentNewsBinding? = null
    private val bindingNews get() = binding!!

    private lateinit var viewModel: NewsViewModel
    private val retrofitService = RetrofitService.getInstance()

    val adapter = RecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewsBinding.inflate(inflater,container,false)
        return bindingNews.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelFactory(NewsRepository(retrofitService))).get(
            NewsViewModel::class.java)
        bindingNews.recyclerView.adapter = adapter

        observeLiveData()

    }

    fun observeLiveData()
    {
        viewModel.newsList.observe(viewLifecycleOwner, Observer {
            //bindingNews.news = viewModel
            adapter.setNewsList(it.articles)
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {

        })
        viewModel.getAllNews()
    }
}