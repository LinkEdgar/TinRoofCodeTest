package com.example.edgarreyes.tinroofcodetest

import android.os.Bundle
import org.json.JSONArray

interface UserContract{
    interface View{
        fun initView() //initializes views and adapters
        fun setUserData(user:User, position: Int)
    }

    interface Presenter{
        fun passUser(jsonArray: JSONArray)
        fun initiateLoadUser(bundle:Bundle?)
    }

    interface Model{
        fun loadUserData()
        fun getUserData(jsonArray: JSONArray)
    }

}