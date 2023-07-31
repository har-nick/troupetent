import org.jetbrains.compose.desktop.application.dsl.TargetFormat.AppImage
import org.jetbrains.compose.desktop.application.dsl.TargetFormat.Exe
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.sqldelight)
//    alias(libs.plugins.moko.resources.generator)
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
                implementation(libs.bundles.moko)
                implementation(libs.bundles.voyager)

                implementation(libs.bandkit)
                implementation(libs.coroutines.core)
                implementation(libs.kamel)
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.kotlinx.collections.immutable)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.serialization.json)
//                api(libs.moko.resources)
                implementation(libs.sqldelight.coroutines)
                implementation(libs.windowsize)
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
    defaultConfig.minSdk = 21
    packaging.resources.excludes += "/META-INF/{AL2.0,LGPL2.1,DEPENDENCIES,MANIFEST,NOTICE,LICENSE}"
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

compose {
    desktop.application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(AppImage, Exe)
        }
    }
}

sqldelight {
    databases {
        create("LocalStorage") {
            packageName.set("uk.co.harnick.troupetent")
        }
    }
}

//multiplatformResources {
//    multiplatformResourcesPackage = "uk.co.harnick.troupetent.resources"
//    multiplatformResourcesClassName = "MokoRes"
//}
