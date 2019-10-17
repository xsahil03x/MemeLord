// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
        maven(url = "https://maven.fabric.io/public")

    }
    dependencies {
        classpath(Android.GRADLE_PLUGIN)
        classpath(Kotlin.GRADLE_PLUGIN)
        classpath(GoogleServices.SERVICES)
        classpath(Fabric.FABRIC)
        classpath(Navigation.SAFE_ARGS_GRADLE_PLUGIN)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven(url = "https://jitpack.io")
    }
}

task("clean", Delete::class) {
    delete = setOf(rootProject.buildDir)
}