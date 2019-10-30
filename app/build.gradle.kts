import org.gradle.kotlin.dsl.kotlin

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.gms.google-services")
}

android {

    compileSdkVersion(29)

    defaultConfig {
        applicationId = "com.magarex.memelord"
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        getByName("debug") {
            storeFile = rootProject.file("debug.keystore")
            keyAlias = "androiddebugkey"
            keyPassword = "android"
            storePassword = "android"
        }
        create("release") {
            storeFile = rootProject.file("keys/ReleaseKey.jks")
            keyAlias = System.getenv("GenKeyAlias")
            keyPassword = System.getenv("GenKeyPassword")
            storePassword = System.getenv("GenKeystorePassword")
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
//            applicationIdSuffix = ".debug"
//            versionNameSuffix = "-debug"
        }
        getByName("release") {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    dataBinding.isEnabled = true
    android.defaultConfig.vectorDrawables.useSupportLibrary = true
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // AppCompat
    implementation(Jetpack.APPCOMPAT)

    // Firebase
    implementation(Firebase.CORE)
    implementation(Firebase.CRASHLYTICS)
    implementation(Firebase.REMOTE_CONFIG)
    implementation(Firebase.STORAGE)
    implementation(Firebase.DATABSE)
    implementation(Firebase.FCM)
    implementation(Firebase.AUTH)
    implementation(Firebase.JOB_DISPATCHER)

    // FirebaseUI for Firebase Auth
    implementation(FirebaseUI.AUTH)
    implementation(FirebaseUI.STORAGE)
    implementation(FirebaseUI.FACEBOOK_LOGIN)
    implementation(FirebaseUI.TWITTER_LOGIN) {
        isTransitive = true
    }

    // FirebaseUI for Cloud Storage
    implementation("androidx.room:room-rxjava2:2.2.0")

    // Photo Editor
    implementation(PhotoEditor.EDITOR)

    // Material Component
    implementation(Material.MATERIAL)

    // Constraint Layout
    implementation(Jetpack.CONSTRAINT_LAYOUT)

    // Retrofit + ( Converters and Adapters )
    implementation(Retrofit.RETROFIT)
    implementation(Retrofit.CONVERTER_MOSHI)
    implementation(Retrofit.RX_JAVA_ADAPTER)

    // Easy Permissions
    implementation(EasyPermission.EASY_PERMISSION)

    // Moshi
    implementation(Moshi.MOSHI)

    // Dagger
    implementation(Dagger.DAGGER)
    implementation(Dagger.ANDROID_SUPPORT)
    kapt(Dagger.COMPILER)
    kapt(Dagger.ANDROID_PROCESSOR)

    // Rx-Android
    implementation(Rx.ANDROID)
    implementation(Rx.JAVA)

    // Lifecycle
    implementation(Lifecycle.EXTENSIONS)

    // Glide
    implementation(Glide.GLIDE)
    kapt(Glide.PROCESSOR)

    // CircleImageView
    implementation(CircleImageView.VIEW)

    // Zoomy
    implementation(Zoomy.ZOOMY)

    // ShimmerView
    implementation(ShimmerRecyclerView.VIEW)
}
