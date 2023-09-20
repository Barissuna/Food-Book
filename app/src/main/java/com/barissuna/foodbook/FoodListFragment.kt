package com.barissuna.foodbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.barissuna.foodbook.databinding.FragmentFoodListBinding


class FoodListFragment : Fragment() {

    private lateinit var binding : FragmentFoodListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFoodListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.foodListButton.setOnClickListener {

            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment(3)
            Navigation.findNavController(it).navigate(action)

        }

    }

}