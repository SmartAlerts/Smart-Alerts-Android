package app.smartalerts

import android.app.Application

class SmartAlertsApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    println("On Create")
  }
}
