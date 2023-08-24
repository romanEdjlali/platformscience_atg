package com.roman.platformscienceapp.util

import android.content.Context
import java.io.IOException

/*
* Class: Utils
* Owner: Roman Edjlali
* Date Created: 08/23/2023 6:23 PM
*/
fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}