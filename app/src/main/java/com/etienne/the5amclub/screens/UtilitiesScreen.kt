package com.etienne.the5amclub.screens


import android.widget.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UtilitiesScreen() {
    var inputAge by remember {
        mutableStateOf("")
    }
    var inputHeight by remember {
        mutableStateOf("")
    }
    var inputWeight by remember {
        mutableStateOf("")
    }
    var displayBMI by remember {
        mutableStateOf("")
    }
    val BMIBackground = Color(0xff333333)
    val InsideBox = Color(0xff0d0d0d)
    val brush1 = Brush.linearGradient(listOf(Color.Blue, Color.Black))
    val brush2 = Brush.linearGradient(listOf(Color.DarkGray, Color.Black))

    Box(contentAlignment = Alignment.TopCenter,
        modifier = Modifier.fillMaxSize())





    {

        Box(modifier = Modifier
            .size(900.dp, 900.dp)
            .background(brush2),
            contentAlignment = Alignment.Center){

            Box(modifier = Modifier
                .size(300.dp,500.dp)
                .background(Color.Black),
                contentAlignment = Alignment.Center){

                Row() {
                    Text(
                        text = "Age",
                        fontSize = 40.sp,
                        color = Color.White,




                        )
                  TextField(
                        value = inputAge,
                        shape = CircleShape,
                        onValueChange = {text -> inputAge = text},
                        modifier = Modifier
                            .padding(bottom = 180.dp)
                            .absolutePadding(70.dp)
                            .background(Color.White)




                    )


                }
                Row() {
                    Text(
                        text = "Height",
                        fontSize = 40.sp,
                        color = Color.White


                    )
                    TextField(
                        value = inputHeight,
                        shape = CircleShape,
                        onValueChange = { text -> inputHeight = text},
                        modifier = Modifier
                            .padding(bottom = 30.dp)
                            .absolutePadding(25.dp)
                            .background(Color.White)

                    )

                }

                Row() {
                    Text(
                        text = "Weight",
                        fontSize = 40.sp,
                        color = Color.White,
                        modifier = Modifier
                            .padding(top = 120.dp)


                    )
                    TextField(
                        value = inputWeight,
                        shape = CircleShape,
                        onValueChange = {text -> inputWeight = text},
                        modifier = Modifier
                            .padding(top = 120.dp)
                            .absolutePadding(25.dp)
                            .background(Color.White)
                    )

                }
                Row() {
                    Text(
                        text = "BMI",
                        fontSize = 40.sp,
                        color = Color.White,
                        modifier = Modifier
                            .padding(top = 400.dp)


                    )
                    TextField(value = displayBMI,
                        shape = CircleShape,
                        onValueChange = {text -> displayBMI = text},
                        modifier = Modifier
                            .padding(top = 400.dp)
                            .absolutePadding(30.dp)
                            .background(Color.White)


                    )

                }

            }
        }

    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()


    ) {
        Text(
            text = "BMI Calculator",
            fontSize = 50.sp,
            color = Color.White


        )


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 215.dp)



        )

        {


        }

        Row(

        ) {
            Button(
                colors = ButtonDefaults.buttonColors( Color.Gray),
                onClick = {
                    displayBMI =
                        BMICalculate(inputWeight.toDouble(), inputHeight.toDouble())

                },
                modifier = Modifier.offset(x = 0.dp, y = 20.dp)
                    .padding(top = 400.dp)


            ) {
                Text(
                    text = "CALCULATE BMI",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold


                )
            }

        }
    }
}

private fun BMICalculate(weight: Double, height: Double): String {
    var result = (weight / ((height / 100) * (height / 100)));
    var strFormat = String.format("%.1f", result)
    return strFormat
}
@Composable
@Preview
fun UtilitiesScreenPreview() {
    UtilitiesScreen()
}