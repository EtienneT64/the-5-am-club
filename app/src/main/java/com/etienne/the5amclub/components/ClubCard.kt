package com.etienne.the5amclub.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.etienne.the5amclub.BottomBarScreen.Home.title
import com.etienne.the5amclub.R
import com.etienne.the5amclub.screens.HomeScreen

@Composable
fun ClubCard(
    onClick: () -> Unit,
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),


    ){
        Column{
            Image(
                painter = painterResource(id = R.drawable.running),
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
                .padding(top = 12.dp, bottom = 12.dp,start = 8.dp, end = 8.dp)
        ){
            Text(
                text = "Running",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start),
            style = MaterialTheme.typography.headlineSmall

            )

        }
    }
}

@Composable
@Preview
fun ClubCardPreview() {
    ClubCard(onClick = {})
}
