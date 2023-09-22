package com.barissuna.foodbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barissuna.foodbook.model.Food
import com.barissuna.foodbook.servis.FoodAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FoodListViewModel : ViewModel() {

    val foods = MutableLiveData<List<Food>>()
    val foodErrorMessage = MutableLiveData<Boolean>()
    val foodLoading = MutableLiveData<Boolean>()

    private val foodApiService = FoodAPIService()
    private val disposable = CompositeDisposable()

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
                        foods.value =t
                        foodLoading.value = false
                        foodErrorMessage.value = false
                    }

                    override fun onError(e: Throwable) {
                        foodLoading.value=false
                        foodErrorMessage.value = true
                        e.printStackTrace()
                    }

                })
        )

    }


}