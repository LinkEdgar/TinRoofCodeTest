package com.example.edgarreyes.tinroofcodetest

import android.os.Bundle
import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class UserPresenter(val view: UserContract.View): UserContract.Presenter{

    private  lateinit var userModel:UserModel
    private lateinit var set: HashMap<String,User>

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
                 var user: User? = null
                 if(!set.contains(userId)){
                     user = User(userId)
                     set.put(userId,user)
                     user.addTask(createTask(json))
                     view.setUserData(user, x)
                 }else{
                     user =set.get(userId)
                     user?.addTask(createTask(json))

                 }

             }catch(e: JSONException){
                 e.printStackTrace()
             }
         }
    }

    private fun createTask(jsonObject: JSONObject): Task{
        val id = jsonObject.getString(idJson)
        val title = jsonObject.getString(titleJson)
        val completed = jsonObject.getBoolean(completedJson)
        val task = Task(id,title,completed)
        return task
    }


    private fun initPresenter(){
        set = HashMap()
        userModel = UserModel(this)
    }

    companion object {
        val userIdJson = "userId"
        val idJson = "id"
        val titleJson = "title"
        val completedJson = "completed"
    }

}