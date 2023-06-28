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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

            var classification by remember{
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

                        }
                        Row {
                            Text(
                                text = "Age",
                                fontSize = 40.sp,
                            )
                            TextField(
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                value = inputAge,
                                shape = CircleShape,
                                onValueChange = { text -> inputAge = text },
                                modifier = Modifier
                                    .padding(bottom = 180.dp)
                                    .absolutePadding(70.dp)
                            )
                            Text(
                                text = "Yrs",
                                fontSize = 30.sp,
                            )
                        }
                        Row {
                            Text(
                                text = "Height",
                                fontSize = 40.sp,
                            )
                            TextField(
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                value = inputHeight,
                                shape = CircleShape,
                                onValueChange = { text -> inputHeight = text },
                                modifier = Modifier
                                    .padding(bottom = 30.dp)
                                    .absolutePadding(25.dp)
                            )
                            Text(
                                text = "cm",
                                fontSize = 30.sp,
                            )

                        }

                        Row {
                            Text(
                                text = "Weight",
                                fontSize = 40.sp,
                                modifier = Modifier.padding(top = 120.dp)
                            )
                            TextField(
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
                                value = inputWeight,
                                shape = CircleShape,
                                onValueChange = { text -> inputWeight = text },
                                modifier = Modifier
                                    .padding(top = 120.dp)
                                    .absolutePadding(25.dp)
                            )
                            Text(
                                text = "kg",
                                fontSize = 30.sp,
                            )

                        }
                        Row {
                            Text(

                                text = "BMI",
                                fontSize = 40.sp,
                                modifier = Modifier.padding(top = 400.dp)
                            )
                            TextField(
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent
                                ),
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
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,

                    )
                Text(
                    text = "Classification:",
                    fontSize = 35.sp,
                )
                Text(
                    text = classification,
                    fontSize = 25.sp,
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



                            if (displayBMI.toDouble() < 16.0) {
                                classification = "Severe Thinness - we recommend you urgently up your calorie intake. Focus less on cardio and begin muscle building exercises like squats, push ups and press ups."
                            }
                            if (displayBMI.toDouble() in 16.0..17.0) {
                                classification = "Moderate Thinness - Maintain a balanced diet with sufficient protein, healthy fats, and complex carbohydrates, and engage in a combination of resistance training and cardiovascular exercises to improve overall fitness and build lean muscle."
                            }
                            if (displayBMI.toDouble() in 17.0..18.0) {
                                classification = "Mild Thinness - Consume a balanced diet with adequate protein, healthy fats, and complex carbohydrates, while incorporating resistance training exercises to build lean muscle mass and improve overall body composition."
                            }

                            if (displayBMI.toDouble() in 18.0..25.0) {
                                classification = "Normal - Continue maintaining a balanced diet with a variety of nutrients and incorporate a combination of aerobic exercises, strength training, and flexibility exercises for optimal health and fitness."
                            }
                            if (displayBMI.toDouble() in 25.0..30.0) {
                                classification = "Overweight - Adopt a calorie-controlled diet with emphasis on whole foods, portion control, and regular physical activity, including a mix of cardiovascular exercises and strength training, to achieve gradual and sustainable weight loss."
                            }
                            if (displayBMI.toDouble() in 30.0..40.0) {
                                classification = "Obese - Follow a structured and supervised weight loss program tailored to individual needs, focusing on a balanced and reduced-calorie diet, along with regular exercise incorporating aerobic activities, strength training, and gradual progressions to facilitate weight loss and improve overall health."
                            }


                        }, modifier = Modifier
                            .offset(x = 0.dp, y = 20.dp)
                            .padding(top = 280.dp)

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
    uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode",
    device = "id:pixel_4", showSystemUi = true
)
@Composable
fun UtilitiesScreenPreview() {
    AppTheme {
        Surface {
            UtilitiesScreen()
        }
    }

}