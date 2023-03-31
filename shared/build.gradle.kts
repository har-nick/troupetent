plugins {
    alias(libs.plugins.jetbrains.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.i18n4k)
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
        val commonMain by getting {
            dependencies {
                implementation(libs.bundles.common)
            }
        }
        val commonTest by getting
        val androidMain by getting {
            dependencies {
                implementation(libs.bundles.androidshared)
            }
        }
        val androidUnitTest by getting
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.kotlin.inject.ksp)
    add("kspAndroid", libs.kotlin.inject.ksp)
    add("kspAndroid", libs.compose.destinations.ksp)
}

sqldelight {

    databases {
        create("PersistentStorage") {
            packageName.set("com.harnick.troupetent.database")
        }
    }
}

i18n4k {
    sourceCodeLocales = listOf("en_gb")
}

android {
    namespace = "com.harnick.troupetent"
    compileSdk = 33
    defaultConfig {
        minSdk = 23
    }
}