package com.example.edgarreyes.tinroofcodetest

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.user_container.view.*

class UserAdapter(var data: ArrayList<User>, var context: Context, var mOnClick: UserIdClick): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    interface UserIdClick{
        fun onClick(position: Int)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = data[position]
        holder.userIdTextView.setText(user.userId)
        holder.view.setOnClickListener{mOnClick.onClick(position)}
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val viewHolder = UserViewHolder(LayoutInflater.from(context).inflate(R.layout.user_container,parent,false))
        return viewHolder
    }


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val userIdTextView = itemView.userid_tv
        val view = itemView
    }
}