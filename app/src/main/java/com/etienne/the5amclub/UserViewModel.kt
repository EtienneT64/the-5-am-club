package com.etienne.the5amclub

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseException
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UserViewModel(): ViewModel() {
    var loading = MutableStateFlow(false)
    val viewModelUser : MutableState<UserModel> = mutableStateOf(UserModel())

    init{
        getUser()
    }

    private fun getUser(){
        viewModelScope.launch(Dispatchers.IO){
            loading.value = false
            viewModelUser.value = getCurrentRealtimeUser()
            Log.d("Inside Coroutine", viewModelUser.value.userFullName.toString())
            loading.value = true
        }
    }

    private suspend fun getCurrentRealtimeUser(): UserModel {
        var tempUser: UserModel
        val user = Firebase.auth.currentUser
        val email = user!!.email.toString()
        val userRef =
            Firebase.database("https://the5amclub-dfb7f-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("users")

        try {
            val snapshot = userRef.get().await()

            for (userSnap in snapshot.children) {
                tempUser = userSnap.getValue(UserModel::class.java)!!
                if (tempUser.userEmail == email) {
                    Log.d("UserObject", "Found correct user.")
                    return tempUser
                }
            }
        }catch(e: DatabaseException){
            Log.e("UserModelDatabase", e.toString())
        }


        //return mapOf("userEmail" to "null")
        Log.d("UserObject", "Could not find correct user.")
        return UserModel()

    }
}