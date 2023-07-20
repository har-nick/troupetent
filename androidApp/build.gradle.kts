plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.versioning)
}

android {
    namespace = "uk.co.harnick.troupetent.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "uk.co.harnick.troupetent"
        minSdk = 21
        targetSdk = 33
    }
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = "1.4.8"
    packaging.resources.excludes += "/META-INF/{AL2.0,LGPL2.1,DEPENDENCIES}"
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions.jvmTarget = "17"
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(libs.koin.android)
}
