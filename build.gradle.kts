buildscript {
    dependencies {
        classpath(libs.kotlin.gradle.plugin)
    }
}

plugins {
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.com.android.application) apply(false)
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.org.jetbrains.kotlin.android) apply(false)
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.com.google.hilt.android) apply(false)
    @Suppress("DSL_SCOPE_VIOLATION")
    alias(libs.plugins.secrets.gradle.plugin) apply(false)
}
