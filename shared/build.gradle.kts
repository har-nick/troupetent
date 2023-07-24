import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.i18n4k)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.sqldelight)
}

i18n4k {
    sourceCodeLocales = listOf("en_gb", "en_us", "fr", "de")
}

@OptIn(ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    @Suppress("UnusedPrivateProperty")
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.bundles.compose.multiplatform)
                implementation(libs.bundles.ktor)
                implementation(libs.bundles.voyager)

                implementation(libs.bandkit)
                implementation(libs.coroutines.core)
                implementation(libs.i18n4k)
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.kotlinx.collections.immutable)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.sqldelight.coroutines)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.coroutines.android)
                implementation(libs.koin.android)
                implementation(libs.sqldelight.driver.android)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.coroutines.jvm)
                implementation(libs.coroutines.swing)
                implementation(libs.sqldelight.driver.jvm)
            }
        }
    }
}

android {
    namespace = "uk.co.harnick.troupetent"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
    }
    packaging.resources.excludes += "/META-INF/{AL2.0,LGPL2.1,DEPENDENCIES}"
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

compose {
    kotlinCompilerPlugin.set("org.jetbrains.compose.compiler:compiler:1.5.0")
    desktop.application.mainClass = "MainKt"
}

sqldelight {
    databases {
        create("LocalStorage") {
            packageName.set("uk.co.harnick.troupetent")
        }
    }
}
