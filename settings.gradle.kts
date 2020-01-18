@file:Suppress("UnstableApiUsage")

// Use 'gradle install' to install latest plugin version
pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}
rootProject.buildFileName = "build.gradle.kts"
include(":app", ":data", ":cache", ":remote", ":domain")
