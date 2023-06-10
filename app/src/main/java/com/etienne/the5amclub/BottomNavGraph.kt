package com.etienne.the5amclub

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.etienne.the5amclub.screens.HomeScreen
import com.etienne.the5amclub.screens.ProfileScreen
import com.etienne.the5amclub.screens.UtilitiesScreen
import com.etienne.the5amclub.screens.WorkoutsScreen
import com.etienne.the5amclub.ui.theme.AppTheme


@Composable
fun BottomNavGraph(navController: NavHostController) {
    AppTheme {
        Surface {
            NavHost(
                navController = navController,
                startDestination = BottomBarScreen.Home.route
            ) {
                composable(route = BottomBarScreen.Home.route) {
                    HomeScreen()
                }
                composable(route = BottomBarScreen.Profile.route) {
                    ProfileScreen()
                }
                composable(route = BottomBarScreen.Workouts.route) {
                    WorkoutsScreen(true)
                }
                composable(route = BottomBarScreen.Utilities.route) {
                    UtilitiesScreen()
                }
            }
        }
    }

}