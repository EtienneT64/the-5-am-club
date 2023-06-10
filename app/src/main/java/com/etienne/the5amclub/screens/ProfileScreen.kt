package com.etienne.the5amclub.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.etienne.the5amclub.R
import com.etienne.the5amclub.UserViewModel
import com.etienne.the5amclub.ui.theme.AppTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

//@OptIn(ExperimentalCoroutinesApi::class)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class)
@SuppressLint("InvalidColorHexValue")
@Composable
fun ProfileScreen() {
    val viewModel = viewModel<UserViewModel>()
    val hasLoaded by viewModel.loading.collectAsState()

    var FullName by remember {
        mutableStateOf("")
    }
    var Email by remember {
        mutableStateOf("")
    }
    var Status by remember {
        mutableStateOf("")
    }

    if (hasLoaded) {
        val currentRealtimeUser = viewModel.viewModelUser.value

        FullName = currentRealtimeUser.userFullName.toString()

        Email = currentRealtimeUser.userEmail.toString()

        Status = currentRealtimeUser.userStatus.toString()

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Log.d("Profile Screen", "The profile screen calls code now")

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {

                Text(
                    text = "PROFILE",
                    fontSize = 50.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.offset(x = 0.dp, y = -10.dp)/*.padding(bottom = 110.dp)*/
                )

                Image(
                    painter = painterResource(id = R.drawable.avatarcolor),
                    contentDescription = "null",
                    // crop the image if it's not a square
                    modifier = Modifier

                        .size(150.dp)
                        .clip(CircleShape)                       // clip to the circle shape
                        .border(5.dp, Color.DarkGray, CircleShape)
                        .offset(x = 0.dp, y = -2.dp)// add a border (optional)
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 2.dp)

                )


                Text(
                    text = "Account Details:",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier/*.offset(x = -80.dp, y = 0.dp)*/.padding(bottom = 25.dp)

                )

                Row {

                    Text(
                        text = "NAME:",
                        fontSize = 25.sp,
                        modifier = Modifier.padding(top = 10.dp),
                    )
                    TextField(

                        value = FullName,
                        onValueChange = {},
                        shape = CircleShape,
                        modifier = Modifier
                            .padding(bottom = 30.dp)
                            .absolutePadding(25.dp)
                    )

                }

                Row {

                    Text(
                        text = "EMAIL:",
                        fontSize = 25.sp,
                        modifier = Modifier.padding(top = 10.dp),
                    )
                    TextField(
                        value = Email,
                        onValueChange = {},
                        shape = CircleShape,
                        modifier = Modifier
                            .padding(bottom = 30.dp)
                            .absolutePadding(25.dp)

                    )
                }

                Row {

                    Text(
                        text = "STATUS:",
                        fontSize = 25.sp,
                        modifier = Modifier.padding(top = 10.dp),
                    )

                    TextField(
                        value = Status,
                        onValueChange = {},
                        shape = CircleShape,/*colors = TextFieldDefaults.textFieldColors(Color.Cyan),*/
                        modifier = Modifier
                            .padding(bottom = 30.dp)
                            .absolutePadding(15.dp)

                    )

                }


                Row {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "null",


                        // crop the image if it's not a square
                        modifier = Modifier

                            .size(150.dp) // clip to the circle shape
                            .border(10.dp, Color.Gray)
                            .offset(x = 0.dp, y = 0.dp)// add a border (optional)
                            .padding(top = 2.dp)

                    )

                }

            }
        }
    } else {


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            CircularProgressBar(hasLoaded)
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode"
)
@Composable
fun ProfileScreenPreview() {
    AppTheme {
        Surface {
            ProfileScreen()
        }
    }
}

@ExperimentalCoroutinesApi
@Composable
fun CircularProgressBar(
    isDisplayed: Boolean
) {
    if (isDisplayed) {
        CircularProgressIndicator()
    }
}


