package com.example.edgarreyes.tinroofcodetest

import android.util.Log
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import java.io.IOException

class NetworkService(val url:String, val model: UserContract.Model){

    private var mOkHttpClient: OkHttpClient

    init {
        mOkHttpClient = OkHttpClient()
        makeUrlRequest()
    }

    private fun makeUrlRequest(){
        val request = Request.Builder().url(url).build()
        mOkHttpClient.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                Log.e("failure", " -->$e")

            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                try {
                    val jsonData = response.body()!!.string()
                    val jArray = JSONArray(jsonData)
                    model.getUserData(jArray)

                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        })

    }

}
