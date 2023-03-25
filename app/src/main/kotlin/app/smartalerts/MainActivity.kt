package app.smartalerts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import app.smartalerts.navigation.AppBottomNavigation
import app.smartalerts.navigation.AppNavHost

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      val navController = rememberNavController()

      Scaffold(
        bottomBar = { AppBottomNavigation(navController) },
        content = { AppNavHost(navController, Modifier.padding(it)) },
      )
    }
  }
}
