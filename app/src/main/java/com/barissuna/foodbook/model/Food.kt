package com.barissuna.foodbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Food(
    //Have to specify which variable need to assign the data coming from servers to.
    @ColumnInfo(name = "isim")
    @SerializedName("isim")
    val name: String?,
    @ColumnInfo(name = "kalori")
    @SerializedName("kalori")
    val calory: String?,
    @ColumnInfo(name = "karbonhidrat")
    @SerializedName("karbonhidrat")
    val carbonhydrate: String?,
    @ColumnInfo(name = "protein")
    @SerializedName("protein")
    val protein: String?,
    @ColumnInfo(name = "yag")
    @SerializedName("yag")
    val oil: String?,
    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    val image: String?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid : Int = 0


}