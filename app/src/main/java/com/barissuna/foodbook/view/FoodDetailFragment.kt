package com.barissuna.foodbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.barissuna.foodbook.databinding.FragmentFoodDetailBinding
import com.barissuna.foodbook.util.downloadImage
import com.barissuna.foodbook.util.makePlaceHolder
import com.barissuna.foodbook.viewmodel.FoodDetailViewModel


class FoodDetailFragment : Fragment() {

    private lateinit var binding : FragmentFoodDetailBinding
    private lateinit var viewModel: FoodDetailViewModel
    private var foodId =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFoodDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let{
            foodId = FoodDetailFragmentArgs.fromBundle(it).foodId
        }

        viewModel = ViewModelProviders.of(this).get(FoodDetailViewModel::class.java)
        viewModel.getRoomData(foodId)


        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.foodLiveData.observe(viewLifecycleOwner, Observer {food ->
            food?.let {
                binding.selectedFood=it
            }
        })
    }



}