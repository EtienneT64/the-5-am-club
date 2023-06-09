package com.etienne.the5amclub

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
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
import com.etienne.the5amclub.ui.theme.AppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Register : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var userRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        userRef =
            Firebase.database("https://the5amclub-dfb7f-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("users")


        setContent {
            RegisterScreen()
        }

    }


    private fun userCreation(
        email: String, password: String, firstname: String, starsign: String? = null
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success
                val user = Firebase.auth.currentUser
                val token = user?.getIdToken(false)
                //val username = user?.email.toString()
                Toast.makeText(
                    baseContext,
                    "$token!",
                    Toast.LENGTH_SHORT,
                ).show()

                //Adding User to Firebase Realtime Database
                //
                addUserToRealtime(firstname, email, starsign)

                Firebase.auth.signOut()

                val intent = Intent(this@Register, LogIn::class.java)
                startActivity(intent)
            } else {
                // If sign in fails, display a message to the user.
                Toast.makeText(
                    baseContext,
                    "Authentication failed.",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(
                this,
                exception.localizedMessage,
                Toast.LENGTH_SHORT,
            ).show()
        }

    }


    private fun addUserToRealtime(firstname: String, email: String, starsign: String?) {
        val userID = userRef.push().key!!

        val user = UserModel(userID, firstname, email, starsign)

        userRef.child(userID).setValue(user).addOnCompleteListener {
            Toast.makeText(
                baseContext,
                "User added!",
                Toast.LENGTH_SHORT,
            ).show()
        }.addOnFailureListener { exception ->
            Toast.makeText(
                this,
                exception.localizedMessage,
                Toast.LENGTH_SHORT,
            ).show()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun RegisterScreen() {
        var inputFullName by remember {
            mutableStateOf("")
        }
        var inputEmail by remember {
            mutableStateOf("")
        }
        var inputPassword by remember {
            mutableStateOf("")
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = 0.dp, y = 100.dp),
        ) {
            Text(
                text = "Create New", fontSize = 50.sp,

                modifier = Modifier.absolutePadding(20.dp)

            )

            Text(
                text = "Account",
                fontSize = 50.sp,

                )

            Text(text = "Already Registered?")


            Button(
                onClick = {
                    inputEmail = ""
                    inputPassword = ""
                    inputFullName = ""
                    val intent = Intent(this@Register, LogIn::class.java)
                    startActivity(intent)

                }, modifier = Modifier.size(width = 150.dp, height = 35.dp)


            ) {
                Text(text = "LOG IN HERE")
            }
        }
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()

        ) {

            //Column to group the info buttons and labels
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp)
            ) {
                Text(
                    text = "Full Name",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .absolutePadding(45.dp)
                        .padding(top = 160.dp)

                )
                OutlinedTextField(value = inputFullName,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onValueChange = { text ->
                        inputFullName = text

                    })
                Text(
                    text = "Email",
                    fontSize = 20.sp,
                    modifier = Modifier.absolutePadding(45.dp)
                )
                OutlinedTextField(value = inputEmail,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onValueChange = { text ->
                        inputEmail = text
                    })
                Text(
                    text = "Password",
                    fontSize = 20.sp,
                    modifier = Modifier.absolutePadding(45.dp)
                )
                OutlinedTextField(value = inputPassword,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onValueChange = { text ->
                        inputPassword = text
                    })
            }
            //Column to center the register button
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        userCreation(inputEmail, inputPassword, inputFullName)
                        inputEmail = ""
                        inputPassword = ""
                        inputFullName = ""
                    }, modifier = Modifier.size(width = 250.dp, height = 50.dp)

                ) {
                    Text(
                        text = "Sign Up", fontSize = 25.sp
                    )
                }


            }
        }

    }

    @Preview(uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode")
    @Composable
    fun RegisterPreview() {
        AppTheme {
            Surface {
                RegisterScreen()
            }
        }
    }
}