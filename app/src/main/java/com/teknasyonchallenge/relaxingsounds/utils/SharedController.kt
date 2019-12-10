package com.teknasyonchallenge.relaxingsounds.utils

import android.content.Context
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class SharedController() {
    private lateinit var context: Context
    constructor(context: Context):this(){
        this.context = context
    }

    fun getAssetJsonData(): String {
        var json: String = ""
        json = try {
            val `is`: InputStream = context.assets.open("voices.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}