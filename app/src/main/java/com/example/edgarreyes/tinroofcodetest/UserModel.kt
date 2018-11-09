package com.example.edgarreyes.tinroofcodetest

import android.util.Log
import org.json.JSONArray

class UserModel(val presenter:UserContract.Presenter): UserContract.Model{

    private var jsonArray: JSONArray? = null

    override fun getUserData(jsonArray: JSONArray) {
        this.jsonArray = jsonArray
        presenter.passUser(jsonArray)
    }

    override fun loadUserData() {
        val network = NetworkService(jsonUrl, this)
    }

    companion object {
        val jsonUrl = "http://jsonplaceholder.typicode.com/todos"
    }

    fun getJsonArray():JSONArray?{
        return jsonArray
    }

}