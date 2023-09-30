package com.barissuna.foodbook.adapter

import android.view.View

interface FoodClickListener {

    fun foodClicked(holder:FoodRecyclerAdapter.FoodViewHolder,view:View)

}