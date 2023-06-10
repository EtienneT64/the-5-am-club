package com.etienne.the5amclub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

//TODO Add back BottomNavBarTheme
//import com.etienne.the5amclub.ui.theme.BottomNavBarTheme

class MainActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        val user = Firebase.auth.currentUser

        //If the user object is null, that means there is no current user, so we should point them to signup or login
        if (user != null) {
            Log.d("OnCreation", user.email.toString())

            //Calling the viewModel like this initializes the view model
            //On initial is when it pulls from the database
            viewModel

            setContent {
                //TODO Add back BottomNavBarTheme
                //BottomNavBarTheme {
                val user = Firebase.auth.currentUser
                MainScreen(user)
                //}
            }
        } else {
            Log.d("Send User to LogIn", "There is no current user")
            val intent = Intent(this@MainActivity, LogIn::class.java)
            startActivity(intent)
        }

    }
}