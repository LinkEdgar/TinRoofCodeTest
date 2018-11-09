package com.example.edgarreyes.tinroofcodetest

import android.os.Parcel
import android.os.Parcelable

data class User(var userId: String?, var numberOfTaks: Int? = 0): Parcelable{

    private var tasks: ArrayList<Task>

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readValue(Int::class.java.classLoader) as? Int) {

    }

    init {
        tasks = ArrayList()
    }

    fun addTask(task: Task){
        tasks.add(task)
    }

    fun amountOfTasks(): Int{
        return tasks.size
    }

    fun getTask(position: Int):Task{
        return tasks[position]
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userId)
        parcel.writeValue(numberOfTaks)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}