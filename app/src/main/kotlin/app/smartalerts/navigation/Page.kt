package app.smartalerts.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import app.smartalerts.R.string.contacts
import app.smartalerts.R.string.events
import app.smartalerts.navigation.page.ContactsPage
import app.smartalerts.navigation.page.EventsPage
import compose.icons.TablerIcons
import compose.icons.tablericons.Calendar
import compose.icons.tablericons.UserExclamation

enum class Page(
  val route: String,
  @StringRes val pageTitle: Int,
  val icon: ImageVector,
  val content: @Composable () -> Unit,
) {

  Contacts("contacts", contacts, TablerIcons.UserExclamation, { ContactsPage() }),
  Events("events", events, TablerIcons.Calendar, { EventsPage() });

  @Composable
  fun pageTitle(): String = stringResource(pageTitle)
}
