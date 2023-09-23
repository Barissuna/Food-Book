package com.barissuna.foodbook.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barissuna.foodbook.model.Food
import com.barissuna.foodbook.servis.FoodAPIService
import com.barissuna.foodbook.servis.FoodDatabase
import com.barissuna.foodbook.util.SpecialSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FoodListViewModel(app:Application) : BaseViewModel(app) {

    val foods = MutableLiveData<List<Food>>()
    val foodErrorMessage = MutableLiveData<Boolean>()
    val foodLoading = MutableLiveData<Boolean>()

    private val foodApiService = FoodAPIService()
    private val disposable = CompositeDisposable()
    private val specialSharedPreferences = SpecialSharedPreferences(getApplication())

    fun refreshData(){
        getDataFromInternet()
    }

    private fun getDataFromInternet(){
        foodLoading.value = true

        disposable.add(
            foodApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Food>>(){
                    override fun onSuccess(t: List<Food>) {
                        sqliteSave(t)
                    }

                    override fun onError(e: Throwable) {
                        foodLoading.value=false
                        foodErrorMessage.value = true
                        e.printStackTrace()
                    }

                })
        )

    }

    private fun showFoods(foodsList : List<Food>){
        foods.value =foodsList
        foodLoading.value = false
        foodErrorMessage.value = false
    }

    private fun sqliteSave(foodList : List<Food>){

        launch {

            val dao = FoodDatabase(getApplication()).foodDao()
            dao.deleteAllFood()
            val uuidList = dao.insertAll(*foodList.toTypedArray())
            var i =0
            while(i < foodList.size){
                foodList[i].uuid = uuidList[i].toInt()
                i++
            }
            showFoods(foodList)
        }
        specialSharedPreferences.saveTime(System.nanoTime())

    }


}