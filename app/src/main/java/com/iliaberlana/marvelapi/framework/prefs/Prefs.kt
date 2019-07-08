package com.iliaberlana.marvelapi.framework.prefs

import android.content.Context
import android.content.SharedPreferences

class Prefs (context: Context) {
    private val PREFS_ORDER = "com.iliaberlana.sharedpreferences"
    private val SHARED_ORDER = "marvel_ordenation"
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_ORDER, 0)

    var order: String
        get() = prefs.getString(SHARED_ORDER, "")?: ""
        set(value) = prefs.edit().putString(SHARED_ORDER, value).apply()
}