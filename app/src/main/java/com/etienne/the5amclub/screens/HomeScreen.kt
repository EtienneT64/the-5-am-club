package com.etienne.the5amclub.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.etienne.the5amclub.R
import com.etienne.the5amclub.ui.theme.AppTheme


@Composable
fun HomeScreen() {
    AppTheme {
        Surface(modifier = Modifier.padding(bottom = 56.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
//            .background(brush2),
            ) {
                Row {
                    Text(
                        fontSize = 50.sp,
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


                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.run),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .size(225.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
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


                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.walking),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .size(225.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
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


                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.hiking),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .size(225.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
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


                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.success),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .size(225.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
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


                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.fives),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .size(225.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
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


                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.padel),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .size(225.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
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
        }
    }
//    val brush2 = Brush.linearGradient(listOf(Color.DarkGray, Color.Black))


}


@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}