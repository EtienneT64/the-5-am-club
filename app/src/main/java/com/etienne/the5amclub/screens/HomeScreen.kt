package com.etienne.the5amclub.screens



import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text


import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun HomeScreen() {
    val brush2 = Brush.linearGradient(listOf(Color.DarkGray, Color.Black))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(brush2),){
        Row() {
            Text(
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                text = "Clubs",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }

}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}