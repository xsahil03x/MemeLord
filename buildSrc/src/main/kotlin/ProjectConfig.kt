object Android {
    private const val GRADLE_PLUGIN_VERSION = "3.5.1"

    const val GRADLE_PLUGIN = "com.android.tools.build:gradle:$GRADLE_PLUGIN_VERSION"
}

object Kotlin {
    private const val VERSION = "1.3.50"
    private const val COROUTINES_VERSION = "1.2.2"
    private const val SERIALIZATION_VERSION = "0.11.1"

    const val GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:$VERSION"
    const val SERIALIZATION_PLUGIN = "org.jetbrains.kotlin:kotlin-serialization:$VERSION"
    const val STDLIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$VERSION"
    const val SERIALIZATION_RUNTIME = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:$SERIALIZATION_VERSION"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINES_VERSION"
}

object GoogleServices {
    private const val VERSION = "4.0.2"

    const val SERVICES = "com.google.gms:google-services:$VERSION"
}

object Fabric {
    private const val VERSION = "1.26.1"

    const val FABRIC = "io.fabric.tools:gradle:$VERSION"
}

object JUnit {
    private const val JUNIT_VERSION = "4.12"

    const val JUNIT = "junit:junit:$JUNIT_VERSION"
}

object AndroidXTest {
    private const val CORE_KTX_VERSION = "1.2.0"
    private const val RUNNER_VERSION = "1.2.0"
    private const val EXT_JUNIT_KTX_VERSION = "1.1.1"

    const val CORE_KTX = "androidx.test:core-ktx:$CORE_KTX_VERSION"
    const val RUNNER = "androidx.test:runner:$RUNNER_VERSION"
    const val EXT_JUNIT_KTX = "androidx.test.ext:junit-ktx:$EXT_JUNIT_KTX_VERSION"
}

object Espresso {
    private const val VERSION = "3.2.0"

    const val CORE = "androidx.test.espresso:espresso-core:$VERSION"
}

object Material {
    private const val VERSION = "1.1.0-alpha09"

    const val MATERIAL = "com.google.android.material:material:$VERSION"
}

object Jetpack {
    private const val ACTIVITY_KTX_VERSION = "1.1.0-alpha02"
    private const val APPCOMPAT_VERSION = "1.1.0-rc01"
    private const val CORE_KTX_VERSION = "1.2.0-alpha03"
    private const val FRAGMENT_KTX_VERSION = "1.2.0-alpha02"
    private const val PREFERENCE_KTX_VERSION = "1.1.0-rc01"
    private const val DRAWERLAYOUT_VERSION = "1.1.0-alpha02"
    private const val RECYCLERVIEW_VERSION = "1.1.0-beta02"
    private const val CONSTRAINT_LAYOUT_VERSION = "2.0.0-beta2"

    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:$ACTIVITY_KTX_VERSION"
    const val APPCOMPAT = "androidx.appcompat:appcompat:$APPCOMPAT_VERSION"
    const val CORE_KTX = "androidx.core:core-ktx:$CORE_KTX_VERSION"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:$FRAGMENT_KTX_VERSION"
    const val PREFERENCE_KTX = "androidx.preference:preference-ktx:$PREFERENCE_KTX_VERSION"
    const val DRAWERLAYOUT = "androidx.drawerlayout:drawerlayout:$DRAWERLAYOUT_VERSION"
    const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:$RECYCLERVIEW_VERSION"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:$CONSTRAINT_LAYOUT_VERSION"
}

object Lifecycle {
    private const val VERSION = "2.2.0-alpha03"

    const val EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:$VERSION"
    const val LIVEDATA_KTX = "androidx.lifecycle:lifecycle-livedata-ktx:$VERSION"
    const val VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:$VERSION"
    const val COMMON_JAVA8 = "androidx.lifecycle:lifecycle-common-java8:$VERSION"
}

object Navigation {
    private const val VERSION = "2.2.0-alpha01"

    const val FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:$VERSION"
    const val UI_KTX = "androidx.navigation:navigation-ui-ktx:$VERSION"
    const val SAFE_ARGS_GRADLE_PLUGIN = "androidx.navigation:navigation-safe-args-gradle-plugin:$VERSION"
}

object Glide {
    private const val VERSION = "4.8.0"

    const val GLIDE = "com.github.bumptech.glide:glide:$VERSION"
    const val PROCESSOR = "com.github.bumptech.glide:compiler:$VERSION"
}

object CircleImageView {
    private const val VERSION = "2.2.0"

    const val VIEW = "de.hdodenhof:circleimageview:$VERSION"
}

object Paging {
    private const val VERSION = "2.1.0"

    const val RUNTIME_KTX = "androidx.paging:paging-runtime-ktx:$VERSION"
}

object Zoomy {
    private const val VERSION = "1.1.0"

    const val ZOOMY = "com.ablanco.zoomy:zoomy:$VERSION"
}

object WorkManager {
    private const val VERSION = "2.2.0-rc01"

    const val RUNTIME_KTX = "androidx.work:work-runtime-ktx:$VERSION"
}

object PhotoEditor {
    private const val VERSION = "0.3.3"

    const val EDITOR = "ja.burhanrashid52:photoeditor:$VERSION"
}

object ShimmerRecyclerView {
    private const val VERSION = "v1.3"

    const val VIEW = "com.github.sharish:ShimmerRecyclerView:$VERSION"
}

object Firebase {
    private const val VERSION = "16.0.5"
    private const val REMOTE_CONFIG_VERSION = "16.1.0"
    private const val CRASHLYTICS_VERSION = "2.9.6"
    private const val FCM_VERSION = "17.3.4"
    private const val AUTH_VERSION = "16.0.4"
    private const val JOB_DISPATCHER_VERSION = "0.8.5"


    const val CORE = "com.google.firebase:firebase-core:$VERSION"
    const val CRASHLYTICS = "com.crashlytics.sdk.android:crashlytics:$CRASHLYTICS_VERSION"
    const val REMOTE_CONFIG = "com.google.firebase:firebase-config:$REMOTE_CONFIG_VERSION"
    const val STORAGE = "com.google.firebase:firebase-storage:$VERSION"
    const val DATABSE = "com.google.firebase:firebase-database:$VERSION"
    const val FCM = "com.google.firebase:firebase-messaging:$FCM_VERSION"
    const val AUTH = "com.google.firebase:firebase-auth:$AUTH_VERSION"
    const val JOB_DISPATCHER = "com.firebase:firebase-jobdispatcher:$JOB_DISPATCHER_VERSION"

}

object FirebaseUI {

    private const val VERSION = "4.2.1"
    private const val FACEBOOK_LOGIN_VERSION = "4.38.1"
    private const val TWITTER_LOGIN_VERSION = "3.3.0"

    const val AUTH = "com.firebaseui:firebase-ui-auth:$VERSION"
    const val STORAGE = "com.firebaseui:firebase-ui-storage:$VERSION"
    const val FACEBOOK_LOGIN = "com.facebook.android:facebook-login:4.38.1"
    const val TWITTER_LOGIN = "com.twitter.sdk.android:twitter-core:$TWITTER_LOGIN_VERSION@aar"
}

object EasyPermission {
    private const val VERSION = "2.0.0"
    const val EASY_PERMISSION = "pub.devrel:easypermissions:$VERSION"
}

object Dagger {
    private const val VERSION = "2.24"

    const val DAGGER = "com.google.dagger:dagger:$VERSION"
    const val ANDROID_SUPPORT = "com.google.dagger:dagger-android-support:$VERSION"
    const val COMPILER = "com.google.dagger:dagger-compiler:$VERSION"
    const val ANDROID_PROCESSOR = "com.google.dagger:dagger-android-processor:$VERSION"
}

object Retrofit {
    private const val VERSION = "2.6.1"
    private const val SERIALIZATION_VERSION = "0.4.0"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:$VERSION"
    const val CONVERTER_MOSHI = "com.squareup.retrofit2:converter-moshi:$VERSION"
    const val CONVERTER_SERIALIZATION =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:$SERIALIZATION_VERSION"
    const val RX_JAVA_ADAPTER = "com.squareup.retrofit2:adapter-rxjava2:$VERSION"
}

object Moshi {
    private const val VERSION = "1,7.0"

    const val MOSHI = "com.squareup.moshi:moshi:$VERSION"
}

object Rx {
    private const val VERSION = "2.1.0"
    const val JAVA = "io.reactivex.rxjava2:rxjava:$VERSION"
    const val ANDROID = "io.reactivex.rxjava2:rxandroid:$VERSION"
}

object OkHttp {
    private const val VERSION = "4.0.1"

    const val OKHTTP = "com.squareup.okhttp3:okhttp:$VERSION"
}

object LeakCanary {
    private const val VERSION = "2.0-beta-2"

    const val ANDROID = "com.squareup.leakcanary:leakcanary-android:$VERSION"
}
