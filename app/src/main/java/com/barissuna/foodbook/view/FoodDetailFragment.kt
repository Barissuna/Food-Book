package com.barissuna.foodbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.barissuna.foodbook.databinding.FragmentFoodDetailBinding
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

        viewModel = ViewModelProviders.of(this).get(FoodDetailViewModel::class.java)
        viewModel.getRoomData()

        arguments?.let{
            foodId = FoodDetailFragmentArgs.fromBundle(it).foodId
            println(foodId)
        }
        observeLiveData()
    }
    fun observeLiveData(){
        viewModel.foodLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.foodName.text = it.name
                binding.foodCalory.text = it.calory
                binding.foodCarbo.text = it.carbonhydrate
                binding.foodProtein.text = it.protein
                binding.foodOil.text = it.oil


            }
        })
    }



}