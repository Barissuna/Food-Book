package com.barissuna.foodbook.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barissuna.foodbook.model.Food
import com.barissuna.foodbook.servis.FoodDatabase
import kotlinx.coroutines.launch

class FoodDetailViewModel(app:Application) : BaseViewModel(app) {

    val foodLiveData = MutableLiveData<Food>()

    fun getRoomData(uuid:Int){
        launch {
            val dao = FoodDatabase(getApplication()).foodDao()
            val food = dao.getFood(uuid)
            foodLiveData.value=food
        }
    }
}