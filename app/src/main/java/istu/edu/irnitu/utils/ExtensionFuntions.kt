package istu.edu.irnitu.utils

import android.content.SharedPreferences

fun SharedPreferences.getStringOrNull(key: String): String? = this.getString(key, null)