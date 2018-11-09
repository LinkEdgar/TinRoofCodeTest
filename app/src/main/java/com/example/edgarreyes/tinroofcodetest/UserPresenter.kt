package com.example.edgarreyes.tinroofcodetest

import android.os.Bundle
import android.util.Log
import org.json.JSONArray
import org.json.JSONException

class UserPresenter(val view: UserContract.View): UserContract.Presenter{

    private  lateinit var userModel:UserModel

    init {
        initPresenter()
    }

    override fun initiateLoadUser(bundle: Bundle?) {
        if(bundle == null){
            userModel.loadUserData()
        }else{
            //TODO: reload data as to not make another network call
        }
    }

    override fun passUser(jsonArray: JSONArray) {
         for (x in 0..(jsonArray.length()-1)){
             try {
                 val json = jsonArray.getJSONObject(x)
                 val userId = json.getString(userIdJson)
                 val id = json.getString(idJson)
                 val title = json.getString(titleJson)
                 val completed = json.getBoolean(completedJson)
                 val user = User(userId, id, title, completed )
                 view.setUserData(user, x)
             }catch(e: JSONException){
                 e.printStackTrace()
             }
         }
    }


    fun initPresenter(){
        userModel = UserModel(this)
    }

    companion object {
        val userIdJson = "userId"
        val idJson = "id"
        val titleJson = "title"
        val completedJson = "completed"
    }

}