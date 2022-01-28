package com.android.newsapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.android.newsapp.viewmodel.NewsViewModel
import com.android.newsapp.databinding.FragmentNewsDetailsBinding
import com.android.newsapp.viewmodel.*
import com.bumptech.glide.Glide

class NewsDetailsFragment : Fragment() {

    private lateinit var bindingDetails: FragmentNewsDetailsBinding

    private lateinit var viewModel: NewsViewModel
    private val retrofitService = RetrofitService.getInstance()

    private val args by navArgs<NewsDetailsFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindingDetails = FragmentNewsDetailsBinding.inflate(inflater,container,false)
        return bindingDetails.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelFactory(NewsRepository(retrofitService))).get(
            NewsViewModel::class.java)

        bindingDetails.back.setOnClickListener {
            val action = NewsDetailsFragmentDirections.actionNewsDetailsFragmentToNewsFragment()
            Navigation.findNavController(view).navigate(action)
        }

        observeLiveData()
    }


    fun observeLiveData()
    {
        viewModel.newsList.observe(viewLifecycleOwner, Observer {

            bindingDetails.textArticleTitle.text = viewModel.newsList.value?.articles?.get(args.position)?.title
            bindingDetails.textViewDescription.text = viewModel.newsList.value?.articles?.get(args.position)?.description
            view?.let { it1 -> Glide.with(it1.context)
                .load(viewModel.newsList.value?.articles?.get(args.position)?.urlToImage)
                .into(bindingDetails.imageArticleImage) }

        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {

        })
        viewModel.getAllNews()
    }


}