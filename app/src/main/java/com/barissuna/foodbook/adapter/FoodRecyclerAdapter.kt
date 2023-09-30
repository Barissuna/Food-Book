package com.barissuna.foodbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.barissuna.foodbook.R
import com.barissuna.foodbook.databinding.FoodRecyclerRowBinding
import com.barissuna.foodbook.model.Food
import com.barissuna.foodbook.util.downloadImage
import com.barissuna.foodbook.util.makePlaceHolder
import com.barissuna.foodbook.view.FoodListFragmentDirections

class FoodRecyclerAdapter(val foodList:ArrayList<Food>) : RecyclerView.Adapter<FoodRecyclerAdapter.FoodViewHolder>(),FoodClickListener {

    class FoodViewHolder(var view: FoodRecyclerRowBinding):RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.food_recycler_row,parent,false)
        val view = DataBindingUtil.inflate<FoodRecyclerRowBinding>(inflater,R.layout.food_recycler_row,parent,false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {

        holder.view.food = foodList[position]
        holder.view.listener=this

        /*
        holder.itemView.findViewById<TextView>(R.id.name).text = foodList.get(position).name
        holder.itemView.findViewById<TextView>(R.id.calory).text = foodList.get(position).calory

        holder.itemView.setOnClickListener{
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment(foodList.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.itemView.findViewById<ImageView>(R.id.imageView).downloadImage(foodList.get(position).image,
            makePlaceHolder(holder.itemView.context)
        )

         */
    }

    fun updateFoodList(newFoodList : List<Food>){
        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()
    }

    override fun foodClicked(holder:FoodViewHolder,view:View) {
        val uuid = holder.view.foodUuid.text.toString().toIntOrNull()
        uuid?.let {
            val action = FoodListFragmentDirections.actionFoodListFragmentToFoodDetailFragment(it)
            Navigation.findNavController(view).navigate(action)
        }
    }

}