package com.etienne.the5amclub

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Profile : BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Workouts : BottomBarScreen(
        route = "workouts",
        title = "Workouts",
        icon = Icons.Default.List
    )

    object Utilities : BottomBarScreen(
        route = "utilities",
        title = "Utilities",
        icon = Icons.Default.CheckCircle
    )
}
