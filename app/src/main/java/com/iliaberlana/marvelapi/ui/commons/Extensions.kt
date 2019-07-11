package com.iliaberlana.marvelapi.ui.commons

import android.content.Context
import android.support.annotation.LayoutRes
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target.*

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun ImageView.loadImage(path: String?) {
    if (!path.isNullOrEmpty()) {
        Glide.with(context)
            .load(path)
            .centerCrop()
            .override(SIZE_ORIGINAL)
            .into(this)
    }
}

fun Context.toast(context: Context = applicationContext, message: String, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(context, message , duration).show()
}

fun String.logDebug(message: String) {
    Log.d(this, message)
}