plugins {
    alias(libs.plugins.jetbrains.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting
        val androidMain by getting
        val androidUnitTest by getting
    }
}

dependencies {
    add("kspCommonMainMetadata", project(":shared"))
}

android {
    namespace = "com.harnick.troupetent"
    compileSdk = 33
    defaultConfig {
        minSdk = 23
    }
}