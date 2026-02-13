plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // If you use the new compose plugin, keep it. If not, use 'kotlin-kapt' or similar.
    // For now, let's stick to the IDs you had that worked:
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.duddletech.convoaiadmin"
    // 👇 STABLE SDK VERSION
    compileSdk = 35

    defaultConfig {
        applicationId = "com.duddletech.convoaiadmin"
        minSdk = 24
        // 👇 STABLE TARGET
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11 // Revert to 11 for stability
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
}

dependencies {
    // 👇 USING STABLE VERSIONS COMPATIBLE WITH API 34
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.1")
    implementation("androidx.activity:activity-compose:1.9.0")

    // Stable Compose BOM
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.1.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.06.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    // 👇 ESSENTIAL FOR XML LAYOUTS (Fixes 'layout_constraintTop_toTopOf' errors)
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // 👇 ESSENTIAL FOR CARDS (Fixes 'cardBackgroundColor' & 'strokeWidth' errors)
    implementation("com.google.android.material:material:1.12.0")
}