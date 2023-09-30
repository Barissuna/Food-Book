package com.barissuna.foodbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.barissuna.foodbook.adapter.FoodRecyclerAdapter
import com.barissuna.foodbook.databinding.FragmentFoodListBinding
import com.barissuna.foodbook.viewmodel.FoodListViewModel


class FoodListFragment : Fragment() {

    private lateinit var binding : FragmentFoodListBinding
    private lateinit var viewModel : FoodListViewModel
    private val recyclerFoodAdapter = FoodRecyclerAdapter(arrayListOf())

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
        viewModel = ViewModelProviders.of(this).get(FoodListViewModel::class.java)
        viewModel.refreshData()

        binding.foodListRecycler.layoutManager = LinearLayoutManager(context)
        binding.foodListRecycler.adapter = recyclerFoodAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.foodLoading.visibility = View.VISIBLE
            binding.foodErrorMessage.visibility = View.GONE
            binding.foodListRecycler.visibility = View.GONE
            viewModel.refreshFromInternet()
            binding.swipeRefreshLayout.isRefreshing = false

        }


        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.foods.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.foodListRecycler.visibility = View.VISIBLE
                recyclerFoodAdapter.updateFoodList(it)
            }
        })

        viewModel.foodErrorMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it){
                    binding.foodErrorMessage.visibility = View.VISIBLE
                }else{
                    binding.foodErrorMessage.visibility = View.GONE
                }
            }
        })

        viewModel.foodLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(it){
                    binding.foodListRecycler.visibility = View.GONE
                    binding.foodErrorMessage.visibility = View.GONE
                    binding.foodLoading.visibility = View.VISIBLE
                }else{
                    binding.foodLoading.visibility = View.GONE
                }
            }
        })

    }

}