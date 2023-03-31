plugins {
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.jetbrains.android).apply(false)
    alias(libs.plugins.jetbrains.multiplatform).apply(false)
    alias(libs.plugins.ksp)
    alias(libs.plugins.complete.kotlin)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.sqldelight).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
