package com.etienne.the5amclub.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.etienne.the5amclub.ui.theme.AppTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.TreeMap


data class Workout(val title: String)

val workoutsList = listOf(
    Workout("Press-ups"),
    Workout("Step-Ups"),
    Workout("Superman"),
    Workout("Walking-lunges"),
    Workout("Squats")
)

data class DBWorkout(
    val WorkoutName: String? = null,
    val Reps: String? = null,
    val Benefits: String? = null,
    val Steps: Map<String, String>? = null,
    val Image: String? = null
)

class WorkoutsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface {
                    WorkoutsScreen()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutsScreen() {
    val viewModel = viewModel<WorkoutsViewModel>()
    // Variable for loading a workout when clicked
    val pageBool = viewModel.pageBool
    AppTheme {
        if (!pageBool) {
            Surface {
                Scaffold(topBar = {
                    CenterAlignedTopAppBar(title = {
                        Text(
                            "Workouts",
                            fontSize = 50.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                        )
                    })
                }, content = {
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
                })
            }
            // Loads individual workout
        } else {
            Surface {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(25.dp)
                ) {
                    val workoutName = viewModel.workoutName
                    var name by remember {
                        mutableStateOf("")
                    }
                    var benefits by remember {
                        mutableStateOf("")
                    }
                    var reps by remember {
                        mutableStateOf("")
                    }
                    var steps by remember {
                        mutableStateOf(mapOf("" to ""))
                    }
                    var image by remember {
                        mutableStateOf("")
                    }
                    // Database connection
                    val workoutRef =
                        Firebase.database("https://the5amclub-dfb7f-default-rtdb.europe-west1.firebasedatabase.app/")
                            .getReference("Workouts")
                    workoutRef.child(workoutName)
                        .addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if (snapshot.exists()) {
                                    // Load information from database
                                    val tempWorkout = snapshot.getValue(DBWorkout::class.java)!!
                                    name = tempWorkout.WorkoutName.toString()
                                    benefits = tempWorkout.Benefits.toString()
                                    reps = tempWorkout.Reps.toString()
                                    steps = tempWorkout.Steps!!
                                    image = tempWorkout.Image.toString()
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                Log.d("WorkoutDB", "Workout details could not be called")
                            }
                        })
                    // Structure of loaded workout

                    Column(
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ) {
                        Text(text = "Workout name:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(text = name)
                        Text(text = "Benefits:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Benefits: $benefits")
                        Text(text = "Reps:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Reps: $reps")
                        Text(text = "Steps:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        val sortedSteps = TreeMap(steps)
                        sortedSteps.forEach { step ->
                            Text(text = "${step.key}: ${step.value}")
                        }
                        AsyncImage(
                            model = image,
                            contentDescription = null,
                            modifier = Modifier.padding(15.dp),
                        )


                    }
                }
            }
        }
    }
}


@Composable
fun WorkoutBlock(workout: Workout) {
    AppTheme {
        Surface(contentColor = MaterialTheme.colorScheme.surface) {
            val viewModel = viewModel<WorkoutsViewModel>()
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 4.dp,
                backgroundColor = MaterialTheme.colorScheme.secondary
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
                    // onClick event to load workout when eye icon is clicked
                    IconButton(onClick = {
                        viewModel.changeWorkout(workout.title.replace(" ", ""))
                        viewModel.setTrue()
                        // Handle view icon click here
                    }, modifier = Modifier.constrainAs(icon) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }) {
                        Icon(
                            tint = MaterialTheme.colorScheme.background,
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "View",
                        )
                    }
                }
            }
        }
    }
}

class WorkoutsViewModel : ViewModel() {
    // ViewModel class to change screens when workout is clicked
    var pageBool by mutableStateOf(false)
    var workoutName by mutableStateOf("")
    fun setTrue() {
        pageBool = true
    }

    fun changeWorkout(workout: String) {
        workoutName = workout
    }
}

