package com.etienne.the5amclub.bottomnavbar

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.etienne.the5amclub.BottomBarScreen
import com.etienne.the5amclub.screens.ProfileScreen
import com.etienne.the5amclub.screens.HomeScreen
import com.etienne.the5amclub.screens.UtilitiesScreen
import com.etienne.the5amclub.screens.WorkoutsScreen
import com.google.firebase.auth.ktx.userProfileChangeRequest


@Composable

fun BottomNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ){
        composable(route = BottomBarScreen.Home.route){
            HomeScreen()
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen()
        }
        composable(route = BottomBarScreen.Workouts.route){
            WorkoutsScreen()
        }
        composable(route = BottomBarScreen.Utilities.route){
            UtilitiesScreen()
        }
    }
}