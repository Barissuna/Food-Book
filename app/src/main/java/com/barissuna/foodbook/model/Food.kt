package com.barissuna.foodbook.model

import com.google.gson.annotations.SerializedName

data class Food(
    //Have to specify which variable need to assign the data coming from servers to.
                @SerializedName("isim")
                val name:String?,
                @SerializedName("kalori")
                val calory:String?,
                @SerializedName("karbonhidrat")
                val carbonhydrate:String?,
                @SerializedName("protein")
                val protein:String?,
                @SerializedName("yag")
                val oil:String?,
                @SerializedName("gorsel")
                val image:String?) {


}