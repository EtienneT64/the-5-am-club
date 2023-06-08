package com.etienne.the5amclub

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

data class UserModel(var userID: String? = null,
var userFullName: String? = null,
var userEmail: String? = null,
var userStarSign: String? = null,
var userStatus: String? = null,
){
    fun getFullName(): String{
        if (userFullName != null)
        {
        return userFullName.toString()
        }
        else{
            return "null"
        }
    }
}

