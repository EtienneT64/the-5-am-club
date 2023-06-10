package com.etienne.the5amclub.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.etienne.the5amclub.ui.theme.AppTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.TreeMap


data class Workout(val title: String)

val workoutsList = listOf(
    Workout("Press-ups"), Workout("Workout 2"), Workout("Workout 3")
)

data class DBWorkout(
    val WorkoutName: String? = null,
    val Reps: String? = null,
    val Benefits: String? = null,
    val Steps: Map<String, String>? = null
)


class WorkoutsActivity : AppCompatActivity() {
    private val viewModel: WorkoutsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pageBool = viewModel.pageBool

        setContent {
            AppTheme {
                Surface {
                    if (pageBool) {
                        WorkoutsScreen()
                    } else {
                        WorkoutInfo()
                    }
                }
            }

        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutsScreen() {
    AppTheme {
        Surface {
            Scaffold(topBar = {
                TopAppBar(title = { Text("Workouts") })
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
    }
}

@Composable
fun WorkoutBlock(workout: Workout) {
    AppTheme {
        Surface {
            val viewModel = viewModel<WorkoutsViewModel>()

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
                        viewModel.changeWorkout(workout.title.replace(" ", ""))
                        viewModel.setTrue()

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

@Composable
fun WorkoutInfo() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        val viewModel = viewModel<WorkoutsViewModel>()

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

        val workoutRef =
            Firebase.database("https://the5amclub-dfb7f-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference("Workouts")

        workoutRef.child(workoutName).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val tempWorkout = snapshot.getValue(DBWorkout::class.java)!!

                    name = tempWorkout.WorkoutName.toString()
                    benefits = tempWorkout.Benefits.toString()
                    reps = tempWorkout.Reps.toString()
                    steps = tempWorkout.Steps!!
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("WorkoutDB", "Workout details could not be called")
            }
        })

        Text(text = "Workout name: $name")
        Text(text = "Benefits: $benefits")
        Text(text = "Reps: $reps")

        val sortedSteps = TreeMap(steps)
        sortedSteps.forEach { step ->
            Text(text = "${step.key}: ${step.value}")
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
            WorkoutsScreen()
        }

    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode"
)
@Composable
fun WorkoutInfoPreview() {
    AppTheme {
        Surface {
            WorkoutInfo()
        }

    }
}

class WorkoutsViewModel : ViewModel() {
    var pageBool by mutableStateOf(false)
    var workoutName by mutableStateOf("")

    fun setTrue() {
        pageBool = true
    }

    fun changeWorkout(workout: String) {
        workoutName = workout
    }

}

