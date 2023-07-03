package com.etienne.the5amclub.screens


import android.icu.util.Calendar
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.etienne.the5amclub.UserViewModel
import com.etienne.the5amclub.ui.CircularProgressBar
import com.etienne.the5amclub.ui.theme.AppTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.time.LocalDate
import java.time.LocalDateTime

data class DBEvent(
    val EventName: String? = null,
    val ClubDescrip: String? = null,
    val Location: String? = null,
    val Cost: String? = null,
    val StartTimes: Map<String, String>? = null,
    val When: String? = null
)


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun HomeScreen() {
    val sharedEvents = viewModel<EventsViewModel>()
    val sharedUser = viewModel<UserViewModel>()

    val hasLoaded by sharedUser.loading.collectAsState()
    var loaded by remember {
        mutableStateOf(false)
    }

    loaded = hasLoaded


    AppTheme {
        Surface(modifier = Modifier.padding(bottom = 56.dp)) {
            if (loaded) {
                var clubBool by remember {
                    mutableStateOf(true)
                }

                if (clubBool) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
//            .background(brush2),
                    ) {
                        LazyColumn {
                            item {
                                Box(
                                    //horizontalAlignment = Alignment.CenterHorizontally,
                                    //verticalArrangement = Arrangement.Top,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        var quote by remember {
                                            mutableStateOf("")
                                        }

                                        val weekOfYear = Calendar.WEEK_OF_YEAR
                                        val quotes = sharedUser.Quotes.value
                                        quote = quotes[weekOfYear]


                                        androidx.compose.material.Text(
                                            text = quote,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.weight(3f)
                                        )




                                        Image(
                                            painter = painterResource(id = R.drawable.logo),
                                            contentDescription = "null",
                                            alignment = Alignment.TopEnd,

                                            // crop the image if it's not a square
                                            modifier = Modifier

                                                .size(90.dp) // clip to the circle shape
                                                .border(5.dp, Color.Gray)
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
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        fontSize = 50.sp,
                                        textAlign = TextAlign.Center,
                                        text = "Calendar",
                                        fontWeight = FontWeight.Bold,
                                    )

                                }
                            }
                            //This is the calendar
                            item {
                                Column(
                                    modifier = Modifier
                                        .fillParentMaxSize()
                                        .background(MaterialTheme.colorScheme.secondary)
                                ) {
                                    val events = sharedUser.viewModelUser.value.Events

                                    Schedule(events = events)
                                }
                            }
                            item {
                                Row(
                                    horizontalArrangement = Arrangement.Center,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        fontSize = 50.sp,
                                        textAlign = TextAlign.Center,
                                        text = "Clubs",
                                        fontWeight = FontWeight.Bold,
                                    )

                                }
                            }
                            item {
                                Card(shape = MaterialTheme.shapes.small,
                                    modifier = Modifier
                                        .padding(
                                            bottom = 6.dp,
                                            top = 6.dp,
                                        )
                                        .fillMaxWidth()
                                        .clickable {
                                            sharedEvents.changeEvent("RunningClub")
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
                                Card(shape = MaterialTheme.shapes.small,
                                    modifier = Modifier
                                        .padding(
                                            bottom = 6.dp,
                                            top = 6.dp,
                                        )
                                        .fillMaxWidth()
                                        .clickable {
                                            sharedEvents.changeEvent("WalkingClub")
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
                                Card(shape = MaterialTheme.shapes.small,
                                    modifier = Modifier
                                        .padding(
                                            bottom = 6.dp,
                                            top = 6.dp,
                                        )
                                        .fillMaxWidth()
                                        .clickable {
                                            sharedEvents.changeEvent("HikingClub")
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
                                Card(shape = MaterialTheme.shapes.small,
                                    modifier = Modifier
                                        .padding(
                                            bottom = 6.dp,
                                            top = 6.dp,
                                        )
                                        .fillMaxWidth()
                                        .clickable {
                                            sharedEvents.changeEvent("Success Club")
                                            clubBool = false
                                        }


                                ) {
                                    Column {
                                        Image(
                                            painter = painterResource(id = R.drawable.successclub),
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
                                Card(shape = MaterialTheme.shapes.small,
                                    modifier = Modifier
                                        .padding(
                                            bottom = 6.dp,
                                            top = 6.dp,
                                        )
                                        .fillMaxWidth()
                                        .clickable {
                                            sharedEvents.changeEvent("FivesFootballClub")
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
                                Card(shape = MaterialTheme.shapes.small,
                                    modifier = Modifier
                                        .padding(
                                            bottom = 6.dp,
                                            top = 6.dp,
                                        )
                                        .fillMaxWidth()
                                        .clickable {
                                            sharedEvents.changeEvent("PadelClub")
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
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    CircularProgressBar(true)
                }
            }
        }
    }
//    val brush2 = Brush.linearGradient(listOf(Color.DarkGray, Color.Black))


}


@Composable
fun ClubBlock() {
    val sharedEvents = viewModel<EventsViewModel>()
    val sharedUser = viewModel<UserViewModel>()

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
                    val eventName = sharedEvents.eventName

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
                            trimName, "drawable", context.packageName
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

                    eventRef.child(eventName).addValueEventListener(object : ValueEventListener {
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

                    Button(modifier = Modifier.size(width = 200.dp, height = 50.dp), onClick = {
                        subscribeToEvent(
                            sharedEvents.eventName, times, sharedUser.viewModelUser.value.userID!!
                        )
                    }) {
                        Text(
                            text = "Subscribe", fontSize = 25.sp
                        )
                    }
                }
            }
        }

    }


}

fun subscribeToEvent(
    eventName: String, startTimes: Map<String, String>, userID: String
) {//This is the function that adds events to the user realtime database
    val userRef =
        Firebase.database("https://the5amclub-dfb7f-default-rtdb.europe-west1.firebasedatabase.app")
            .getReference("users")

    userRef.child(userID).child("Attending").setValue(mapOf(eventName to "True"))


    if (eventName == "SuccessClub") {
        val obvious = true
    } else {
        var time = LocalDateTime.now()
        time = time.withHour(0)
        time = time.withMinute(0)
        time = time.withSecond(0)
        time = time.withNano(0)


        for (stringTime: String in startTimes.values) {
            //Calculates the amt days between the start time weekday and the next correct weekday
            val daysUntilStartDate =
                ((getDateFromString(stringTime) - LocalDate.now().dayOfWeek.value + 7) % 7)
            time = time.plusDays(daysUntilStartDate.toLong())

            var stringManip = stringTime.drop(stringTime.indexOf(" ") + 3)
            stringManip = stringManip.dropLast(stringManip.length - stringManip.indexOf(" "))
            val hours = stringManip.dropLast(3).toLong()
            val minutes = stringManip.drop(3).toLong()
            time = time.plusHours(hours)
            time = time.plusMinutes(minutes)

            var startTime = time.toString()
            var endTime = time.plusHours(2).toString()

            //First event calculated with the right day
            var event = Event(
                eventName, 0xFFAFBBF2, startTime, endTime
            )
            userRef.child(userID).child("Events").child(startTime).setValue(event)

            for (i in 1..51) {
                time = time.plusDays(7)
                startTime = time.toString()
                endTime = time.plusHours(2).toString()

                //Subsequent events every week for a year
                event = Event(
                    eventName, 0xFFAFBBF2, startTime, endTime
                )
                userRef.child(userID).child("Events").child(startTime).setValue(event)
            }

            //Resets the time back to current day after first start time has been added
            time = LocalDateTime.now()
            time = time.withHour(0)
            time = time.withMinute(0)
            time = time.withSecond(0)
            time = time.withNano(0)
        }
    }


    //TODO Hard Code Success club

    //userRef.child(userID).child("Events").child(startTime).setValue(event)
}

fun getDateFromString(startTime: String): Int {
    val str = startTime.dropLast(startTime.length - startTime.indexOf(" "))
    return when (str) {
        "Monday" -> 1
        "Tuesday" -> 2
        "Wednesday" -> 3
        "Thursday" -> 4
        "Friday" -> 5
        "Saturday" -> 6
        "Sunday" -> 7
        else -> {
            Log.d("DateCalculation", "Start Time went wrong")
            1
        }
    }
}



class EventsViewModel : ViewModel() {
    var pageBool by mutableStateOf(false)
    var eventName by mutableStateOf("")

    fun setTrue() {
        pageBool = true
    }

    fun changeEvent(event: String) {
        eventName = event
    }

}

@Composable
fun Rower() {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        Text(
            fontSize = 50.sp,
            textAlign = TextAlign.Center,
            text = "Calendar",
            fontWeight = FontWeight.Bold,
        )

    }
}

@Composable
@Preview
fun RowerPreview() {
    Rower()
}
