import org.gradle.api.JavaVersion.VERSION_1_8
import java.util.Properties

plugins {
  id("com.android.application")
  kotlin("android")
  id("io.gitlab.arturbosch.detekt").version("1.22.0")
}

android {
  namespace = "app.smartalerts"
  compileSdk = 33

  defaultConfig {
    applicationId = "app.smartalerts"
    targetSdk = 33
    minSdk = 21
    versionCode = 100
    versionName = "1.0.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  signingConfigs {
    val keystore = rootProject.file("keystore")
    val keystoreSecret = rootProject.file("keystore.secret")

    if(!keystore.exists() && keystoreSecret.exists()) {
      logger.warn("Impossible to create signing configuration with files encrypted")
      return@signingConfigs
    }
    val keystoreProperties = Properties().apply {
      load(rootProject.file("keystore.properties").inputStream())
    }

    create("signed") {
      storeFile = keystore
      storePassword = keystoreProperties.getProperty("KEYSTORE_PASSWORD")
      keyAlias = keystoreProperties.getProperty("SIGNING_KEY_ALIAS")
      keyPassword = keystoreProperties.getProperty("SIGNING_KEY_PASSWORD")
    }
  }

  buildTypes {
    named("release") {
      isMinifyEnabled = true
    }

    named("debug") {
      applicationIdSuffix = ".debug"
      isPseudoLocalesEnabled = true
      isDebuggable = true
    }

    if(signingConfigs.findByName("signed") == null) return@buildTypes
    create("signedRelease") {
      isMinifyEnabled = true
      signingConfig = signingConfigs.getByName("signed")
    }
  }

  compileOptions {
    sourceCompatibility = VERSION_1_8
    targetCompatibility = VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }

  testOptions {
    unitTests.all { it.useJUnitPlatform() }
  }

  buildFeatures {
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.compose.get()
  }
}

dependencies {
  // Compose
  implementation(platform(libs.compose.bom))
  implementation(libs.bundles.compose)
}

detekt {
  buildUponDefaultConfig = true
}
