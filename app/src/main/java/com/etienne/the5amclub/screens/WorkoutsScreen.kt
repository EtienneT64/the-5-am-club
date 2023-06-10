package com.etienne.the5amclub.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.etienne.the5amclub.ui.theme.AppTheme


@Composable
fun WorkoutsScreen() {
    AppTheme {
        Surface {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Workouts",
                    fontWeight = FontWeight.Bold,
                )
            }
        }

    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, name = "Dark Mode"
)
@Composable
fun WorkoutsScreenPreview() {
    AppTheme {
        Surface {
            WorkoutsScreen()
        }
    }

}