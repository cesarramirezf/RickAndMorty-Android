package com.ramces.ricknmortyapp.view.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ramces.ricknmortyapp.databinding.FragmentDetailBinding
import com.ramces.ricknmortyapp.viewmodel.DetailViewModel
import com.ramces.ricknmortyapp.viewmodel.DetailViewModelFactory

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var application: Application

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        @Suppress("UNUSED_VARIABLE")
        application = requireNotNull(activity).application

        binding = FragmentDetailBinding.inflate(inflater)
        initUI()
        return binding.root
    }

    private fun initUI() {
        binding.lifecycleOwner = this

        val rickAndMortyItem = DetailFragmentArgs.fromBundle(requireArguments()).selectedItem

        (activity as AppCompatActivity).supportActionBar?.title = rickAndMortyItem.name

        val viewModelFactory = DetailViewModelFactory(rickAndMortyItem, application)

        binding.viewModel =
            ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
    }
}