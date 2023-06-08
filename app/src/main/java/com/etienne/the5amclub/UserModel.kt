package com.etienne.the5amclub

import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await

class UserModel(
    var userID: String? = null,
    var userFullName: String? = null,
    var userEmail: String? = null,
    var userStarSign: String? = null,
    var userStatus: String? =null,
){
    private lateinit var userRef: DatabaseReference
    private lateinit var tempUser: UserModel

    fun getUserObject(email: String): UserModel = runBlocking{
        FirebaseApp.initializeApp(this)
        var userObject = getCurrentRealtimeUser(email)
        return@runBlocking userObject

    }


    private suspend fun getCurrentRealtimeUser(email: String): UserModel {
        userRef =
            Firebase.database("https://the5amclub-dfb7f-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("users")


        val mappy = mapOf("one" to mapOf("two" to 2))

        val snapshot = userRef.get().await()

        Log.d("UserModel", "User should be called")

        for (userSnap in snapshot.children) {
            tempUser = userSnap.getValue(UserModel::class.java)!!
            Log.d("UserObject", tempUser.userEmail.toString())
            if (tempUser.userEmail == email) {
                Log.d("UserObject", "Found correct user.")
                return tempUser
            }
        }

        //return mapOf("userEmail" to "null")
        Log.d("UserObject", "Could not find correct user.")
        return UserModel()
    }

    //Using callbacks
    /*fun getUserDetails(email: String, myCallback: UserCallback){
        var userRef = Firebase.database("https://fir-auth-826d9-default-rtdb.europe-west1.firebasedatabase.app").getReference("users")
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnap in snapshot.children) {
                        tempUser = userSnap.getValue(UserModel::class.java)!!
                        Log.d("FB", tempUser?.userEmail!!)
                        if (tempUser.userEmail == email) {
                            Log.d("FB", "Found correct user.")
                            myCallback.onCallback(tempUser.userEmail.toString())
                            break
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("FB", "Find User cancelled.")
            }

        })
    }*/

    //Try using coroutines

    //Using callbacks
    /*fun getUserObject(email: String) {
        getUserDetails(email, object: UserCallback{
            override fun onCallback(value: String) {
                UserModel.userEmail = value
                Log.d("Callback", "This email is $value")
            }
        })

    }

    interface UserCallback{
        fun onCallback(value: String)
        }
    }*/

    /* @OptIn(ExperimentalCoroutinesApi::class)
 fun getString(email: String, ExampleViewModel: ViewModel): String {
         val result = ExampleViewModel.viewModelScope.async{
             //var mappy = mapOf("userID" to "123")
             var mappy = getCurrentRealtimeUser(email)
             mappy
         }

     result.invokeOnCompletion {
         if (it == null){
             var userMap = result.getCompleted()

             userEmail = userMap["userEmail"]
         }
     }

     return userEmail.toString()

 }*/


}

