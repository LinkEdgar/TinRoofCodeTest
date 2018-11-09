package com.example.edgarreyes.tinroofcodetest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity(), UserContract.View, UserAdapter.UserIdClick {

    private lateinit var presenter: UserPresenter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mUserAdapter: UserAdapter
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private lateinit var mData: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        presenter = UserPresenter(this)
        presenter.initiateLoadUser(savedInstanceState)
    }

    override fun setUserData(user: User, position: Int) {
        this@MainActivity.runOnUiThread{
            try {
                addDataToAdapter(user, position)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun addDataToAdapter(user:User, position: Int){
        mData.add(user)
        mUserAdapter.notifyItemInserted(position)
    }

    override fun initView() {
        mRecyclerView = user_recycler_view
        mLayoutManager = LinearLayoutManager(applicationContext)
        mData = ArrayList()
        mUserAdapter = UserAdapter(mData, applicationContext, this)
        mRecyclerView.layoutManager = mLayoutManager
        mRecyclerView.adapter = mUserAdapter
    }

    override fun onClick(position: Int) {
        val detailActivityItent = Intent(this, UserDetailActivity::class.java)
        detailActivityItent.putExtra(intentExtraUser, mData[position])
        startActivity(detailActivityItent)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        //TODO persist data to avoid unnecessary network calls
    }

    companion object {
        val intentExtraUser = "user"
    }
}
