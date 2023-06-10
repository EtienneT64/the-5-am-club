package com.etienne.the5amclub.screens

import android.content.res.Configuration
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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.etienne.the5amclub.ui.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UtilitiesScreen() {
    AppTheme {
        Surface {
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
            Box(
                contentAlignment = Alignment.TopCenter, modifier = Modifier.fillMaxSize()
            )


            {

                Box(
                    modifier = Modifier.size(900.dp, 900.dp), contentAlignment = Alignment.Center
                ) {

                    Box(
                        modifier = Modifier.size(300.dp, 500.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        Row {
                            Text(
                                text = "Age",
                                fontSize = 40.sp,
                            )
                            TextField(
                                value = inputAge,
                                shape = CircleShape,
                                onValueChange = { text -> inputAge = text },
                                modifier = Modifier
                                    .padding(bottom = 180.dp)
                                    .absolutePadding(70.dp)
                            )
                        }
                        Row {
                            Text(
                                text = "Height",
                                fontSize = 40.sp,
                            )
                            TextField(
                                value = inputHeight,
                                shape = CircleShape,
                                onValueChange = { text -> inputHeight = text },
                                modifier = Modifier
                                    .padding(bottom = 30.dp)
                                    .absolutePadding(25.dp)
                            )

                        }

                        Row {
                            Text(
                                text = "Weight",
                                fontSize = 40.sp,
                                modifier = Modifier.padding(top = 120.dp)
                            )
                            TextField(
                                value = inputWeight,
                                shape = CircleShape,
                                onValueChange = { text -> inputWeight = text },
                                modifier = Modifier
                                    .padding(top = 120.dp)
                                    .absolutePadding(25.dp)
                            )

                        }
                        Row {
                            Text(
                                text = "BMI",
                                fontSize = 40.sp,
                                modifier = Modifier.padding(top = 400.dp)
                            )
                            TextField(
                                value = displayBMI,
                                shape = CircleShape,
                                onValueChange = { text -> displayBMI = text },
                                modifier = Modifier
                                    .padding(top = 400.dp)
                                    .absolutePadding(30.dp)
                            )

                        }

                    }
                }

            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()

            ) {
                Text(
                    text = "BMI Calculator",
                    fontSize = 50.sp,
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

                Row {
                    Button(
                        onClick = {
                            displayBMI =
                                BMICalculate(inputWeight.toDouble(), inputHeight.toDouble())

                        }, modifier = Modifier
                            .offset(x = 0.dp, y = 20.dp)
                            .padding(top = 350.dp)

                    ) {
                        Text(
                            text = "CALCULATE BMI", fontSize = 30.sp, fontWeight = FontWeight.Bold

                        )
                    }
                }
            }
        }
    }

}

private fun BMICalculate(weight: Double, height: Double): String {
    var result = (weight / ((height / 100) * (height / 100)))
    var strFormat = String.format("%.1f", result)
    return strFormat
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode"
)
@Composable
fun UtilitiesScreenPreview() {
    AppTheme {
        Surface {
            UtilitiesScreen()
        }
    }

}