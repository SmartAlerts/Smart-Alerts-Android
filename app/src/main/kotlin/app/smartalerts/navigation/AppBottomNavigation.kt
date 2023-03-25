package app.smartalerts.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppBottomNavigation(navController: NavController) = BottomNavigation {
  Page.values().forEach { NavigationItem(navController, it) }
}

@Composable
private fun RowScope.NavigationItem(navController: NavController, page: Page) {
  val navBackstackEntry by navController.currentBackStackEntryAsState()
  val currentDestination = navBackstackEntry?.destination

  BottomNavigationItem(
    icon = { Icon(page.icon, page.pageTitle()) },
    label = { Text(page.pageTitle()) },
    selected = currentDestination?.hierarchy?.any { it.route == page.route } ?: false,
    onClick = { navController.navigate(page) },
  )
}

private fun NavController.navigate(page: Page) = navigate(page.route) {
  popUpTo(graph.findStartDestination().id) { saveState = true }
  launchSingleTop = true
  restoreState = true
}
