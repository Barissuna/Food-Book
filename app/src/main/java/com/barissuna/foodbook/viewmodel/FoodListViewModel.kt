package com.barissuna.foodbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barissuna.foodbook.model.Food

class FoodListViewModel : ViewModel() {

    val foods = MutableLiveData<List<Food>>()
    val foodErrorMessage = MutableLiveData<Boolean>()
    val foodLoading = MutableLiveData<Boolean>()

    fun refreshData(){
        val muz = Food("Muz","100","10","5","1","www.test.com")
        val cilek = Food("Cilek","200","20","10","2","www.test2.com")
        val elma = Food("Elma","300","30","15","3","www.test3.com")

        val foodList = arrayListOf<Food>(muz,elma,cilek)

        foods.value=foodList
        foodErrorMessage.value=true
        foodLoading.value=false
    }


}