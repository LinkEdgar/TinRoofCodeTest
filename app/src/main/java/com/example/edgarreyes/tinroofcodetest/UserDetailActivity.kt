package com.example.edgarreyes.tinroofcodetest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        handleIntentData()
    }

    private fun handleIntentData(){
        val user = intent.getParcelableExtra<User>(intentKey)
        userid_tv.setText(user.userId)
        //TODO display tasks for each userId
    }

    companion object {
        val intentKey = "user"
    }
}
