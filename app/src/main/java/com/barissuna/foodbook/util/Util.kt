package com.barissuna.foodbook.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.barissuna.foodbook.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.downloadImage (url: String?,placeHolder:CircularProgressDrawable){

    val options = RequestOptions().placeholder(placeHolder).error(R.mipmap.ic_launcher_round)

    Glide.with(context).setDefaultRequestOptions(options).load(url).into(this)

}

fun makePlaceHolder(context: Context) : CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadImageXML")
fun downloadImageXML(view:ImageView,url:String?){
    view.downloadImage(url, makePlaceHolder(view.context))
}