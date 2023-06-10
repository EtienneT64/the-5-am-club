package com.etienne.the5amclub.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.etienne.the5amclub.ui.theme.AppTheme


data class Workout(val title: String)

val workoutsList = listOf(
    Workout("Workout 1"), Workout("Workout 2"), Workout("Workout 3")
)


class WorkoutsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface {
                    WorkoutsScreen(true)
                }
            }

        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutsScreen(isVisible: Boolean) {
    var pageBool by remember {
        mutableStateOf(false)
    }

    AppTheme {
        Surface {
            Scaffold(topBar = {
                TopAppBar(title = { Text("Workouts") })
            }, content = {
                if(isVisible) {
                    Column(
                        modifier = Modifier
                            .padding(top = 100.dp)
                            .padding(50.dp)
                    ) {
                        workoutsList.forEach { workout ->
                            WorkoutBlock(workout = workout)
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
                else if(isVisible == false){
                    Column(
                        modifier = Modifier
                            .padding(top = 100.dp)
                            .padding(50.dp)
                    ) {
                        workoutsList.forEach { workout ->
                            WorkoutBlock(workout = workout)
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            })
        }
    }

}

@Composable
fun WorkoutBlock(workout: Workout) {
    AppTheme {
        Surface {

            if (pageBool){
                WorkoutsScreen(false)
            }
            Card(
                modifier = Modifier.fillMaxWidth(), elevation = 4.dp
            ) {
                ConstraintLayout(
                    modifier = Modifier.padding(16.dp)
                ) {
                    val (text, icon) = createRefs()

                    Text(text = workout.title, modifier = Modifier.constrainAs(text) {
                        start.linkTo(parent.start)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(icon.start)
                        width = androidx.constraintlayout.compose.Dimension.fillToConstraints
                    })

                    IconButton(onClick = {
                         pageBool = true

                        // Handle view icon click here
                    }, modifier = Modifier.constrainAs(icon) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "View",
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
fun WorkoutsScreenPreview() {
    AppTheme {
        Surface {
            WorkoutsScreen(false)
        }

    }
}

