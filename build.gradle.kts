// Top-level build file
plugins {
    // 👇 Reverted to 8.7.2 to match your Android Studio version
    id("com.android.application") version "8.7.2" apply false
    id("com.android.library") version "8.7.2" apply false

    // Keep your Kotlin version (assuming 1.9.x or 2.0)
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    id("com.google.gms.google-services") version "4.4.2" apply false
}