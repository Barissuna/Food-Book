package com.barissuna.foodbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barissuna.foodbook.model.Food

class FoodDetailViewModel : ViewModel() {

    val foodLiveData = MutableLiveData<Food>()

    fun getRoomData(){
        val muz = Food("Muz","100","10","5","1","www.test.com")
        foodLiveData.value=muz
    }
}