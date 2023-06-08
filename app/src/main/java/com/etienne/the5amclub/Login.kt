package com.etienne.the5amclub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bottomnavbar.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogIn : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var currentRealtimeUser: UserModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        currentRealtimeUser = UserModel()

        setContent{
            LogInScreen()
        }
    }


    private fun userLogIn(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success
                    Toast.makeText(
                        baseContext,
                        "Log in successful.",
                        Toast.LENGTH_SHORT,
                    ).show()

                    val user = Firebase.auth.currentUser
                    val intent = Intent(this@LogIn, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext,
                        "Log in unsuccessful.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
            .addOnFailureListener { exception -> //Shows the error message that caused the log in to break
                Toast.makeText(
                    baseContext,
                    exception.localizedMessage,
                    Toast.LENGTH_SHORT,
                ).show()
            }
    }
    //Checks if there is a user signed in and returns an email if there is
    private fun checkUser(): String {
        val user = Firebase.auth.currentUser
        return if ((user != null) && (user.email != null))
            user.email.toString()
        else
            "No Current User"

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LogInScreen() {
        var inputEmail by remember {
            mutableStateOf("")
        }
        var inputPassword by remember{
            mutableStateOf("")
        }
        var currentEmail by remember{
            mutableStateOf("")
        }
        var firstName by remember{
            mutableStateOf("")
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = 0.dp, y = 150.dp),
        ) {
            Text(
                text = "Log In",
                fontSize = 50.sp,
            )
        }
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {

            //Column to group the info buttons and labels
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp),
            ) {
                Text(
                    text = "Email",
                    color = Color.Blue,
                    fontSize = 20.sp,
                )
                OutlinedTextField(
                    value = inputEmail,
                    onValueChange = { text ->
                        inputEmail = text
                    }
                )
                Text(
                    text = "Password",
                    color = Color.Blue,
                    fontSize = 20.sp,
                )
                OutlinedTextField(
                    value = inputPassword,
                    onValueChange = { text ->
                        inputPassword = text
                    }
                )
            }
            //Column to center the register button
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(onClick = {
                    //Add empty input validation
                    userLogIn(inputEmail, inputPassword)
                    inputEmail = ""
                    inputPassword = ""
                }) {
                    Text(text="Log In")
                }
                Button(onClick = {
                    currentEmail = checkUser()
                    Log.d("FB", currentEmail)
                    if (currentEmail != "No Current User") {
                        currentRealtimeUser = currentRealtimeUser.getUserObject(currentEmail)
                        if (currentRealtimeUser.userFullName != null) {
                            firstName = currentRealtimeUser.userFullName.toString()
                        }
                        else{
                            firstName = "The name is null."
                            Log.d("Realtime User", "The thingy is blank")
                        }
                    }

                }) {
                    Text(text="Check Current User")
                }
                Text(
                    text = "Email: $currentEmail"
                )
                Text(
                    text = "Name: $firstName"
                )
                Button(onClick = {
                    inputEmail = ""
                    inputPassword = ""
                    Firebase.auth.signOut()
                    val intent = Intent(this@LogIn, Register::class.java)
                    startActivity(intent)
                }) {
                    Text(text="Sign Up")
                }
                Button(onClick = {
                    currentEmail = checkUser()
                    if (currentEmail != "No Current User") {
                        currentRealtimeUser = currentRealtimeUser.getUserObject(currentEmail)
                        if (currentRealtimeUser.userFullName != null) {
                            firstName = currentRealtimeUser.userFullName.toString()
                        }
                        else{
                            firstName = "The name is null."
                            Log.d("Realtime User", "The thingy is blank")
                        }
                    }

                }) {
                    Text(text="Test Callback")
                }
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun LogInPreview() {
        LogInScreen()
    }
}