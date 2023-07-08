package com.etienne.the5amclub

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.etienne.the5amclub.ui.theme.AppTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Suppress("UNUSED_EXPRESSION")
class LogIn : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var currentRealtimeUser: UserModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        currentRealtimeUser = UserModel()

        setContent {
            AppTheme {
                Surface {
                    LogInScreen()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LogInScreen() {
        AppTheme {
            Surface {
                val viewModel = viewModel<UserViewModel>()
                var inputEmail by remember {
                    mutableStateOf("")
                }
                var inputPassword by remember {
                    mutableStateOf("")
                }
                var loginState by remember {
                    mutableStateOf(false)
                }
                val showPassword = remember {
                    mutableStateOf(false)
                }
                if (loginState) {
                    //After sign in, auth current user is assigned
                    //The current user is what the view model uses to grab data from the database
                    viewModel

                    //Swapping the intent makes the login screen go away
                    //Ultimately a temp solution, unless we decide not to fix
                    val intent = Intent(this@LogIn, MainActivity::class.java)
                    startActivity(intent)
                    MainScreen()
                }

                fun userLogIn(email: String, password: String) {
                    auth.signInWithEmailAndPassword(email, password)//Firebase object
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success
                                Toast.makeText(
                                    baseContext,
                                    "Log in successful.",
                                    Toast.LENGTH_SHORT,
                                ).show()
                                loginState = true
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(
                                    baseContext,
                                    "Log in unsuccessful.",
                                    Toast.LENGTH_SHORT,
                                ).show()

                                loginState = false
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
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(x = 0.dp, y = 100.dp),
                ) {
                    Text(
                        text = "Login",
                        fontSize = 50.sp,
                    )
                    Text(text = "Don't Have An Account?")

                    Button(
                        onClick = {
                            inputEmail = ""
                            inputPassword = ""
                            val intent = Intent(this@LogIn, Register::class.java)
                            startActivity(intent)
                        },
                        modifier = Modifier.size(width = 150.dp, height = 35.dp),
                    ) {
                        Text(text = "Sign Up Here")
                    }
                }

                Spacer(modifier = Modifier.height(50.dp))

                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    //Column to group the info buttons and labels
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 10.dp),
                    ) {
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
                            visualTransformation = if (showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            onValueChange = { text ->
                                inputPassword = text
                            },
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
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    //Column to center the register button
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Please Login In To Continue",
                            textDecoration = TextDecoration.Underline
                        )
                        Button(
                            onClick = {
                                val validate = Validate()
                                if (validate.validateEmail(inputEmail) and validate.validatePassword(
                                        inputPassword
                                    )
                                ) {
                                    userLogIn(inputEmail, inputPassword)
                                    inputEmail = ""
                                    inputPassword = ""
                                } else {
                                    Toast.makeText(
                                        baseContext,
                                        "Invalid email or password.",
                                        Toast.LENGTH_SHORT,
                                    ).show()
                                    inputEmail = ""
                                    inputPassword = ""
                                }

                            }, modifier = Modifier.size(width = 200.dp, height = 50.dp)
                        ) {
                            Text(
                                text = "Login", fontSize = 25.sp
                            )
                        }
                    }
                }
            }
        }
    }

    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode"
    )
    @Composable
    fun LogInPreview() {
        AppTheme {
            Surface {
                LogInScreen()
            }
        }
    }
}