package com.etienne.the5amclub.screens


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.etienne.the5amclub.R
import com.etienne.the5amclub.ui.theme.AppTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

data class DBEvent(
    val EventName: String? = null,
    val ClubDescrip: String? = null,
    val Location: String? = null,
    val Cost: String? = null,
    val StartTimes: Map<String, String>? = null,
    val When: String? = null
)


@Composable
fun HomeScreen() {
    val viewModel = viewModel<EventsViewModel>()

    AppTheme {
        Surface(modifier = Modifier.padding(bottom = 56.dp)) {

            var clubBool by remember {
                mutableStateOf(true)
            }


            if (clubBool) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
//            .background(brush2),
                ) {
                    Row {
                        Text(
                            fontSize = 35.sp,
                            textAlign = TextAlign.Center,
                            text = "Clubs",
                            fontWeight = FontWeight.Bold,
//                color = Color.White
                        )

                    }
                    LazyColumn {
                        item {
                            Card(
                                shape = MaterialTheme.shapes.small, modifier = Modifier
                                    .padding(
                                        bottom = 6.dp,
                                        top = 6.dp,
                                    )
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.changeEvent("RunningClub")
                                        clubBool = false
                                    }


                            ) {
                                Column {
                                    Image(
                                        painter = painterResource(id = R.drawable.runningclub),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .size(275.dp),
                                        contentScale = ContentScale.FillBounds
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 12.dp,
                                            bottom = 12.dp,
                                            start = 8.dp,
                                            end = 8.dp
                                        )
                                ) {
                                    Text(
                                        text = "Running Club",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentWidth(Alignment.Start),
                                        style = MaterialTheme.typography.headlineSmall

                                    )

                                }
                            }
                        }
                        item {
                            Card(
                                shape = MaterialTheme.shapes.small, modifier = Modifier
                                    .padding(
                                        bottom = 6.dp,
                                        top = 6.dp,
                                    )
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.changeEvent("WalkingClub")
                                        clubBool = false
                                    }


                            ) {
                                Column {
                                    Image(
                                        painter = painterResource(id = R.drawable.walkingclub),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .size(275.dp),
                                        contentScale = ContentScale.FillBounds
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 12.dp,
                                            bottom = 12.dp,
                                            start = 8.dp,
                                            end = 8.dp
                                        )
                                ) {
                                    Text(
                                        text = "Walking Club",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentWidth(Alignment.Start),
                                        style = MaterialTheme.typography.headlineSmall

                                    )

                                }
                            }
                        }
                        item {
                            Card(
                                shape = MaterialTheme.shapes.small, modifier = Modifier
                                    .padding(
                                        bottom = 6.dp,
                                        top = 6.dp,
                                    )
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.changeEvent("HikingClub")
                                        clubBool = false
                                    }


                            ) {
                                Column {
                                    Image(
                                        painter = painterResource(id = R.drawable.hikingclub),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .size(275.dp),
                                        contentScale = ContentScale.FillBounds
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 12.dp,
                                            bottom = 12.dp,
                                            start = 8.dp,
                                            end = 8.dp
                                        )
                                ) {
                                    Text(
                                        text = "Hiking Club",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentWidth(Alignment.Start),
                                        style = MaterialTheme.typography.headlineSmall

                                    )

                                }
                            }
                        }
                        item {
                            Card(
                                shape = MaterialTheme.shapes.small, modifier = Modifier
                                    .padding(
                                        bottom = 6.dp,
                                        top = 6.dp,
                                    )
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.changeEvent("Success Club")
                                        clubBool = false
                                    }


                            ) {
                                Column {
                                    Image(
                                        painter = painterResource(id = R.drawable.padelclub),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .size(275.dp),
                                        contentScale = ContentScale.FillBounds
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 12.dp,
                                            bottom = 12.dp,
                                            start = 8.dp,
                                            end = 8.dp
                                        )
                                ) {
                                    Text(
                                        text = "Success Club",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentWidth(Alignment.Start),
                                        style = MaterialTheme.typography.headlineSmall

                                    )

                                }
                            }
                        }
                        item {
                            Card(
                                shape = MaterialTheme.shapes.small, modifier = Modifier
                                    .padding(
                                        bottom = 6.dp,
                                        top = 6.dp,
                                    )
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.changeEvent("FivesFootballClub")
                                        clubBool = false
                                    }


                            ) {
                                Column {
                                    Image(
                                        painter = painterResource(id = R.drawable.fivesfootballclub),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .size(275.dp),
                                        contentScale = ContentScale.FillBounds
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 12.dp,
                                            bottom = 12.dp,
                                            start = 8.dp,
                                            end = 8.dp
                                        )
                                ) {
                                    Text(
                                        text = "Fives Football Club",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentWidth(Alignment.Start),
                                        style = MaterialTheme.typography.headlineSmall

                                    )

                                }
                            }
                        }
                        item {
                            Card(
                                shape = MaterialTheme.shapes.small, modifier = Modifier
                                    .padding(
                                        bottom = 6.dp,
                                        top = 6.dp,
                                    )
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.changeEvent("PadelClub")
                                        clubBool = false
                                    }


                            ) {
                                Column {
                                    Image(
                                        painter = painterResource(id = R.drawable.padelclub),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .size(275.dp),
                                        contentScale = ContentScale.FillBounds
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 12.dp,
                                            bottom = 12.dp,
                                            start = 8.dp,
                                            end = 8.dp
                                        )
                                ) {
                                    Text(
                                        text = "Padel Club",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentWidth(Alignment.Start),
                                        style = MaterialTheme.typography.headlineSmall

                                    )

                                }
                            }
                        }

                    }
                }
            } else {
                ClubBlock()
            }
        }
    }
//    val brush2 = Brush.linearGradient(listOf(Color.DarkGray, Color.Black))


}


@Composable
fun ClubBlock() {
    val viewModel = viewModel<EventsViewModel>()

    val img = "HikingClub"



    AppTheme {
        Surface {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(25.dp)
                //.verticalScroll(rememberScrollState())
            ) {
                item {
                    val eventName = viewModel.eventName

                    var name by remember {
                        mutableStateOf("")
                    }

                    var descrip by remember {
                        mutableStateOf("")
                    }

                    var location by remember {
                        mutableStateOf("")
                    }

                    var cost by remember {
                        mutableStateOf("")
                    }

                    var times by remember {
                        mutableStateOf(mapOf("" to ""))
                    }

                    var whentime by remember {
                        mutableStateOf("")
                    }

                    var trimName = eventName.lowercase()
                    trimName = trimName.replace(" ", "")

                    val context = LocalContext.current
                    val drawableId = remember(trimName) {
                        context.resources.getIdentifier(
                            trimName,
                            "drawable",
                            context.packageName
                        )
                    }

                    Image(
                        painter = painterResource(id = drawableId),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(320.dp),
                        contentScale = ContentScale.FillBounds
                    )


                    val eventRef =
                        Firebase.database("https://the5amclub-dfb7f-default-rtdb.europe-west1.firebasedatabase.app/")
                            .getReference("Events")

                    eventRef.child(eventName)
                        .addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if (snapshot.exists()) {
                                    val tempEvent = snapshot.getValue(DBEvent::class.java)!!

                                    descrip = tempEvent.ClubDescrip.toString()
                                    cost = tempEvent.Cost.toString()
                                    name = tempEvent.EventName.toString()
                                    location = tempEvent.Location.toString()
                                    if (name == "Success Club") {
                                        whentime = tempEvent.When.toString()
                                    } else {
                                        times = tempEvent.StartTimes!!
                                    }
                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                Log.d("EventDB", "Event details could not be called")
                            }
                        })


                    //Text(text = "Club Name:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "$name", fontSize = 40.sp, fontWeight = FontWeight.Bold)
                    //Text(text = "Description:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "$descrip")
                    Text(text = "Location:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "$location")

                    if (name == "Success Club") {
                        Text(text = "When:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(text = "$whentime")
                    } else {

                        Text(text = "Start Times:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        //TODO
                    }


                    Text(text = "Cost:", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "$cost")


                    /* val sortedSteps = TreeMap(steps)
                sortedSteps.forEach { step ->
                    Text(text = "${step.key}: ${step.value}")
                }*/

                    Button(
                        modifier = Modifier.size(width = 200.dp, height = 50.dp),
                        onClick = {}
                    ) {
                        Text(
                            text = "Subscribe",
                            fontSize = 25.sp
                        )
                    }
                }
            }
        }

    }
}


@Composable
@Preview
fun ClubPreview() {
    ClubBlock()
}

/*
@Composable
@Preview
fun HomeScreenPreview() {
    //HomeScreen()
}*/

class EventsViewModel : ViewModel() {
    var pageBool by mutableStateOf(false)
    var eventName by mutableStateOf("")

    fun setTrue() {
        pageBool = true
    }

    fun changeEvent(workout: String) {
        eventName = workout
    }

}
