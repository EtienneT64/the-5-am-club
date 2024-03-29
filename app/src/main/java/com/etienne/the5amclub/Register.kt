package com.etienne.the5amclub

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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
            AppTheme {
                Surface {
                    RegisterScreen()
                }
            }
        }
    }


    private fun userCreation(
        email: String, password: String, firstname: String
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success

                addUserToRealtime(firstname, email)

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


    private fun addUserToRealtime(firstname: String, email: String) {
        val userID = userRef.push().key!!

        val user = UserModel(userID, firstname, email, "Registered")

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
        AppTheme {
            Surface {
                var inputFullName by remember {
                    mutableStateOf("")
                }
                var inputEmail by remember {
                    mutableStateOf("")
                }
                var inputPassword by remember {
                    mutableStateOf("")
                }
                val showPassword = remember {
                    mutableStateOf(false)
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 70.dp),
                ) {
                    Text(
                        text = "Create New", fontSize = 50.sp, modifier = Modifier.padding(5.dp)
                    )

                    Text(
                        text = "Account", fontSize = 50.sp
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

                Spacer(modifier = Modifier.height(500.dp))

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 10.dp)
                    ) {
                        Text(
                            text = "Full Name",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 45.dp, top = 160.dp)
                        )
                        OutlinedTextField(value = inputFullName,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            onValueChange = { text ->
                                inputFullName = text
                            })
                        Text(
                            text = "Email",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 45.dp)
                        )
                        OutlinedTextField(value = inputEmail,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            onValueChange = { text ->
                                inputEmail = text
                            })
                        Text(
                            text = "Password",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 45.dp)
                        )
                        OutlinedTextField(value = inputPassword,
                            visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            onValueChange = { text -> inputPassword = text },
                            trailingIcon = {
                                IconButton(onClick = {
                                    showPassword.value = !showPassword.value
                                }) {
                                    Icon(
                                        tint = MaterialTheme.colorScheme.inverseSurface,
                                        imageVector = Icons.Default.Visibility,
                                        contentDescription = "View",
                                    )
                                }
                            })

                        Spacer(modifier = Modifier.height(40.dp))

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                onClick = {
                                    val validate = Validate()
                                    if (inputFullName.isNotBlank()) {
                                        if (validate.validateEmail(inputEmail) and validate.validatePassword(
                                                inputPassword
                                            )
                                        ) {
                                            userCreation(inputEmail, inputPassword, inputFullName)
                                            inputEmail = ""
                                            inputPassword = ""
                                            inputFullName = ""
                                        } else {
                                            Toast.makeText(
                                                baseContext,
                                                "Invalid name, email or password.",
                                                Toast.LENGTH_SHORT,
                                            ).show()
                                            inputEmail = ""
                                            inputPassword = ""
                                            inputFullName = ""
                                        }
                                    }
                                }, modifier = Modifier.size(width = 250.dp, height = 50.dp)
                            ) {
                                Text(text = "Sign Up", fontSize = 25.sp)
                            }
                        }
                    }
                }
            }
        }
    }


    @Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true, name = "Dark Mode")
    @Composable
    fun RegisterPreview() {
        AppTheme {
            Surface {
                RegisterScreen()
            }
        }
    }
}

//Testing
@Composable
fun Boxer() {
    Box(
        //horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            var quote by remember {
                mutableStateOf("")
            }


            quote = "The only bad workout is the one that didn't happen. Keep pushing forward."


            androidx.compose.material.Text(
                text = quote,
                fontSize = androidx.compose.material.MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.weight(3f)
            )


            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "null",
                alignment = Alignment.TopEnd,

                // crop the image if it's not a square
                modifier = Modifier

                    .size(90.dp) // clip to the circle shape
                    .border(10.dp, Color.Gray)
                    .offset(
                        x = 0.dp,
                        y = 0.dp
                    )// add a border (optional)
                    .padding(bottom = 0.dp)
                    .weight(1f, fill = false)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoxerPreview() {
    AppTheme {
        Boxer()
    }
}
