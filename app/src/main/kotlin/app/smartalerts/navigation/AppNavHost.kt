package app.smartalerts.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavHost(controller: NavHostController, modifier: Modifier) {
  NavHost(controller, startDestination = Page.Contacts.route, modifier) {

    Page.values().forEach { page ->
      composable(page.route) { page.content() }
    }
  }
}
