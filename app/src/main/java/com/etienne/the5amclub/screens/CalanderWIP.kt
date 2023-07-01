package com.etienne.the5amclub.screens

//import androidx.compose.ui.tooling.data.EmptyGroup.name
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.ParentDataModifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.etienne.the5amclub.ui.theme.AppTheme
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt

data class Event(
    val name: String? = null,
    val color: Long? = null,
    val start: String? = null,//start used to be stored as LocalDateTime, but is now converted at the locations that need it
    val end: String? = null,//Same as start
    val description: String? = null,
)

val EventTimeFormatter = DateTimeFormatter.ofPattern("h:mm a")!!

@Composable
fun BasicEvent(
    event: Event,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(end = 2.dp, bottom = 2.dp)
            .background(Color(event.color!!), shape = RoundedCornerShape(4.dp))
            .padding(4.dp)
    ) {
        Text(
            text = "${LocalDateTime.parse(event.start).format(EventTimeFormatter)} - ${
                LocalDateTime.parse(event.end).format(
                    EventTimeFormatter
                )
            }",
            style = MaterialTheme.typography.caption,
        )

        Text(
            text = event.name!!,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
        )

        if (event.description != null) {
            Text(
                text = event.description,
                style = MaterialTheme.typography.body2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

val sampleEvents = mapOf(
    "1" to Event(
        name = "Google I/O Keynote",
        color = 0xFFAFBBF2,
        start = "2023-06-30T09:00:00",
        end = "2023-06-30T11:00:00",
    ),
    "2" to Event(
        name = "Developer Keynote",
        color = 0xFFAFBBF2,
        start = "2023-07-01T11:15:00",
        end = "2023-07-01T12:15:00",
    ),
    "3" to Event(
        name = "What's new in Android",
        color = 0xFF1B998B,
        start = "2021-05-18T12:30:00",
        end = "2021-05-18T15:00:00",
    ),
    "4" to Event(
        name = "What's new in Machine Learning",
        color = 0xFFF4BFDB,
        start = "2021-05-19T09:30:00",
        end = "2021-05-19T11:00:00",
    ),
    "5" to Event(
        name = "What's new in Material Design",
        color = 0xFF6DD3CE,
        start = "2021-05-19T11:00:00",
        end = "2021-05-19T12:15:00",
    ),
    "6" to Event(
        name = "Foo Compose Basics",
        color = 0xFF1B998B,
        start = "2021-05-20T12:00:00",
        end = "2021-05-20T13:00:00",
    ),
    "7" to Event(
        name = "Bar Compose Basics",
        color = 0xFF1B998B,
        start = "2021-05-21T12:00:00",
        end = "2021-05-21T13:00:00",
    ),
)

class EventsProvider : PreviewParameterProvider<Event> {
    private val eventList = sampleEvents.values
    override val values = eventList.asSequence()
}

@Preview(showBackground = true)
@Composable
fun EventPreview(
    @PreviewParameter(EventsProvider::class) event: Event,
) {
    AppTheme {
        BasicEvent(event, modifier = Modifier.sizeIn(maxHeight = 64.dp))
    }
}

private class EventDataModifier(
    val event: Event,
) : ParentDataModifier {
    override fun Density.modifyParentData(parentData: Any?) = event
}

private fun Modifier.eventData(event: Event) = this.then(EventDataModifier(event))

private val DayFormatter = DateTimeFormatter.ofPattern("EE, MMM d")

@Composable
fun BasicDayHeader(
    day: LocalDate,
    modifier: Modifier = Modifier,
) {
    Text(
        text = day.format(DayFormatter),
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun BasicDayHeaderPreview() {
    AppTheme {
        BasicDayHeader(day = LocalDate.now())
    }
}

@Composable
fun ScheduleHeader(
    minDate: LocalDate,
    maxDate: LocalDate,
    dayWidth: Dp,
    modifier: Modifier = Modifier,
    dayHeader: @Composable (day: LocalDate) -> Unit = { BasicDayHeader(day = it) },
) {
    Row(modifier = modifier) {
        val numDays = ChronoUnit.DAYS.between(minDate, maxDate).toInt() + 1
        repeat(numDays) { i ->
            Box(modifier = Modifier.width(dayWidth)) {
                dayHeader(minDate.plusDays(i.toLong()))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleHeaderPreview() {
    AppTheme {
        ScheduleHeader(
            minDate = LocalDate.now(),
            maxDate = LocalDate.now().plusDays(5),
            dayWidth = 256.dp,
        )
    }
}

private val HourFormatter = DateTimeFormatter.ofPattern("h a")

@Composable
fun BasicSidebarLabel(
    time: LocalTime,
    modifier: Modifier = Modifier,
) {
    Text(
        text = time.format(HourFormatter),
        modifier = modifier
            .fillMaxHeight()
            .padding(4.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun BasicSidebarLabelPreview() {
    AppTheme {
        BasicSidebarLabel(time = LocalTime.NOON, Modifier.sizeIn(maxHeight = 64.dp))
    }
}

@Composable
fun ScheduleSidebar(
    hourHeight: Dp,
    modifier: Modifier = Modifier,
    label: @Composable (time: LocalTime) -> Unit = { BasicSidebarLabel(time = it) },
) {
    Column(modifier = modifier) {
        var startTime = LocalTime.MIN
        startTime = startTime.plusHours(5)
        repeat(19) { i ->
            Box(modifier = Modifier.height(hourHeight)) {
                label(startTime.plusHours(i.toLong()))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleSidebarPreview() {
    AppTheme {
        ScheduleSidebar(hourHeight = 64.dp)
    }
}

@Composable
fun Schedule(
    events: Map<String, Event>?,
    modifier: Modifier = Modifier,
    eventContent: @Composable (event: Event) -> Unit = { BasicEvent(event = it) },
    dayHeader: @Composable (day: LocalDate) -> Unit = { BasicDayHeader(day = it) },
    minDate: LocalDate = LocalDate.now(),
    maxDate: LocalDate = LocalDate.now().plusDays(7),
) {
    val dayWidth = 130.dp
    val hourHeight = 35.dp
    val verticalScrollState = rememberScrollState()
    val horizontalScrollState = rememberScrollState()
    var sidebarWidth by remember { mutableIntStateOf(0) }

    Log.d("Schedule", "This code fires here")

    Column(modifier = modifier) {
        ScheduleHeader(
            minDate = minDate,
            maxDate = maxDate,
            dayWidth = dayWidth,
            dayHeader = dayHeader,
            modifier = Modifier
                .padding(start = with(LocalDensity.current) { sidebarWidth.toDp() })
                .horizontalScroll(horizontalScrollState)
        )
        Row(modifier = Modifier.weight(1f)) {
            ScheduleSidebar(
                hourHeight = hourHeight,
                modifier = Modifier
                    .verticalScroll(verticalScrollState)
                    .onGloballyPositioned { sidebarWidth = it.size.width }
            )
            BasicSchedule(
                events = events,
                eventContent = eventContent,
                minDate = minDate,
                maxDate = maxDate,
                dayWidth = dayWidth,
                hourHeight = hourHeight,
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(verticalScrollState)
                    .horizontalScroll(horizontalScrollState)
            )
        }
    }
}

@Composable
fun BasicSchedule(
    events: Map<String, Event>?,
    modifier: Modifier = Modifier,
    eventContent: @Composable (event: Event) -> Unit = { BasicEvent(event = it) },
    minDate: LocalDate = LocalDate.now(),
    maxDate: LocalDate = LocalDate.now().plusDays(6),
    dayWidth: Dp,
    hourHeight: Dp,
) {
    val numDays = ChronoUnit.DAYS.between(minDate, maxDate).toInt() + 1
    val dividerColor = if (MaterialTheme.colors.isLight) Color.LightGray else Color.DarkGray
    Layout(
        content = {
            val eventList = ArrayList(events!!.values)

            eventList.sortedBy(Event::start).forEach { event ->
                Box(modifier = Modifier.eventData(event)) {
                    eventContent(event)
                }
            }
        },
        modifier = modifier
            .drawBehind {
                repeat(19) {
                    drawLine(
                        dividerColor,
                        start = Offset(0f, (it + 1) * hourHeight.toPx()),
                        end = Offset(size.width, (it + 1) * hourHeight.toPx()),
                        strokeWidth = 1.dp.toPx()
                    )
                }
                repeat(numDays) {
                    drawLine(
                        dividerColor,
                        start = Offset((it + 1) * dayWidth.toPx(), 0f),
                        end = Offset((it + 1) * dayWidth.toPx(), size.height),
                        strokeWidth = 1.dp.toPx()
                    )
                }
            }
    ) { measureables, constraints ->
        val height = hourHeight.roundToPx() * 24
        val width = dayWidth.roundToPx() * numDays
        val placeablesWithEvents = measureables.map { measurable ->
            val event = measurable.parentData as Event
            val eventDurationMinutes = ChronoUnit.MINUTES.between(
                LocalDateTime.parse(event.start),
                LocalDateTime.parse(event.end)
            )
            val eventHeight = ((eventDurationMinutes / 60f) * hourHeight.toPx()).roundToInt()
            val placeable = measurable.measure(
                constraints.copy(
                    minWidth = dayWidth.roundToPx(),
                    maxWidth = dayWidth.roundToPx(),
                    minHeight = eventHeight,
                    maxHeight = eventHeight
                )
            )
            Pair(placeable, event)
        }
        layout(width, height) {
            placeablesWithEvents.forEach { (placeable, event) ->
                val eventOffsetMinutes =
                    (ChronoUnit.MINUTES.between(
                        LocalTime.MIN,
                        LocalDateTime.parse(event.start).toLocalTime()
                    ) - 300)
                val eventY = (((eventOffsetMinutes / 60f) * hourHeight.toPx()) - 0).roundToInt()
                val eventOffsetDays =
                    ChronoUnit.DAYS.between(minDate, LocalDateTime.parse(event.start).toLocalDate())
                        .toInt()
                val eventX = eventOffsetDays * dayWidth.roundToPx()
                placeable.place(eventX, eventY)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SchedulePreview() {
    AppTheme {
        Schedule(sampleEvents)
    }
}