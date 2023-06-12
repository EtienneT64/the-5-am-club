package com.etienne.the5amclub

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.etienne.the5amclub.ui.theme.AppTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    AppTheme {
        Surface {
            val navController = rememberNavController()
            Scaffold(bottomBar = { BottomBar(navController = navController) }) {
                BottomNavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    AppTheme {
        Surface {
            val screens = listOf(
                BottomBarScreen.Profile,
                BottomBarScreen.Home,
                BottomBarScreen.Workouts,
                BottomBarScreen.Utilities,
            )
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            BottomNavigation(
                backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            ) {
                screens.forEach { screen ->
                    AddItem(
                        screen = screen,
                        currentDestination = currentDestination,
                        navController = navController
                    )
                }
            }
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen, currentDestination: NavDestination?, navController: NavHostController
) {
    AppTheme {
        Surface {
            BottomNavigationItem(
                modifier = Modifier.background(Color.Blue),
                selectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unselectedContentColor = Color.Black,
                label = {
                    Text(text = screen.title)
                },
                icon = {
                    Icon(
                        imageVector = screen.icon, contentDescription = "Navigation Icon"
                    )
                },
                selected = currentDestination?.hierarchy?.any {
                    it.route == screen.route
                } == true,
                onClick = {
                    navController.navigate(screen.route)
                },

                )
        }
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode"
)
@Composable
fun MainScreenPreview() {
    AppTheme {
        Surface {
            MainScreen()
        }
    }

}



