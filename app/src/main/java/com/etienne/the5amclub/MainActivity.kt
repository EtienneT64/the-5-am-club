package com.etienne.the5amclub

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

//TODO Add back BottomNavBarTheme
//import com.etienne.the5amclub.ui.theme.BottomNavBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        //Testing Code START
        val mappy = mapOf("one" to mapOf("two" to 2))
        var stringToMap =" {one={two=2}}"
        stringToMap = mappy["one"].toString()
        stringToMap = stringToMap.replace("{", "")
        stringToMap = stringToMap.replace("}", "")

        val map = stringToMap.split("=")

        Log.d("Map", map.toString())


        Handler(Looper.getMainLooper()).postDelayed({
            val user = Firebase.auth.currentUser

            //If the user object is null, that means there is no current user, so we should point them to signup or login
            if (user != null) {
                Log.d("OnCreation", user.email.toString())
            } else {
                Log.d("Send User to LogIn", "There is no current user")
                val intent = Intent(this@MainActivity, LogIn::class.java)
                startActivity(intent)
            }
        }, 2000)
        //Testing Code END



        setContent {
            //TODO Add back BottomNavBarTheme
            //BottomNavBarTheme {
                Log.d("FirebaseInitialize","Just Before MainScreen Called")
                val user = Firebase.auth.currentUser
                MainScreen(user)
            //}
        }
    }
}